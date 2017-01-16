<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dev2.intern.vo.BoardVO"%>
<script type="text/javascript" src="/js/header.js"></script>

<h1 id="title">MJ</h1>

<ul class="board_list">
	<c:forEach var="board" items="${boardList}">
		<li class="board_tab" id="board${board.id}">${board.name}</li>
	</c:forEach>
</ul>

<div id="user_info">
	<div id="login">
		<div id="goto_login" class="user_button"></div>
		<div id="goto_signup" class="user_button"></div>
	</div>
</div>

<div id="dialog" title="">
	<p id="dialog_contents"></p>
</div>

<div id="loading">
</div>
