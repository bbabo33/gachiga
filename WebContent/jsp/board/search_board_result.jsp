<%@page import="kr.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function doAction(board_no){
		location.href="<%=request.getContextPath()%>/board/detail_board.do?post_type=${post_type}&no="+board_no;
	}
	
</script>
<jsp:include page="/jsp/board/search_board.jsp" />
<h3 align="center">
	<c:if test="${post_type == 'free'}">자유 게시판</c:if>
	<c:if test="${post_type == 'review'}">후기 게시판</c:if>
</h3>
<h4 class="R">'${word}' 검색결과</h4>
<div class="C">
	<table class="list_table">
		<tr>
			<th width="8%">번호</th>
			<th>제목</th>
			<th width="15%">글쓴이</th>
			<th width="10%">등록일</th>
		</tr>
		<c:forEach var="board" items="${searchedList}">
			<tr>
				<td align="center">${board.board_no}</td>
				<td><a href="javascript:doAction('${board.board_no}')"> <c:out
							value="${board.title}" />
				</a></td>
				<td>${board.id}</td>
				<td>${board.regDate}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${ empty searchedList }">
		<h3>검색된 게시글이 없습니다</h3>
	</c:if>
	<c:forEach var="i" begin="${start}" end="${end}" step="1">
		<c:choose>
			<c:when test="${i != end }">
				<a href="<%=request.getContextPath()%>/board/search_board.do?post_type=${post_type}&pageNo=${i}&category=${category}&word=${word}">${i}/</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/board/search_board.do?post_type=${post_type}&pageNo=${i}&category=${category}&word=${word}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>