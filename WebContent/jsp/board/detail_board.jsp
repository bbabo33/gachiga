<%@page import="kr.dao.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%// 줄바꿈
    pageContext.setAttribute("br", "<br/>");
    pageContext.setAttribute("cn", "\n");
%> 
<script type="text/javascript">
$(document).ready(function(){
	$("input").click(function(event){
		var name = $(event.target).attr("name");
		switch(name){
			case "edit_board":
				if( confirm("글을 수정하시겠습니까?") )
					location.href = "<%= request.getContextPath() %>/board/update_board_form.do?post_type=${post_type}&no=${no}";
				break;
			case "delete_board":
				if( confirm("글을 삭제하시겠습니까?") )
					$.ajax({
						url : '<%=request.getContextPath()%>/board/delete_board.do',
						data :{
							'no' : '${board.board_no}'	
						},
						type : 'post',
						success : function(data){
							alert("글이 삭제되었습니다");
							location.href = "<%=request.getContextPath()%>/board/board_list.do?post_type=${post_type}";
						},
						error : function(xhr, ajaxOptions, thrownError) {
				             alert(xhr.status + " " + thrownError);
			            }
					});
				break;
			case "post_list":
				location.href = "<%=request.getContextPath()%>/board/board_list.do?post_type=${post_type}";
				break;
		}
	});	
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
				<td>${fn:replace(board.content, cn, br) }</td>
				<%-- <td><c:out value="${ board.content }" /></td> --%>
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
	<input class="btn" type="button" name="post_list" value="목록"><br><br>
	<hr width="100%">
	<ol class="commentBox">
		<c:forEach var="cl" items="${commentList }">
		<li class="comment_wrap">
			<div id="comment_profile"> <!-- 프로필 -->
				<img src="/carpool/assets/images/user_profile.jpg">
			</div>
			<div id="comment_info"> <!-- 닉네임, 작성날짜  -->	
				<span id="comment_writer">${cl.writer}님</span>
				<span id="comment_date">
					<c:if test="${fn:length(cl.reg_date) == 2}">
						${cl.reg_date }시${cl.reg_date2 }분
					</c:if>
					<c:if test="${fn:length(cl.reg_date) == 7}">
						${cl.reg_date }월${cl.reg_date2 }일
					</c:if>
				</span>
			</div>
			<div id="comment_content"> <!-- 내용  -->
				<span>
					${fn:replace(cl.content, cn, br) }
				</span>
			</div>
		</li>		
		</c:forEach>
		<c:if test="${ empty commentList }">
			<h3>등록된 댓글이 없습니다</h3>
		</c:if>
	</ol>
	<c:if test="${ ! empty login_result }">
		<jsp:include page="/page/include/board_Comment.jsp"/>		
	</c:if>	
</div>