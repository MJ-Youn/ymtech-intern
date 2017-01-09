<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- Library include -->
		<script type="text/javascript" src="/lib/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="/lib/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="/lib/js/angular-1.6.1.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/lib/css/jquery-ui.min.css" />
		
		<script type="text/javascript" src="/js/main.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		
		<title>main</title>
	</head>
	<body ng-app="app" ng-controller="main">
	
		<div id="header" ng-include="'/header'"></div>
		
		<div class="board">
			<div class="post_frame">
				<h2>공지 사항</h2>
				<table class="post_list">
					<tr>
						<th class="number">Number</th>
						<th class="title">Title</th>
						<th class="name">Writer</th>
						<th class="date">Date</th>
						<th class="hit_count">Hit Count</th>
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
			</div>
		</div>
		
		<div id="footer" ng-include="'/footer'"></div>
		
	</body>
</html>