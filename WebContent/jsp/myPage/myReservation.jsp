<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<link rel="stylesheet" href="/carpool/assets/css/table.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
		
		
	});
	
	function gotReserv(no){
		
	}
	

</script>
</head>
<body>
	<section>
		<div>
			<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
		</div>
		<div>
		<jsp:include page="/jsp/myPage/reservMenu.jsp"/>
		</div>
		<div id="table">
			<div class="row">
				<span>프로필</span>
				<span>출발</span>
				<span>도착</span>
				<span>타입</span>
				<span>자리</span>
			</div>
			<div id="row">
			<c:forEach items="${requestScope.carpoolList }" var="carpool">
				<span>${carpool.writer_id }</span>
				<span>${carpool.start_place_name }<br>
							${carpool.start_time }</span>
				<span>${carpool.end_place_name }</span>
				<span>${carpool.post_type }</span>
				<span>${carpool.user_cnt }</span>
				<span>예약 : ${carpool.apply_cnt }</span>
					<div>
						<input type="button" value="예약보기" id="reservation" onclick="gotReserv(${carpool.no})">
							<%-- <div id="div${carpool.no }">
								<c:set var="no" value="${carpool.no }"/>
								<script>
									console.log(no);
								</script>
								<c:set var="rList" value="${requestScope.reservList[no]}"/> 
								<c:forEach items="rList" var="reservMem">
									<span>${reservMem.id }</span>
									<span>${reservMem.name }</span>
									<span>${reservMem.age }</span>
									<span>${reservMem.tel }</span>
								</c:forEach> 
							</div> --%>
					</div>
			
				</c:forEach>
			</div>
		</div>
		

	</section>
</body>
</html>