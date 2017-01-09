"use strict";

var currentPage = Number(1);
var pageCount = 1;

$(document).ready(function() {
	$("#post_write").click(function() {
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + BOARD_WRITE_ROOT);
	});
	
	$.ajax({
		url: BOARD_ROOT + CURRENT_BOARD_ID + PAGE_ROOT,
		type: "GET",
		dataType: "json",
		success: function(data) {
			pageCount = Number(data.body.pageCount); 
			createPagination();
		},
		error:function(request,status,error){
			alert("code: " + request.status + "\nmessage: " + request.responseText + "\nerror: " + error);
		}
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
