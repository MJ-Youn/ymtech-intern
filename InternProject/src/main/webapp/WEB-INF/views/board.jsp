<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.dev2.intern.vo.PostVO"%>
<!DOCTYPE>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- Library include -->
		<script type="text/javascript" src="/lib/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="/lib/js/jquery-ui.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/lib/css/jquery-ui.min.css" />
		
		<!-- Common file include -->
		<script type="text/javascript" src="/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/common.css" />
		
		<script type="text/javascript" src="/js/board.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/board.css" />
		
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
				<div id="post_write"></div>
				<table class="post_list">
					<tr>
						<th class="number">번호</th>
						<th class="title">제목</th>
						<th class="name">글쓴이</th>
						<th class="date">날짜</th>
						<th class="hit_count">조회수</th>
					</tr>
					<c:forEach var="post" items="${postList }">
						<tr>
							<td class="number">${post.postNumber }</td>
							<td class="title" id="post${post.id }">${post.title }</td>
							<td class="name">${post.userName }</td>
							<td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.modifyDate }" /></td>
							<td class="hit_count">${post.hitCount }</td>
						</tr>
					</c:forEach>
				</table>
				<ul class="pagination" id="pagination${pageCount.pageCount}">
				</ul>
			</div>
		</div>
		
		<div id="footer">
			<script type="text/javascript">
				$("#footer").load("/footer");
			</script>
		</div>
		
	</body>
</html>