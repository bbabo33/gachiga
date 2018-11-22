	<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/list.css">
<script>
$(document).ready(function(){
	$(".list_wrap div.list").click(function(e){
		var no;
		if($(e.target).prop('tagName') == "DIV"){
			no = $(e.target).find('input').val();
		}else {
			no = $(e.target).parent().find('input').val();
		}
		location.href="<%=request.getContextPath()%>/carpool/detail_carpool.do?no="+no;
	});
})
</script>
<div align="center">
	<hr>
	<h3>카풀 목록</h3>
	
	<form class="R" action="/carpool/page/carpool/search_post.jsp" name="search" method="GET">
		<select name="category">
			<option value="">선택하세요</option>
			<option value="writer">글쓴이</option>
			<option value="title">제목</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색">		
	</form>
	
	<div class="list_wrap">
		<div class="row">	
			<span class="cell col1 head">글쓴이</span>
			<span class="cell col3 head">출발지</span>
			<span class="cell col3 head">도착지</span>
			<span class="cell col2 head">유형</span>
			<span class="cell col2 head">좌석수</span>
			<span class="cell col2 head">출발일</span>
		</div>
		<c:forEach items="${carpool_list}" var="post">
			<div class="row list">	
				<span class="cell col1">${post.writer_id }</span>
				<span class="cell col2">${post.start_place_name}</span>
				<span class="cell col2">${post.end_place_name}</span>
				<span class="cell col1">${post.post_type == 'driver' ? '타세요' : '태워주세요' }</span>
				<span class="cell col1">${post.user_cnt - post.apply_cnt}/${ post.user_cnt }</span>
				<span class="cell col1">${post.start_date}</span>
				<input type="hidden" value="${post.no}">
				<%-- <td><a href="javascript:go_detail('${board.no }', '${ not empty login_result }')"> 
					<c:out value="${post.title }" />	
				</a></td> --%>
			</div>
		</c:forEach>
	</div>
	
	<div id="page_step">
		<c:forEach var="i" begin="1" end="${step}" step="1">
			<a class="page_link ${ page == i || page == null ? 'check' : '' }" href="list_post.do?page=${i}">${i}</a>
		</c:forEach>
	</div>
	<c:if test="${ not empty login_result }">
		<div class="R">
			<input class="btn" type="button" name="new_post" value="새글등록">
		</div>
	</c:if>
</div>