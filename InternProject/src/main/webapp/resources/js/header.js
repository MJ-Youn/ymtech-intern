"use strict";

$(document).ready(function(){
	$("#title").click(function(){
		$(location).attr('href', '/');
	});
});

function getBoardId(name) {
	for (var i = 0 ; i < BOARD_LIST.length ; i++) {
		if (BOARD_LIST[i].name === name) {
			return BOARD_LIST[i].id;
		}
	}
}

app.controller("header", function($scope){
	
});