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
					<%-- location.href = "<%= request.getContextPath() %>/board/delete_board.do?no=${no}"; --%>
					$.ajax({
						url : "<%=request.getContextPath()%>/board/delete_board.do",
						data :{
							'no' : '${board.board_no}'	
						},
						type : 'post',
						success : callback,
						error : function(xhr, ajaxOptions, thrownError) {
				             alert(xhr.status + " " + thrownError);
			            }
					});
				break;
			case "post_list":
				location.href = "<%= request.getContextPath() %>/board/free_board_list.do";
				break;
		}
	});
	
	function callback(){
		alert("글 삭제완료");
		location.href = "<%=request.getContextPath()%>/board/free_board_list.do";
	}
	
});
</script>
<div align="center">
	<hr>
	<h3>상세 내용</h3>
	<hr>
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
			
		</c:if>
	</table>
	
 	<c:if test="${ login_result.id == board.id  }">
		<input class="btn" type="button" name="edit_board" value="수정"> 
		<input class="btn" type="button" name="delete_board" value="삭제">
	</c:if>
	<hr width="100%">
	<input class="btn" type="button" name="post_list" value="목록"><br><br>
	<div id="div_comment">Comment</div>	
			<c:if test="${ ! empty login_result }">
				<jsp:include page="/page/include/board_Comment.jsp"/>
			</c:if>
	
	<table class="commentBox">
		<tr>
			<th>작성자</th>
			<th>내용</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="cl" items="${commentList }">
		<tr>
			<td>				
				${cl.writer }
			</td>
			<td>
				${cl.content }
			</td>
			<td>
				${cl.regDate }
			</td>
		</tr>		
		</c:forEach>
	</table>	
	<br>
</div>