<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<!-- <link rel="stylesheet" href="/carpool/assets/css/table.css"> -->
<script>

	$(document).ready(function(){
		
		
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
		
		
	});
	
	function gotReserv(no){
		
	}
	

</script>
<div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
</div>
<div>
	<jsp:include page="/jsp/myPage/reservMenu.jsp" />
</div>
<div id="table">
	<div class="row">
		<span class="cell col1 head">프로필</span> <span class="cell col1 head">출발</span>
		<span class="cell col1 head">도착</span> <span class="cell col1 head">타입</span>
		<span class="cell col1 head">자리</span>
	</div>
		<c:forEach items="${requestScope.carpoolList }" var="carpool">
		<div class="row">
			<span class="cell col2">${carpool.writer_id }</span>
			<span class="cell col2">${carpool.start_place_name } <br>${carpool.start_date }:${carpool.start_time }</span>
			<span class="cell col2">${carpool.end_place_name }</span>
			<span class="cell col2">${carpool.post_type }</span>
			<span class="cell col2">${carpool.user_cnt - carpool.apply_cnt }/${carpool.user_cnt }
				<input type="button" value="예약보기" id="reservation"	onclick="gotReserv(${carpool.no})">
			</span>

		</div>
		</c:forEach>
</div>


