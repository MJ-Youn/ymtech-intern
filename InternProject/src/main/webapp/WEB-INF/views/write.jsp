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
		
		<script type="text/javascript" src="/js/write.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/write.css" />
		
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
				<table class="write_table">
					<tr>
						<td>제목</td>
						<td><input id="post_title" type="text" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea id="post_contents" rows="10" cols="10"></textarea></td>
					</tr>
				</table>
				<div class="button_frame">
					<div id="confirm" class="button">글쓰기</div>
					<div id="cancel" class="button">취소</div>
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