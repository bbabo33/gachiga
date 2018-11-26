<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<div class="myProfileArea">
	<div class="wrap">
		<h2>프로필 정보</h2>
		<table>
			<tr>
				<th>이름</th>
				<td>${login_result.name}</td>
				<th>이메일</th>
				<td>${login_result.email }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${login_result.tel }</td>
				<th>회원가입일</th>
				<td>${login_result.regDate }</td>
			</tr>
			<tr>
				<th colspan="2">주소</th>
				<td colspan="2">${login_result.addr }</td>
			</tr>
		</table>
	</div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
	<div class="clear"></div>
</div>