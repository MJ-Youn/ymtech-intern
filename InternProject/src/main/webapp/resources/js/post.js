"use strict";

$(document).ready(function() {
	$("#modify_post").click(function() {
		var currentUrl = $(location).attr("href");
		$(location).attr("href", currentUrl + MODIFY_ROOT);
	});
	
	$("#delete_post").click(function() {
		fillModalData(MODAL_CONFIRM_TITLE, MODAL_DELETE_CONFIRM_CONTENTS);
		$("#dialog").dialog({
			resizable : false,
			height : "auto",
			width : 400,
			modal : true,
			buttons : [ {
				text : MODAL_BUTTON_OK,
				click : function() {
					$(this).dialog("close");
					
					deletePost();
				}
			}, {
				text : MODAL_BUTTON_CANCEL,
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	});
});

function deletePost() {
	$.ajax({
		type: "DELETE",
		contentType: "application/json; charset=utf-8",
		url: POST_ROOT,
		data: JSON.stringify({
			"postNumber" : extractPostId()
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

function extractPostId() {
	return Number($(location).attr("href").split(POST_ROOT)[1].match(/[0-9]/g).join(""));
}
