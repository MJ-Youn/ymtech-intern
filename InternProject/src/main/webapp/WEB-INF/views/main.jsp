<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- Start: Library include -->
		<script type="text/javascript" src="/lib/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="/lib/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="/lib/js/angular-1.6.1.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/lib/css/jquery-ui.min.css" />
		<!-- E n d: Library include -->

		<script type="text/javascript" src="/js/common.js"></script>		
		<script type="text/javascript" src="/js/main.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
		
		<script type="text/javascript" src="/js/header.js"></script>
		
		<title>main</title>
	</head>
	<body ng-app="app" ng-controller="main">
		<div id="header" ng-include="'/header'"></div>
		<div class="board" ng-include="'/board'">
			<!-- <ng-include src="getBoardBaseUrl()"></ng-include> -->
		</div>
		<div id="footer" ng-include="'/footer'"></div>
	</body>
</html>