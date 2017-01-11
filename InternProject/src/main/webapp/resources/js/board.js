"use strict";

var currentPage;
var pageCount;

$(document).ready(function() {
	pageCount = Number($(".pagination").attr("id").match(/[0-9]/g).join(""));
	currentPage = Number($(location).attr("href").split(PAGE_ROOT)[1]);
	createPagination();
	
	$("#post_write").click(function() {
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + WRITE_ROOT);
	});
	
	$(".post_list .title").click(function() {
		var postId = Number($(this).attr("id").match(/[0-9]/g).join(""));
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + POST_ROOT + postId);
	});
});

function createPagination() {
	for (var i = 0 ; i < pageCount ; i++) {
		var newPage;
		if ((i + 1) === currentPage) {
			newPage = $(NEW_ACTIVE_PAGINATION_LIST_ELEMENT);
		} else {
			newPage = $(NEW_PAGINATION_LIST_ELEMENT);
		}
		
		newPage.html(i + 1);
		$(".pagination").append(newPage);
	}
}
