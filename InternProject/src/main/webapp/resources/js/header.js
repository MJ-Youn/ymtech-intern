"use strict";

var BOARD_LIST = [];

$(document).ready(function() {
	listUpBoard();

	if ($(location).attr("href").includes(BOARD_ROOT)) {
		CURRENT_BOARD_ID = $(location).attr("href").split(BOARD_ROOT)[1].split("/")[0];
		CURRENT_BOARD_NAME = getBoardName(CURRENT_BOARD_ID);
		$("#board_title").html(CURRENT_BOARD_NAME);
	} else if ($(location).attr("href").includes(LOGIN_ROOT.match(/[a-zA-Z]/g).join(""))) {
		$("#board_title").html("Log In");
		
		if ($(location).attr("href").includes("false")) {
			viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_LOGIN_FAIL_CONTENTS, function() {
				document.logout.submit();
			});
		}
	} else if ($(location).attr("href").includes(SIGNUP_ROOT.match(/[a-zA-Z]/g).join(""))) {
		$("#board_title").html("Sign Up");
	}
	
	$("#title").click(function() {
		$(location).attr("href", ROOT);
	});

	$(".board_tab").click(function() {
		CURRENT_BOARD_ID = Number($(this).attr("id").match(/[0-9]/g).join(""));
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + "1");
	});
	
	$("#goto_login").click(function() {
		$(location).attr("href", LOGIN_ROOT);
	});
	
	$("#goto_signup").click(function() {
		$(location).attr("href", SIGNUP_ROOT);
	});
	
	$("#info").click(function() {
		$(location).attr("href", USER_ROOT);
	});
});

function listUpBoard() {
	var boardElementList = $(".board_tab");
	
	for (var i = 0 ; i < boardElementList.length ; i++) {
		BOARD_LIST[i] = {"id": boardElementList[i].id.split("board")[1], "name": boardElementList[i].innerHTML}; 
	}
}

function getBoardId(name) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (name === BOARD_LIST[i].name) {
			return BOARD_LIST[i].id;
		}
	}
}

function getBoardName(id) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (Number(id) === Number(BOARD_LIST[i].id)) {
			return BOARD_LIST[i].name
		}
	}
}
