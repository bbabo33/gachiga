<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<link rel="stylesheet" href="/carpool/assets/css/table.css">
<script>
	$(document).ready(function(){
		$('#reservation').click(function(){
			location.href="";
		});
		
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
	});
</script>
<div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp"></jsp:include>
</div>
<div>
	<jsp:include page="/jsp/myPage/reservMenu.jsp"></jsp:include>
</div>

<div id="table">
	<div class="row">
		<span class="cell col1 head">프로필</span> 
		<span class="cell col1 head">출발</span> 
		<span class="cell col1 head">도착</span> 
		<span class="cell col1 head">타입</span> 
		<span class="cell col1 head">자리</span>
	</div>
</div>

<c:forEach items="${requestScope.applyList }" var="apply">
	<div class="row">
		<span class="cell col1">${apply.writer_id }</span> 
		<span class="cell col1">${apply.start_place_name }<br>${apply.start_date}:${apply.start_time}</span> 
		<span class="cell col1">${apply.end_place_name }</span> 
		<span class="cell col1">${apply.type }</span> 
		<span class="cell col1">${apply.user_cnt }</span>
	</div>
</c:forEach>