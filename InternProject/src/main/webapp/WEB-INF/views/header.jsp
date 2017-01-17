<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.dev2.intern.vo.BoardVO"%>
<script type="text/javascript" src="/js/header.js"></script>

<h1 id="title">MJ</h1>

<ul class="board_list">
	<c:forEach var="board" items="${boardList}">
		<li class="board_tab" id="board${board.id}">${board.name}</li>
	</c:forEach>
</ul>

<div id="user_info">
	<sec:authorize access="isAnonymous()">
		<div id="login">
			<div id="goto_login" class="user_button"></div>
			<div id="goto_signup" class="user_button"></div>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<div id="login_info">
			<sec:authentication var="user" property="principal" />
			<div id="info">${user}</div>
			<div id="logout" class="user_button"></div>
		</div>
	</sec:authorize>
</div>

<div id="dialog" title="">
	<p id="dialog_contents"></p>
</div>

<div id="loading">
</div>
