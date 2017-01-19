"use strict";

var EMAIL;
var PASSWORD;
var NAME;
var emailCheck = false;
var passwordCheck = false;
var nameCheck = false;

$(document).ready(function() {
	$("#email").focusout(function() {
		checkValidEmail();
	});
	
	$("#password1").focusout(function() {
		checkValidPassword();
	});
	
	$("#password2").focusout(function() {
		checkValidPassword();
	});
	
	$("#signup_button").click(function() {
		signup();
	});
	
	$("#modify_button").click(function() {
		modify();
	});
	
	$("#delete_button").click(function() {
		deleteUser();
	});
	
	$("#admin_page").click(function() {
		$(location).attr("href", ADMIN_ROOT);
	});
});

function checkValidEmail() {
	var emailPattern = /^[0-9a-zA-Z\-\_]+@[0-9a-zA-Z\-]+\.[a-zA-Z\.]+$/;
	var email = $("#email").val();
	var form_status = $("#email").siblings(".form_status");
	
	if (email !== undefined && emailPattern.test(email)) {
		form_status.css("background-image", "url('/img/valid.ico')");
		EMAIL = email;
		emailCheck = true;
	} else {
		form_status.css("background-image", "url('/img/invalid.ico')");
		EMAIL = null;
		emailCheck = false;
	}
}

function checkValidPassword() {
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	var form_status = $("#password2").siblings(".form_status");
	
	if (password1 !== undefined && password1 !== null && password1 !== "" && password1 === password2) {
		form_status.css("background-image", "url('/img/valid.ico')");
		PASSWORD = password1;
		passwordCheck = true;
	} else {
		form_status.css("background-image", "url('/img/invalid.ico')");
		PASSWORD = null;
		passwordCheck = false;
	}
}

function checkValidName() {
	var name = $("#name").val();
	
	if (name !== undefined && name !== null && name !== "") {
		NAME = name;
		nameCheck = true;
	} else {
		NAME = null;
		nameCheck = false;
	}
}

function signup() {
	checkValidName();
	
	if (emailCheck !== true) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_INVALID_EMAIL);
	} else if (passwordCheck !== true) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_INVALID_PASSWORD);
	} else if (nameCheck !== true) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_INVALID_NAME);
	} else {
		callAjax("POST", SIGNUP_ROOT, {
			"email" : EMAIL,
			"password" : PASSWORD,
			"name" : NAME
		}, function(data) {
			viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_SUCCESS_CONTENTS, function() {
				$(location).attr("href", LOGIN_ROOT);
			});
		});
	}
}

function modify() {
	checkValidName();
	
	if (passwordCheck !== true) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_INVALID_PASSWORD);
	} else if (nameCheck !== true) {
		viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_SIGNUP_INVALID_NAME);
	} else {
		callAjax("POST", USER_ROOT, {
			"password" : PASSWORD,
			"name" : NAME
		}, function(data) {
			viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_USER_MODIFY_CONTENTS, function() {
				$(location).attr("href", ROOT);
			});
		});
	}
}

function deleteUser() {
	viewPromptModal(MODAL_CONFIRM_TITLE, MODAL_UESR_DELETE_CONTENTS, function() {
		callAjax("DELETE", USER_ROOT, null, function() {
			viewConfirmModal(MODAL_CONFIRM_TITLE, MODAL_USER_DELETE_CONFIRM, function() {
				document.logout.submit();
			});
		});
	});
}