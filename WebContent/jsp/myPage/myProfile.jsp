<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script>
	$(document).ready(function(){
		$('#updateBtn').click(function(){
			location.href="<%= request.getContextPath()%>/member/UpdateMForm.do";
		});
	});
</script>
</head>
 <body>
	<section>
		<div class="myProfileArea">
			<jsp:include page="/jsp/myPage/myPageMenu.jsp"/>
			
			
			<hr style="clear: both;">
			<h2>내 정보</h2>
			<table>
			<tr>
				<th>이름</th>
				<td>${member.name}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.email }</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${member.birth }</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${member.age }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${member.tel }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${member.addr }</td>
			</tr>
			<tr>
				<th>회원가입일</th>
				<td>${member.regDate }</td>
			</tr>
		
		
		</table>
		<br>
		<br>
		<div align="center">
		<input type="button" value="회원정보 수정" id="updateBtn">
		</div>
		<br>
		<br>
		</div>
		</section>
			
 	<%-- 	<div>
		<hr>
		<h2>마이페이지</h2>
		<hr>
		<table>
			<tr>
				<th>이름</th>
				<td>${member.name}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.email }</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${member.birth }</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${member.age }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${member.tel }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${member.addr }</td>
			</tr>
			<tr>
				<th>회원가입일</th>
				<td>${member.regDate }</td>
			</tr>
		
		
		</table>
	</div>
</body>
<aside>
	<section>
		<ul>
			<li><a href="/member/MyProfile.do">내 프로필</a></li>
			<li><a href="">예약내역</a></li>
			<li><a href="/member/MyCar.do">차량등록</a></li>
			<li><a href="">회원탈퇴</a></li>
		</ul>
	</section>
</aside> --%>
</body>
</html> 