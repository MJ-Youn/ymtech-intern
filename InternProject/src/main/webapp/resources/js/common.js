"use strict";

var CURRENT_BOARD_ID = "";
var CURRENT_BOARD_NAME = "";

var ROOT = "/";
var BOARD_ROOT = "/board/";
var WRITE_ROOT = "/write/";
var POST_ROOT = "/post/";
var PAGE_ROOT = "/page/";
var MODIFY_ROOT = "/modify/";

var NEW_BOARD_LIST_ELEMENT = "<li class='board_tab'></li>";
var NEW_PAGINATION_LIST_ELEMENT = "<li class='page_number'></li>";
var NEW_ACTIVE_PAGINATION_LIST_ELEMENT = "<li class='page_number active'></li>";

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

function fillModalData(title, contents) {
	$("#dialog").attr("title", title);
	$("#dialog_contents").html(contents);
}

function viewPromptModal(title, contents, callbackfunction) {
	fillModalData(title, contents);
	$("#dialog").dialog({
		resizable : false,
		height : "auto",
		width : 400,
		modal : true,
		buttons : [ {
			text : MODAL_BUTTON_OK,
			click : function() {
				$(this).dialog("close");
				callbackfunction.call();
			}
		}, {
			text : MODAL_BUTTON_CANCEL,
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});
}

function viewConfirmModal(title, contents) {
	fillModalData(title, contents);
	$("#dialog").dialog({
		resizable : false,
		height : "auto",
		width : 400,
		modal : true,
		buttons : [ {
			text : MODAL_BUTTON_OK,
			click : function() {
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
			if (data === "200") {
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

//function isSuccessful(header) {
//	if (header.resultCode === 200) {
//		return true;
//	} else {
//		return false;
//	}
//}

function defendXSS(str) {
	str = str.replace(/\</g, "&lt;");
	str = str.replace(/\>/g, "&gt;");
	str = str.replace(/\"/g, "&quot;");
	str = str.replace(/\'/g, "&#39;");
	
	return str;
}
