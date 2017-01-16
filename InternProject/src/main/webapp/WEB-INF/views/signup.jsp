<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.dev2.intern.vo.PostVO"%>
<!DOCTYPE>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- Library include -->
		<script type="text/javascript" src="/lib/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/lib/js/jquery-ui.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/lib/css/jquery-ui.css" />
		
		<!-- Common file include -->
		<script type="text/javascript" src="/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		
		<!-- private file include -->
		<script type="text/javascript" src="/js/signup.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/signup.css" />
		
		<title>main</title>
	</head>
	<body>
		<div id="header">
			<script type="text/javascript">
				$("#header").load("/header");
			</script>
		</div>

		<div id="body">
			<div class="main_frame">
				<h2 id="board_title"></h2>
				
				<div id="signup_frame">
					<table class="signup_table">
						<tr>
							<td>Email</td>
							<td>
								<input class="signup_form" id="email" type="email" placeholder="Email to be used at login" autofocus />
								<div class="form_status"></div>
							</td>
						</tr>
						<tr>
							<td>Password</td>
							<td>
								<input class="signup_form" id="password1" type="password" placeholder="Password" />
								<input class="signup_form" id="password2" type="password" placeholder="Password enter again" />
								<div class="form_status"></div>
							</td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input class="signup_form" id="name" type="text" placeholder="Name"></td>
						</tr>
					</table>

					<div id="signup_button">회원가입</div>
				</div>
			</div>
		</div>
		
		<div id="footer">
			<script type="text/javascript">
				$("#footer").load("/footer");
			</script>
		</div>
	</body>
</html>
