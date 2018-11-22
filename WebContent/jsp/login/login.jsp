<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath()%>/assets/js/checkForm.js"></script>
<script>
	function checkForm() {
		var f = document.lform;
		
		if(isNull(f.id, "아이디를 입력하세요")) {
			return false;
		}
		
		if(isNull(f.password, "패스워드를 입력하세요")) 
			return false;
		return true;
	}
</script>
</head>
<body>

	<div align="center">
		<br>
		<hr>
		<h2>로그인</h2>
		<hr>
		<br>
		
		<form method="post" action="<%= request.getContextPath() %>/login/loginProcess.do"
			  name="lform" onsubmit="return checkForm()">
			<table style="width:40%;">
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" size="20"></td>
				</tr>
				<tr>
					<th>PASSWORD</th>
					<td><input type="password" name="password" size="20"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인">
		</form>
		
	</div>

</body>
</html>
