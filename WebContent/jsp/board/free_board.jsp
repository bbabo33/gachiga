<%@page import="kr.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

	function doAction(board_no){
		location.href="<%= request.getContextPath()%>/board/detail_board.do?no="+board_no;
	}
	
	function goList(){
		location.href="<%= request.getContextPath()%>/board/free_board_list.do";
	}
	
</script>
<script src=""></script>
<jsp:include page="/jsp/board/search_board.jsp"/>
<hr>
<h3 align="center">자유 게시판</h3>
<hr>
<div align="center">
	<table class="list_table">
		<tr>
			<th width="8%">번호</th>
			<th>제목</th>
			<th width="15%">글쓴이</th>
			<th width="10%">등록일</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
					<tr>	
						<td align="center">${board.board_no}</td>
						<td>
							<a href="javascript:doAction('${board.board_no}')">
								<c:out value="${board.title}"/>
							</a>
							
						</td>
						<td>${board.id}</td>
						<td>${board.regDate}</td> 
					</tr>
		</c:forEach>
	</table>
		<c:forEach var ="i" begin="${start}" end="${end}" step="1">
			<c:choose>
				<c:when test="${i != end }"><a href="<%=request.getContextPath()%>/board/free_board_list.do?pageNo=${i}">${i}/</a></c:when>
				<c:otherwise><a href="<%=request.getContextPath()%>/board/free_board_list.do?pageNo=${i}">${i}</a></c:otherwise>
			</c:choose>
		</c:forEach>
 	<br>
	<input type="button" value="목록으로" onclick="goList()">
</div>