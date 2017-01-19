"use strict";

var TABLE_DATA_URL = "/admin/table/";
var USER_LEVEL_URL = "/admin/user/level";
var tableName = "post";

$(document).ready(function() {

	getTableData();
	
	$("#home_title").click(function() {
		$(location).attr("href", ROOT);
	});
	
	$("#exit").click(function() {
		$(location).attr("href", ROOT);
	});
	
	$("#menu_list li").click(function() {
		$("#menu_list li.active").removeClass("active");
		$(this).attr("class", "active");
		$("#menu_title").html($(this).html());
		tableName = $(this).attr("id");
		
		getTableData();
	});
	
	$(document).on("click", ".delete", function(){ 
		var parent = $(this).parent();
		var children = parent.children(":not(.delete)");
		
		var data = {};
		
		for (var i = 0 ; i < children.length ; i++) {
			data[children[i].className] = children[i].innerHTML;
		}
		
		callAjax("DELETE", TABLE_DATA_URL + tableName, data, function(data) {
			getTableData();
		});
	});
	
	$(document).on("keypress", ".level", function(event) {
		if (event.which === 13) {
			var level = $(this).html();
			
			if (level === "0" || level === "1" || level === "9") {
				var id = $(this).parent().children(".id").html();
				
				callAjax("PATCH", USER_LEVEL_URL, {
					"userId": id,
					"level": level
				},function(data) {
					getTableData();
				});
			}
		}
	});
});

function getTableData() {
	$("#list").html("");
	
	callAjax("GET", TABLE_DATA_URL + tableName, null, function(data) {
		var body = data.body;
		var keys = Object.keys(body[0]);
		
		keys[keys.length] = "delete";
		
		var table = $("#list");
		var tr = $("<tr></tr>");
		var th;
		var td;
		
		for (var i = 0 ; i < keys.length ; i++) {
			th = $("<th></th>").html(keys[i]);
			tr.append(th);
		}
		
		table.append(tr);
		
		for (var i = 0 ; i < body.length ; i++) {
			tr = $("<tr></tr>");
			
			for (var j = 0 ; j < keys.length ; j++) {
				td = $("<td></td>").attr("class", keys[j]).html(body[i][keys[j]]);

				if (keys[j] === "level") {
					td.attr("contenteditable", "true");
				} 
				tr.append(td);
			}
			
			table.append(tr);
		}
	});
}
