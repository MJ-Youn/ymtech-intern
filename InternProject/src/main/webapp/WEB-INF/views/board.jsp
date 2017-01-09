<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<tr>
						<td class="number">1</td>
						<td class="title">1</td>
						<td class="name">1</td>
						<td class="date">1</td>
						<td class="hit_count">1</td>
					</tr>
					<tr>
						<td class="number">2</td>
						<td class="title">2</td>
						<td class="name">2</td>
						<td class="date">2</td>
						<td class="hit_count">2</td>
					</tr>
					<tr>
						<td class="number">3</td>
						<td class="title">3</td>
						<td class="name">3</td>
						<td class="date">3</td>
						<td class="hit_count">3</td>
					</tr>
				</table>
				<ul class="pagination">
					<!-- <li class="page_number active">1</li>
					<li class="page_number">2</li>
					<li class="page_number">3</li>
					<li class="page_number">4</li>
					<li class="page_number">5</li> -->
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