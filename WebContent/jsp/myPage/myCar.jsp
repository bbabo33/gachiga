<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
li {
	list-style: none;
	float: left;
	margin: 0 10px;
}
</style>
<script>
$(document).ready(function(){
	$('#regiCar').click(function(){
		location.href="<%=request.getContextPath()%>/myPage/MyCarForm.do";
	});
});
</script>
<div class="myProfileArea">
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
	<hr style="clear: both;">

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
	<input type="button" value="차량등록" id="regiCar"> 
	<input type="button" value="등록삭제" id="deleteCar">
</div>