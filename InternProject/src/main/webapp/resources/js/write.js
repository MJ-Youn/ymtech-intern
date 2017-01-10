"use strict";

$(document).ready(function() {
	CKEDITOR.replace("post_contents", {
		height : "350px",
		resize_enabled : false,
		enterMode : CKEDITOR.ENTER_BR
	});

	$("#cancel").click(function() {
		fillModalData(MODAL_CANCEL_TITLE, MODAL_CANCEL_CONTENTS);
		$("#dialog").dialog({
			resizable : false,
			height : "auto",
			width : 400,
			modal : true,
			buttons : [ {
				text : MODAL_BUTTON_OK,
				click : function() {
					$(this).dialog("close");
					$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
				}
			}, {
				text : MODAL_BUTTON_CANCEL,
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	});

	$("#confirm").click(function() {
		fillModalData(MODAL_CONFIRM_TITLE, MODAL_CONFIRM_CONTENTS);
		$("#dialog").dialog({
			resizable : false,
			height : "auto",
			width : 400,
			modal : true,
			buttons : [ {
				text : MODAL_BUTTON_OK,
				click : function() {
					$(this).dialog("close");
					$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
				}
			}, {
				text : MODAL_BUTTON_CANCEL,
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	})
});
