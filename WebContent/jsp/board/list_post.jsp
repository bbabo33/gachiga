<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath() %>/assets/js/list.js"></script>
<div align="center">
	<hr>
	<h3>게시글 목록</h3>
	
	<form class="R" action="/carpool/page/board/search_post.jsp" name="search" method="GET">
		<select name="category">
			<option value="">선택하세요</option>
			<option value="writer">글쓴이</option>
			<option value="title">제목</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색">		
	</form>
	
	<table class="list_table">
		<tr>
			<th width="7%">번호</th>
			<th>제목</th>
			<th width="16%">글쓴이</th>
			<th width="20%">등록일</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.no }</td>
				<td><a href="javascript:go_detail('${board.no }', '${ not empty login_result }')"> 
					<c:out value="${board.title }" />
					<c:if test="${ boardNoList.contains(board.no)}">
						<img src="/carpool/assets/images/file.png" width="10px">
					</c:if>
				</a></td>
				<td>${board.writer }</td>
				<td>
				${ board.regDate}
				</td>
			</tr>
		</c:forEach>
	</table>

	<div id="page_step">
		<c:forEach var="i" begin="1" end="${step}" step="1">
			<a class="page_link ${ page == i || page == null ? 'check' : '' }" href="list_post.do?page=${i}">${i}</a>
		</c:forEach>
	</div>
	<c:if test="${ not empty login_result }">
		<div class="R">
			<input class="btn" type="button" name="new_post" value="새글등록">
		</div>
	</c:if>
</div>