<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- Library include -->
		<script type="text/javascript" src="/lib/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="/lib/js/jquery-ui.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/lib/css/jquery-ui.min.css" />
		
		<script type="text/javascript" src="/lib/ckeditor/ckeditor.js"></script>
		
		<!-- CommonJS include -->
		<script type="text/javascript" src="/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		
		<script type="text/javascript" src="/js/post.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/post.css" />
		
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
				<table id="post_table">
					<tr>
						<th colspan="6" id="post_title">${post.title }</th>
					</tr>
					<tr>
						<td>작성자</td>
						<td id="writer_name">${post.userName }</td>
						<td>날짜</td>
						<td id="create_date">${post.modifyDate }</td>
						<td>조회수</td>
						<td id="hit_count">${post.hitCount }</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="6" id="post_contents">${post.contents }</td>
					</tr>
				</table>
				<div id="post_button_frame">
					<div id="modify_post" class="post_button"></div>
					<div id="delete_post" class="post_button"></div>
				</div>
				<div id="comment_list">
					<div class="comment_depth1"><div class="comment_user">admin</div><div class="comment_date">2017-01-01</div><div class="comment_contents">absdqweq</div></div>
					<div class="comment_depth2"><div class="comment_user">admin</div><div class="comment_date">2017-01-01</div><div class="comment_contents">absdqweq</div></div>
					<div class="comment_depth2"><div class="comment_user">admin</div><div class="comment_date">2017-01-01</div><div class="comment_contents">absdqweq</div></div>
					<div class="comment_depth3"><div class="comment_user">admin</div><div class="comment_date">2017-01-01</div><div class="comment_contents">absdqweq</div></div>
					<div class="comment_depth1"><div class="comment_user">admin</div><div class="comment_date">2017-01-01</div><div class="comment_contents">absdqweq</div></div>
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