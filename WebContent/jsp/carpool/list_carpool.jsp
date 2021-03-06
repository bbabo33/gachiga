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
<jsp:include page="/jsp/carpool/search_carpool.jsp" />
<div align="center">
	<h3>카풀 목록</h3>
	
	<div class="list_wrap">
		<div class="row">	
			<span class="cell col1 head">글쓴이</span>
			<span class="cell col3 head">출발지</span>
			<span class="cell col3 head">도착지</span>
			<span class="cell col1 head">유형</span>
			<span class="cell col1 head">좌석수</span>
			<span class="cell col1 head">출발일</span>
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
			</div>
		</c:forEach>
	</div>
	<c:if test="${ empty carpool_list }">
		<h3>등록된 게시글이 없습니다</h3>
	</c:if>
	
	<div id="page_step">
		<c:forEach var="i" begin="${start}" end="${end}" step="1">
			<a class="page_link ${ pageNo == i ? 'check' : '' }" href="<%=request.getContextPath()%>/carpool/list_carpool.do?pageNo=${i}">${i}</a>
		</c:forEach>
	</div>
</div>