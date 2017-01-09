<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/board.js"></script>

<div class="post_frame">
	<h2 class="board_name" ng-bind="boardName"></h2>
	<div id="post_write">글쓰기</div>
	<table class="post_list">
		<tr>
			<th class="number">번호</th>
			<th class="title">제목</th>
			<th class="name">작성자</th>
			<th class="date">날짜</th>
			<th class="hit_count">조회수</th>
		</tr>
		<tr>
			<td class="number">1</td>
			<td class="title">1</td>
			<td class="name">1</td>
			<td class="date">2017-01-01</td>
			<td class="hit_count">1</td>
		</tr>
		<tr>
			<td class="number">2</td>
			<td class="title">2</td>
			<td class="name">2</td>
			<td class="date">2017-01-01</td>
			<td class="hit_count">2</td>
		</tr>
		<tr>
			<td class="number">3</td>
			<td class="title">3</td>
			<td class="name">3</td>
			<td class="date">2017-01-01</td>
			<td class="hit_count">3</td>
		</tr>
	</table>
	
	<ul class="pagination">
		<li class="page_number active">1</li>
		<li class="page_number">2</li>
		<li class="page_number">3</li>
		<li class="page_number">4</li>
		<li class="page_number">5</li>
		<li class="page_number">6</li>
		<li class="page_number">7</li>
	</ul>
</div>