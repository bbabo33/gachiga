<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("input").click(function(event) {
			var name = $(event.target).attr("name");
			switch (name) {
			case "new_post":
				location.href = "add_post.jsp";
				break;
			case "mem_list":
				location.href = "/carpool/page/member/list_user.jsp";
				break;
			}
		});
		
		$("form[name=search]").submit(function(){
			if( $("input[name=search]").val() == "" ){
				alert("검색어를 입력하세요");
				return false;
			}
			return true;
		});
	});
</script>
<div align="center">
	<hr>
	<h3>"${ search }" 검색 결과</h3>
	
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
				<td><a href="javascript:go_detail('${board.no }', '${ not empty login_result }')"> <c:out
							value="${board.title }" />
				</a></td>
				<td>${board.writer }</td>
				<td>${ board.regDate}</td>
			</tr>
		</c:forEach>
	</table>

	<div id="page_step">
		<c:forEach var="i" begin="1" end="${step}" step="1">
			<a class="page_link" href="search_post.jsp?page=${i}&search=${search}&category=${category}">${i}</a>
		</c:forEach>
	</div>
	<c:if test="${ not empty member }">
		<div class="R">
			<input class="btn" type="button" name="new_post" value="새글등록">
		</div>
	</c:if>
</div>