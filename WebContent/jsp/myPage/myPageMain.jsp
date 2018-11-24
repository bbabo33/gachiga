<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<div class="myProfileArea">
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
	<hr style="clear: both;">
	<h2>내 정보</h2>
	<table>
		<tr>
			<th>이름</th>
			<td>${login_result.name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${login_result.email }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${login_result.tel }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${login_result.addr }</td>
		</tr>
		<tr>
			<th>회원가입일</th>
			<td>${login_result.regDate }</td>
		</tr>

	</table>
	<div align="center">
		<input type="button" value="회원정보 수정" id="updateBtn">
</div>