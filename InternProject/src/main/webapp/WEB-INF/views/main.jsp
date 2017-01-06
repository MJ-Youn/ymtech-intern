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
		<div class="board" ng-include="'/board/1'"></div>
		<div id="footer" ng-include="'/footer'"></div>
	</body>
</html>