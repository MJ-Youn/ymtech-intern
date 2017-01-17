"use strict";

$(document).ready(function() {
//	$("#login_button").click(function() {
//		login();
//	});
//	
//	$("body").keypress(function(event) {
//		if(event.which === 13) {
//			login();
//		}
//	});
});

function login() {
	var email = $("#id").val();
	var password = $("#password").val();
	
	alert(email + " " + password);
}