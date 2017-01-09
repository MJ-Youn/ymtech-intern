"use strict";

$(document).ready(function() {
	CKEDITOR.replace("post_contents", {
		height : "350px",
		resize_enabled : false,
		enterMode : CKEDITOR.ENTER_BR
	});

	$("#cancel").click(function() {
		fillModalData(MODAL_TITLE_CANCEL, MODAL_CONTENTS_CANCEL);
		$("#dialog").dialog({
			resizable : false,
			height : "auto",
			width : 400,
			modal : true,
			buttons : [ {
				text : MODAL_BUTTON_OK,
				click : function() {
					$(this).dialog("close");
					$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID);
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

	})
});
