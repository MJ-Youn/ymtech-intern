"use strict";

var modalConfirmContents = "";
var modify = false;
var CURRENT_POST_ID;

$(document).ready(function() {
	CKEDITOR.replace("post_contents", {
		height : "350px",
		resize_enabled : false,
		enterMode : CKEDITOR.ENTER_BR
	});
	
	checkModify();
	
	$("#cancel").click(function() {
		viewPromptModal(MODAL_CANCEL_TITLE, MODAL_POSTING_CANCEL_CONTENTS, function() {
			$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
		});
	});

	$("#confirm").click(function() {
		viewPromptModal(MODAL_CONFIRM_TITLE, modalConfirmContents, function() {
			if (modify) {
				modifyPost();
			} else {
				postNewPost();
			}
		});
	});
});

function checkModify() {
	var currentURL = $(location).attr("href");
	
	if (currentURL.includes(MODIFY_ROOT.match(/[a-zA-Z]/g).join(""))) {
		$("#confirm").html("글수정");
		modalConfirmContents = MODAL_POST_MODIFY_CONFIRM_CONTENTS;
		CURRENT_POST_ID = Number($(location).attr("href").split(POST_ROOT)[1].split(MODIFY_ROOT)[0]);
		modify = true;
	} else {
		$("#confirm").html("글쓰기");
		modalConfirmContents = MODAL_POSTING_CONFIRM_CONTENTS;
	}
}

function postNewPost() {
	var title = defendXSS($("#post_title").val());
	var contents = CKEDITOR.instances['post_contents'].getData();
	
	// TODO: 추후 userId를 불러오는 부분 변경 필요
	var userId = 1;
	
	if (isValidationPost(title, contents) === true ) {
		callAjax("POST", POST_ROOT, {
			"boardId" : CURRENT_BOARD_ID,
			"userId": userId,
			"title": title,
			"contents": contents
		}, function() {
			$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
		});
	}
}

function modifyPost() {
	var title = defendXSS($("#post_title").val());
	var contents = CKEDITOR.instances['post_contents'].getData();
	
	if (isValidationPost(title, contents) === true ) {
		callAjax("PATCH", POST_ROOT, {
			"id": CURRENT_POST_ID,
			"title": title,
			"contents": contents
		}, function() {
			$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + POST_ROOT + CURRENT_POST_ID);
		});
	}
}

function isValidationPost(title, contents) {
	if (isWhiteSpace(title) === true) {
		viewConfirmModal(MODAL_CANCEL_TITLE, MODAL_POSTING_UNVALID_TITLE);
		return false;
	} else if (isWhiteSpace(contents) === true) {
		viewConfirmModal(MODAL_CANCEL_TITLE, MODAL_POSTING_UNVALID_CONTENTS);
		return false;
	}
	
	return true;
}

function isWhiteSpace(text) {
	if (text.trim() === "" | text == null) {
		return true;
	}
	
	return false;
}