<%@page import="kr.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(document).ready(function(){
	$("input[name=new_post]").click(function(){
		location.href="<%=request.getContextPath()%>/board/add_board_form.do?post_type=${post_type}";
	});
});

function doAction(board_no){
	location.href="<%=request.getContextPath()%>/board/detail_board.do?post_type=${post_type}&no="+board_no;
}
	
</script>
<jsp:include page="/jsp/board/search_board.jsp" />
<h3 align="center">
	<c:if test="${post_type == 'free'}">자유 게시판</c:if>
	<c:if test="${post_type == 'review'}">후기 게시판</c:if>
</h3>

<div align="center">
	<table class="list_table">
		<tr>
			<th width="8%">번호</th>
			<th>제목</th>
			<th width="10%">조회수</th>
			<th width="15%">글쓴이</th>
			<th width="10%">등록일</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td align="center">${board.board_no}</td>
				<td><a href="javascript:doAction('${board.board_no}')"> 
				<c:out value="${board.title}" />
				</a></td>
				<td>${board.cnt}</td>
				<td>${board.id}</td>
				<td>${board.regDate}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${ empty boardList }">
		<h3>등록된 게시글이 없습니다</h3>
	</c:if>
	<c:forEach var="i" begin="${start}" end="${end}" step="1">
		<c:choose>
			<c:when test="${i != end }">
				<a href="<%=request.getContextPath()%>/board/board_list.do?post_type=${post_type}&pageNo=${i}">${i}/</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/board/board_list.do?post_type=${post_type}&pageNo=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<div class="R">
		<input class="btn" type="button" name="new_post" value="새글등록">
	</div>
</div>