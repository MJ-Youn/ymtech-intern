"use strict";

var CURRENT_POST_ID = Number($(location).attr("href").split(POST_ROOT)[1].split(MODIFY_ROOT)[0]);
var CURRENT_COMMENT_ID = -1;
var CURRENT_COMMENT_CONTENTS = "";

$(document).ready(function() {
	$("#modify_post").click(function() {
		var currentUrl = $(location).attr("href");
		$(location).attr("href", currentUrl + MODIFY_ROOT);
	});
	
	$("#delete_post").click(function() {
		viewPromptModal(MODAL_CONFIRM_TITLE, MODAL_DELETE_CONFIRM_CONTENTS, function() {
			deletePost();
		});
	});
	
	$(".comment_reply").click(function() {
		$(this).parent().next().toggle();
	});
	
	$(".comment_modify").click(function() {
		if (CURRENT_COMMENT_ID !== -1) {
			cancelModifyComment();
		}
		
		CURRENT_COMMENT_ID = extractCommentId($(this).parent());
		CURRENT_COMMENT_CONTENTS = $(this).parent().children(".comment_contents").html();
		$(this).parent().children(".comment_contents").attr("contenteditable", "true");
		$(this).parent().children(".comment_contents").css("border", "1px solid skyblue");
		$(this).parent().children(".comment_options_button").toggle();
	});
	
	$(".comment_ok").click(function() {
		CURRENT_COMMENT_CONTENTS = $(this).parent().children(".comment_contents").html();
		callAjax("PATCH", COMMENT_ROOT, {
			"id": CURRENT_COMMENT_ID,
			"contents": defendXSS(CURRENT_COMMENT_CONTENTS)
		}, function() {
			location.reload();
		});
	});
	
	$(".comment_no").click(function() {
		cancelModifyComment();
	});
	
	$(".comment_delete").click(function() {
		var thisComment = $(this).parent();
		var commentId = extractCommentId(thisComment);
		
		viewPromptModal(MODAL_CONFIRM_TITLE, MODAL_DELETE_CONFIRM_CONTENTS, function() {
			callAjax("DELETE", COMMENT_ROOT, {
				"commentId": commentId
			}, function() {
				location.reload();
			});
		});
	});
	
	$(".comment_button").click(function() {
		var commentDepth = 1;
		var commentParentId = -1;
		var commentContent = $(this).prev().val().replace(/\n/g, "<br/>");

		if (!checkNewComment($(this))) {
			var thisComment = $(this).parent().prev();
			commentDepth = extractCommentDepth(thisComment) + 1;
			commentParentId = extractCommentId(thisComment);
		}

		if (isWhiteSpace(commentContent) === true) {
			viewConfirmModal(MODAL_CANCEL_TITLE, MODAL_POSTING_UNVALID_CONTENTS);
		} else {
			callAjax("POST", COMMENT_ROOT, {
				"parentCommentId": commentParentId,
				"postId": CURRENT_POST_ID,
				"userId": userId,
				"depth": commentDepth,
				"contents": defendXSS(commentContent)
			}, function() {
				location.reload();
			});
		}
	});
	

	$('.comment_contents').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13) {
			// insert 2 br tags (if only one br tag is
			// inserted the cursor won't go to the next
			// line)
			document.execCommand('insertHTML', false, "<br><br>");
			// prevent the default behaviour of return
			// key pressed
			return false;
		}
	});
});

function deletePost() {
	callAjax("DELETE", POST_ROOT, {
		"postNumber" : extractPostId()
	}, function() {
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
	});
}

function cancelModifyComment() {
	$("#comment" + CURRENT_COMMENT_ID).children(".comment_contents").html(CURRENT_COMMENT_CONTENTS);
	$("#comment" + CURRENT_COMMENT_ID).children(".comment_contents").attr("contenteditable", "false");
	$("#comment" + CURRENT_COMMENT_ID).children(".comment_contents").css("border", "none");
	$("#comment" + CURRENT_COMMENT_ID).children(".comment_options_button").toggle();
	CURRENT_COMMENT_ID = -1;
}

function extractPostId() {
	return Number($(location).attr("href").split(POST_ROOT)[1].match(/[0-9]/g).join(""));
}

function extractCommentDepth(comment) {
	return Number(comment.attr("class").match(/[0-9]/g).join(""));
}

function extractCommentId(comment) {
	return Number(comment.attr("id").match(/[0-9]/g).join(""));
}

function checkNewComment(comment) {
	var prevId = comment.parent().prev().attr("id");
	
	if (prevId === "comment_list") {
		return true;
	} else {
		return false;
	}
}