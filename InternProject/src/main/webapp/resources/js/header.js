"use strict";

$(document).ready(function(){
	appendList();
	
	$("#title").click(function() {
		$(location).attr("href", ROOT);
	});
	
	$(".board_tab").click(function() {
		$(location).attr("href", BOARD_ROOT + getBoardId($(this).html()));
	});
});

function appendList() {
	for (var i = 0 ; i < BOARD_LIST.length ; i++ ) {
		var newList = $(NEW_BOARD_LIST_ELEMENT);
		newList.html(BOARD_LIST[i].name);
		$(".board_list").append(newList);
	}
}