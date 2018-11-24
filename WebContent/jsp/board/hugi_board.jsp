<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 align="center">후기 게시판</h3>
<div align="center">
	<table class="list_table">
		<tr>
			<th width="8%">번호</th>
			<th>제목</th>
			<th width="15%">글쓴이</th>
			<th width="10%">등록일</th>
		</tr>
		<c:forEach items="${ reviewList  }" var="board">
			<tr>
				<td>${ board.board_no }</td>
				<td><a
					href="javascript:go_detail('${ board.board_no }', '${ not empty login_result }')">
						<c:out value="${ board.title }" />
				</a></td>
				<td>${ board.id }</td>
				<td>${ board.regDate }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="새글작성">
</div>