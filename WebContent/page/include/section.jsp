<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.vo.BoardVO"%>
<%@ page import="kr.dao.BoardDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/* BoardDAO dao = new BoardDAO();
	pageContext.setAttribute("boardList", dao.selectBoardList(1, 5)); */
%>
<div id="container">
	<h3>최신글목록</h3>

	<table class="list_table">
		<tr>
			<th width="7%">번호</th>
			<th>제목</th>
			<th width="20%">등록일</th>
			<th width="16%">글쓴이</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.no }</td>
				<td><a href="javascript:go_detail('${board.no }', '${ not empty login_result }')">
				${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>
			</tr>
		</c:forEach>

	</table>
</div>