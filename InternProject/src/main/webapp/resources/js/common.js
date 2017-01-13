"use strict";

var CURRENT_BOARD_ID = "";
var CURRENT_BOARD_NAME = "";

var ROOT = "/";
var BOARD_ROOT = "/board/";
var WRITE_ROOT = "/write/";
var POST_ROOT = "/post/";
var PAGE_ROOT = "/page/";
var MODIFY_ROOT = "/modify/";
var COMMENT_ROOT = "/comment/";
var FILE_ROOT = "/file/";

var NEW_BOARD_LIST_ELEMENT = "<li class='board_tab'></li>";
var NEW_PAGINATION_LIST_ELEMENT = "<li class='page_number'></li>";
var NEW_ACTIVE_PAGINATION_LIST_ELEMENT = "<li class='page_number active'></li>";
var NEW_COMMENT_FORM_ELEMENT = "<div class='comment_form'> <textarea class='comment_content' rows='3' cols='50'></textarea> <div class='comment_button'></div> </div>";

var MODAL_CONFIRM_TITLE = "확인";
var MODAL_CANCEL_TITLE = "취소";
var MODAL_POSTING_CONFIRM_CONTENTS = "글을 게시하시겠습니까?";
var MODAL_POST_MODIFY_CONFIRM_CONTENTS = "글을 수정하시겠습니까?";
var MODAL_POSTING_CANCEL_CONTENTS = "작성했던 내용은 저장되지 않습니다. 뒤로 돌아가시겠습니까?";

var MODAL_POSTING_UNVALID_TITLE = "제목을 입력하세요.";
var MODAL_POSTING_UNVALID_CONTENTS = "내용을 입력하세요.";

var MODAL_DELETE_CONFIRM_CONTENTS = "삭제하시겠습니까?";

var MODAL_BUTTON_OK = "확인";
var MODAL_BUTTON_CANCEL = "취소";

//TODO: 추후 userId를 불러오는 부분 변경 필요
var userId = 1;

function fillModalData(title, contents) {
	$("#dialog").attr("title", title);
	$("#dialog_contents").html(contents);
}

function viewPromptModal(title, contents, callbackfunction) {
	fillModalData(title, contents);
	$("#dialog").dialog({
		resizable: false,
		height: "auto",
		width: 400,
		position: {
			of: $("body"),
			my: "center top",
			at: "center top+150"
		},
		modal: true,
		buttons: [ {
			text: MODAL_BUTTON_OK,
			click: function() {
				$(this).dialog("close");
				callbackfunction.call();
			}
		}, {
			text: MODAL_BUTTON_CANCEL,
			click: function() {
				$(this).dialog("close");
			}
		} ]
	});
}

function viewConfirmModal(title, contents) {
	fillModalData(title, contents);
	$("#dialog").dialog({
		resizable: false,
		height: "auto",
		width: 400,
		position: {
			of: $("body"),
			my: "center top",
			at: "center top+150"
		},
		modal: true,
		buttons: [ {
			text: MODAL_BUTTON_OK,
			click: function() {
				$(this).dialog("close");
			}
		} ]
    });
}

function callAjax(type, url, data, callbackfunction) {
	$.ajax({
		type: type,
		contentType: "application/json; charset=UTF-8",
		url: url,
		data: JSON.stringify(data),
		success: function(data) {
			if (data.header.resultCode === 200) {
				callbackfunction.call();
			} else {
				alert("Error");
			}
		},
		error: function(request, status, error){
			alert("code:" + request.status + "\nmessage:" + request.responseText + "\nerror:" + error);
        }
	});
}

function defendXSS(str) {
	str = str.replace(/\</g, "&lt;");
	str = str.replace(/\>/g, "&gt;");
	str = str.replace(/\"/g, "&quot;");
	str = str.replace(/\'/g, "&#39;");
	
	return str;
}

function isWhiteSpace(text) {
	if (text.trim() === "" | text == null) {
		return true;
	}
	
	return false;
}
