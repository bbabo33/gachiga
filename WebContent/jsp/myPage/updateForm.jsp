<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id ="update_user_form">
	<h3>회원정보 수정</h3>
	<form action="/carpool/member/UpdateMProcess.do" name="updateForm" method="post">
			<input type="hidden" name="id" value="${member.id }">
			<input type="hidden" name="name" value="${member.name }">
		<div id="id">
			<lable for="userID">아이디</lable>
			<span>${member.id }</span>
		</div>
		<div id="name">
			<label for="name">이름</label>
			<span>${member.name }</span>
		</div>
		<div id="bitrh">
			<label for="birth">생일</label>
			<input type="text" value="${member.birth }" name="birth">
		</div>
		<div id="age">
			<label for="age">나이</label>
			<input type="text" value="${member.age }" name="age">
		</div>
		<div id="addr">
			<label for="addr">주소</label>
			<input type="text" value="${member.addr }" name="addr">
		</div>
		<div id="tel">
			<label for="tel">전화번호</label>
			<input type="text" value="${member.tel }" name="tel">
		</div>
		<br>
		<br>
		<button type="submit">수정</button>
	</form>
</div>