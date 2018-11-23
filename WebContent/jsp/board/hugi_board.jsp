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
						<c:out value="${ board.title }" /> <%-- 	<c:if test="${ boardNoList.contains(board.no) }">
								<img src="/carpool/assets/images/file.png" width="10px">
							</c:if> --%>
				</a></td>
				<td>${ board.id }</td>
				<td>${ board.regDate }</td>
			</tr>
		</c:forEach>
	</table>
 	<%-- <div id="page_step">
		<c:forEach var="i" begin="1" end="${step}" step="1">
			<a class="page_link ${ page == i || page == null ? 'check' : '' }" href="list_post.do?page=${i}">${i}</a>
		</c:forEach>
	</div>
	<c:if test="${ not empty login_result }">
		<div class="R">
			<input class="btn" type="button" name="new_post" value="새글등록">
		</div>
	</c:if>
</div> --%>
	<!-- 	<form class="R" action="/carpool/page/board/search_post.jsp" name="search" method="GET">
		<select name="category">
			<option value="">선택하세요</option>
			<option value="writer">글쓴이</option>
			<option value="title">제목</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색">		
	</form> -->
	<input type="button" value="새글작성">
</div>