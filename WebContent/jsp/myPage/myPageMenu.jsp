<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="myProfileMenu">
	<div id="menuNav">
		<ul class="profileMenu">
			<li><h3>마이페이지</h3></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyProfile.do"><span>내정보</span></a></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyReservation.do"><span>예약내역</span></a></li>
			<li><a href="<%=request.getContextPath()%>/myPage/MyCar.do"><span>차량등록</span></a></li>
			<li><a href="<%=request.getContextPath()%>/member/Withdrawal.do"><span>회원탈퇴</span></a></li>
		</ul>
	</div>
</div>
