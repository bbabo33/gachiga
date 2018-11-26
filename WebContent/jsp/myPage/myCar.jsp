<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script>
$(document).ready(function(){
	$('#regiCar').click(function(){
		location.href="<%=request.getContextPath()%>/myPage/MyCarForm.do";
	});
	$('#deleteCar').click(function(){
		if( confirm("차량정보를 삭제하시겠습니까?") )
		$.ajax({
			url : '<%=request.getContextPath()%>/myPage/MyCarDelete.do',
			type : 'post',
			success : function(data){
				if(data.trim()==1){
					alert("차량 정보를 삭제했습니다");
				} else {
					alert("차량 정보삭제에 실패했습니다");
				}
				location.reload();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status + " " + thrownError);
			}
		});
	});
});
</script>
<div class="myProfileArea">
	<div class="wrap">
		<h2>차량 정보</h2>
		<table>
			<tr>
				<th>차종</th>
				<td>${car.carName }</td>
			</tr>
			<tr>
				<th>차번호</th>
				<td>${car.carNo }</td>
			</tr>
			<tr>
				<th>좌석수</th>
				<td>${car.rideNo }</td>
			</tr>
			<tr>
				<th>차내흡연</th>
				<td>${car.smoke }</td>
			</tr>
			<tr>
				<th>대인배상</th>
				<td>${car.direct }</td>
			</tr>
	
		</table>
		<c:if test="${ empty car}">
			<input class="btn" type="button" value="차량등록" id="regiCar"> 
		</c:if>
		<c:if test="${ not empty car}">
			<input class="btn" type="button" value="차정보삭제" id="deleteCar">
		</c:if>
	</div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
</div>