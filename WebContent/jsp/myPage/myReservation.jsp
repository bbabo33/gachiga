<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script>
	$(document).ready(function(){
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
	});
	function gotReserv(no){
		
	}
</script>
<div class="myProfileArea" id="reserv_list">
	<div class="wrap">
	<h2>받은예약내역</h2>
		<div class="row">
			<span class="cell col2 head">시간정보</span> 
			<span class="cell col2 head">출발</span>
			<span class="cell col2 head">도착</span> 
			<span class="cell col2 head">인원수</span>
		</div>
		<c:forEach items="${requestScope.carpoolList }" var="carpool">
			<div class="row">
				<span class="cell col1">${carpool.start_date }:${carpool.start_time }</span> 
				<span class="cell col3">${carpool.start_place_name }</span> 
				<span class="cell col3">${carpool.end_place_name }</span> 
				<span class="cell col2">${carpool.user_cnt - carpool.apply_cnt }/${carpool.user_cnt }
					<%-- <br><input type="button" class="btn" value="예약보기" id="reservation"
					onclick="gotReserv(${carpool.no})"> --%>
				</span>
			</div>
			<c:forEach items="${reservList[carpool.no]}" var="reservMem">
			<div class="row">
				<span class="cell col2"></span>
				<span class="cell col2">${reservMem.id }</span>
				<span class="cell col2">${reservMem.name }</span>
				<span class="cell col2">${reservMem.tel }</span>
			</div>
			</c:forEach> 
		</c:forEach>
		<c:if test="${ empty carpoolList }">
			<h2>예약 내역이 없습니다</h2>
		</c:if>
	</div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
	<div class="clear"></div>
</div>