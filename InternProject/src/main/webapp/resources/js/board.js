"use strict";

$(document).ready(function() {
	$("#post_write").click(function() {
		$(location).attr("href", BOARD_ROOT + CURRENT_BOARD_ID + BOARD_WRITE_ROOT);
	});
});