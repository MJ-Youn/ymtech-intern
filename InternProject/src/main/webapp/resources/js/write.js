"use strict";

$(document).ready(function() {
	CKEDITOR.replace("post_contents", {
		height : "350px",
		resize_enabled : false,
		enterMode : CKEDITOR.ENTER_BR
	});
	
	/*
	 * Modal 
	 */
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
					
					var title = defendXSS($("#post_title").val());
					var contents = CKEDITOR.instances['post_contents'].getData();
					var userId = 1;
					
					$.ajax({
						type: "POST",
						contentType : "application/json; charset=UTF-8",
						url: POST_ROOT + BOARD_WRITE_ROOT,
						data: JSON.stringify({
							"boardId" : CURRENT_BOARD_ID,
							"userId": userId,
							"title": title,
							"contents": contents
						}),
						success: function(data) {
							if (data === "200") {
								$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT + 1);
							} else {
								alert("Error");
							}
						},
						error:function(request, status, error){
							alert("code:" + request.status + "\nmessage:" + request.responseText + "\nerror:" + error);
				        }
					});
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
