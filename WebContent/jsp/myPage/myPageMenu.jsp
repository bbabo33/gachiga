<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="myProfileMenu">
	<div id="menuNav">
		<ul class="profileMenu">
			<li><h3>마이페이지</h3></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyPageMain.do"><span>내정보</span></a></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyReservation.do"><span>받은예약</span></a></li>
			<li><a href="<%=request.getContextPath()%>/myPage/SentReserv.do"><span>나의예약</span></a></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyCar.do"><span>차량정보</span></a></li>
			<li><a href="<%=request.getContextPath()%>/member/UpdateMForm.do"><span>회원수정</span></a></li>
			<li><a href="<%=request.getContextPath()%>/member/Withdrawal.do"><span>회원탈퇴</span></a></li>
		</ul>
	</div>
</div>
