<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div ng-controller="header">
	<h1 id="title">MJ</h1>
	
	<ul class="board_list">
		<li ng-repeat="boardName in boardNameList" ng-click="menuClick($event)">{{boardName}}</li>
	</ul>
</div>
