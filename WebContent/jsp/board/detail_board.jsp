<%@page import="kr.dao.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript">
$(document).ready(function(){
	$("input").click(function(event){
		var name = $(event.target).attr("name");
		switch(name){
		case "edit_board":
			if( confirm("글을 수정하시겠습니까?") )
				location.href = "<%= request.getContextPath() %>/board/update_board_form.do?no=${no}";
			break;
		case "delete_board":
			if( confirm("글을 삭제하시겠습니까?") )
				location.href = "<%= request.getContextPath() %>/board/delete_board.do?no=${no}";
			break;
		case "post_list":
			location.href = "<%= request.getContextPath() %>/board/free_board_list.do";
			break;
		}
	});
	
});
</script>
<div align="center">
	<h3>상세2</h3>
	<table style="width: 100%;">

		<c:if test="${ board != null }">
			<tr>
				<th width="23%">번호</th>
				<td>${ board.board_no }</td>
			</tr>
			<tr>
				<th width="23%">작성날짜</th>
				<td>${ board.regDate }</td>
			</tr>
			<tr>
				<th width="23%">제목</th>
				<td><c:out value="${ board.title }" /></td>
			</tr>

			<tr>
				<th width="23%">글쓴이</th>
				<td>${ board.id }</td>
			</tr>
			<tr>
				<th width="23%">내용</th>
				<td><c:out value="${ board.content }" /></td>
			</tr>
			<tr>
				<th width="23%">조회수</th>
				<td>${ board.cnt }</td>
			</tr>
			<%-- <tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${ fileList }" var="file" >
						<form name="downForm" action="/carpool/jsp/board/fileDownload.jsp" method="GET">
							<img class="download" src="/carpool/upload/${file.fileSaveName}" width="100px">
							<input type="hidden" name="oriName" value="${file.fileOriName}">
							<input type="hidden" name="saveName" value="${file.fileSaveName}">
						</form>
					</c:forEach>
				
				</td>
			</tr>
 --%>
		</c:if>
	</table>
<%-- 	<c:if test="${ login_result.id == board.id  }">--%>
	<input class="btn" type="button" name="edit_board" value="수정"> 
	<input class="btn" type="button" name="delete_board" value="삭제">
<%-- 	</c:if> --%>
	<input class="btn" type="button" name="post_list" value="목록">
</div>