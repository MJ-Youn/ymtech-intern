<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
		<script type="text/javascript" src="/js/view.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/view.css" />
		
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
						<th colspan="6" id="post_title">이건 첫번째 글입니다. 아닐수도 있구요.</th>
					</tr>
					<tr>
						<td>작성자</td>
						<td id="writer_name">홍길동</td>
						<td>날짜</td>
						<td id="create_date">2017.01.01</td>
						<td>조회수</td>
						<td id="hit_count">231234</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="6" id="post_contents">블라블라블라<br/>asdczx</td>
					</tr>
				</table>
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