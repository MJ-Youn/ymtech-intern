<%@page import="com.dev2	.intern.util.UserGradeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page import="com.dev2.intern.vo.BoardVO"%>
<script type="text/javascript" src="/js/header.js"></script>

<h1 id="title">MJ</h1>

<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String grade = auth.getAuthorities().toArray()[0].toString();
	
	if (UserGradeUtil.getGradeNameByLevel(-1).equals(grade) == false && "ROLE_ANONYMOUS".equals(grade) == false) {
		%>
		<script>
			callAjax("GET", "/user/id", null, function(data) {
				userId = data.body.id;
			})
		</script>
		<%
	} 
%>

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
			<form action="/logout" method="post" name="logout">
				<div id="info">${user}</div>
				<input type="submit" id="logout" class="user_button" value="" />
			</form>
		</div>
	</sec:authorize>
</div>

<div id="dialog" title="">
	<p id="dialog_contents"></p>
</div>

<div id="loading">
</div>
