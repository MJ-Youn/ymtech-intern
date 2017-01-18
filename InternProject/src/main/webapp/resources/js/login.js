"use strict";

$(document).ready(function() {
	$("#login_button").click(function() {
		login();
	});
	
	$(".login_form").keypress(function(event) {
		if(event.which === 13) {
			login();
		}
	});
});

function login() {
	if (checkValidEmail() === false) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_LOGIN_INVALID_EMAIL);
	} else if (checkValidPassword() === false) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_LOGIN_INVALID_PASSWORD);
	} else {
		document.login.submit();
	}
}

function checkValidEmail() {
	var email = $("#id").val();
	
	if (email !== undefined && email !== null && email !== "") {
		return true;
	}
	
	return false;
}

function checkValidPassword() {
	var password = $("#password").val();
	
	if (password !== undefined && password !== null && password !== "") {
		return true;
	}
	
	return false;
}