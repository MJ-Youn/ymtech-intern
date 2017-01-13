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
	
	$(".post_file").click(function() {
		$(this).remove();
	});
	
});

function checkModify() {
	if ($("#confirm").html() === "글수정") {
		modalConfirmContents = MODAL_POST_MODIFY_CONFIRM_CONTENTS;
		CURRENT_POST_ID = Number($(location).attr("href").split(POST_ROOT)[1].split(MODIFY_ROOT)[0]);
		modify = true;
	} else if ($("#confirm").html() === "글쓰기") {
		modalConfirmContents = MODAL_POSTING_CONFIRM_CONTENTS;
	}
}

function postNewPost() {
	var title = defendXSS($("#post_title").val());
	var contents = CKEDITOR.instances['post_contents'].getData();
	var data = new FormData();
	var file = null;

//	var files = [];
//	
//	for (var i = 0 ; i < $("#file_form")[0].files.length ; i++) {
//		files[i] = $("#file_form")[0].files[i];
//	}
	
	data.append("boardId", Number(CURRENT_BOARD_ID));
	data.append("userId", userId);
	data.append("title", title);
	data.append("contents", contents);
	
	if ($("#file_form")[0].files.length !== 0) {
		file = $("#file_form")[0].files[0];
		data.append("file", file);
	}
	
//	data.append("files", files);
	
	if (isValidationPost(title, contents) === true ) {
		$.ajax({
			type: "POST",
			contentType: false,
			url: POST_ROOT,
			data: data,
			dataType: "json",
			processData: false,
			success: function(data) {
				if (data.header.resultCode === 200) {
					$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
				} else {
					alert("Error");
				}
			},
			error: function(request, status, error){
				alert("code:" + request.status + "\nmessage:" + request.responseText + "\nerror:" + error);
	        }
		});
	}
}

function modifyPost() {
	var title = defendXSS($("#post_title").val());
	var contents = CKEDITOR.instances['post_contents'].getData();
	var data = new FormData();
	var file = null;
	
	data.append("id", Number(CURRENT_POST_ID));
	data.append("title", title);
	data.append("contents", contents);
	
	if ($("#file_form")[0].files.length !== 0) {
		file = $("#file_form")[0].files[0];
		data.append("file", file);
	}
	
	if (isValidationPost(title, contents) === true ) {
//		callAjax("PATCH", POST_ROOT, {
//			"id": CURRENT_POST_ID,
//			"title": title,
//			"contents": contents
//		}, function() {
//			$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + POST_ROOT + CURRENT_POST_ID);
//		});
		
		$.ajax({
			type: "POST",
			contentType: false,
			url: POST_ROOT + CURRENT_POST_ID + MODIFY_ROOT,
			data: data,
			dataType: "json",
			processData: false,
			success: function(data) {
				if (data.header.resultCode === 200) {
					$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + POST_ROOT + CURRENT_POST_ID);
				} else {
					alert("Error");
				}
			},
			error: function(request, status, error){
				alert("code:" + request.status + "\nmessage:" + request.responseText + "\nerror:" + error);
	        }
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