"use strict";

var CURRENT_BOARD_ID = "";
var CURRENT_BOARD_NAME = "";

var BOARD_LIST = [{id: 1, name: "공지사항"},{id: 2, name: "자유 게시판"}];
var ROOT = "/";
var BOARD_ROOT = "/board/";
var BOARD_WRITE_ROOT = "/write/";
var POST_ROOT = "/post/";

var NEW_BOARD_LIST_ELEMENT = "<li class='board_tab'></li>";

var MODAL_CONFIRM_TITLE = "확인";
var MODAL_CONFIRM_CONTENTS = "글을 게시하시겠습니까?";
var MODAL_CANCEL_TITLE = "취소";
var MODAL_CANCEL_CONTENTS = "작성했던 내용은 저장되지 않습니다. 뒤로 돌아가시겠습니까?";

var MODAL_BUTTON_OK = "확인";
var MODAL_BUTTON_CANCEL = "취소";

$(document).ready(function() {
	CURRENT_BOARD_ID = $(location).attr("href").split(BOARD_ROOT)[1].split("/")[0];
	CURRENT_BOARD_NAME = getBoardName(CURRENT_BOARD_ID);
	$("#board_title").html(CURRENT_BOARD_NAME);
});

function getBoardId(name) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (BOARD_LIST[i].name === name) {
			return BOARD_LIST[i].id;
		}
	}
}

function getBoardName(id) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (BOARD_LIST[i].id === Number(id)) {
			return BOARD_LIST[i].name;
		}
	}
}

function fillModalData(title, contents) {
	$("#dialog").attr("title", title);
	$("#dialog_contents").html(contents);
}