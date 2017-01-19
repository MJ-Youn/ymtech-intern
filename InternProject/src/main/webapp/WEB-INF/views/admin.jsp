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
		
		<script type="text/javascript" src="/js/admin.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/admin.css" />
		
		<title>main</title>
	</head>
	<body>
		<div id="admin_lnb">
			<div id="home_title"></div>
			<ul id="menu_list">
				<li id="post" class="active">게시글 관리</li>
				<li id="file">첨부파일 관리</li>
				<li id="comment">댓글 관리</li>
				<li id="user">유저 관리</li>
				<li id="trash_post">삭제한 게시글 관리</li>
				<li id="trash_file">삭제한 첨부파일 관리</li>
				<li id="trash_comment">삭제한 댓글 관리</li>
				<li id="trash_user">삭제한 유저 관리</li>
			</ul>
			<div id="exit"></div>
		</div>
		<div id="admin_body">
			<h2 id="menu_title">게시글 관리</h2>
			<table id="list">
				<%-- <tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
				<tr>
						<td class="number">${post.postNumber }</td>
						<td class="title">${post.title }</td>
						<td class="name">${post.userName }</td>
						<td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.modifyDate }" /></td>
						<td class="hit_count">${post.hitCount }</td>
					</tr>
					<tr>
						<td class="number">${post.postNumber }</td>
						<td class="title">${post.title }</td>
						<td class="name">${post.userName }</td>
						<td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.modifyDate }" /></td>
						<td class="hit_count">${post.hitCount }</td>
					</tr>
					<tr>
						<td class="number">${post.postNumber }</td>
						<td class="title">${post.title }</td>
						<td class="name">${post.userName }</td>
						<td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.modifyDate }" /></td>
						<td class="hit_count">${post.hitCount }</td>
					</tr>
<tr>
						<td class="number">${post.postNumber }</td>
						<td class="title">${post.title }</td>
						<td class="name">${post.userName }</td>
						<td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.modifyDate }" /></td>
						<td class="hit_count">${post.hitCount }</td>
					</tr> --%>
			</table>
			<ul class="pagination" id="pagination${pageCount.pageCount}">
			</ul>
		</div>
	</body>
</html>
