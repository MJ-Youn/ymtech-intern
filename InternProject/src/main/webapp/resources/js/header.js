"use strict";

$(document).ready(function(){
	$("#title").click(function(){
		$(location).attr('href', '/');
	});
	
	$(".board_list li").click(function(){
		var boardName = $(this).html();
		var boardId = getBoardId(boardName);
		console.log(boardName + " " + boardId);
	});
});

function getBoardId(name) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (BOARD_LIST[i].name === name) {
			return BOARD_LIST[i].id;
		}
	}
}