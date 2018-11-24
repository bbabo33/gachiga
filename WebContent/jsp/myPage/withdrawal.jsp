<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#withdraw').click(function(){
			var result = confirm("회원탈퇴 하시겠습니까?");
			
			if(result){
				location.href="<%=request.getContextPath()%>/member/WithdrawalProcess.do";
			}else{
				
			}
		});
		
	});

</script>
</head>
<body>
	<section>
		<div class="myProfileArea">
			<jsp:include page="/jsp/myPage/myPageMenu.jsp"/>
			<hr style="clear: both;">
			
<h2>회원탈퇴</h2>
<div align="center">
회원 탈퇴하시면 등록한 글과 정보가 모두 삭제됩니다.<br>
기존 활동 내역 및 정보는 재가입시 복구가 불가능합니다.<br>
그래도 탈퇴를 원하실 경우 하단의 탈퇴버튼을 눌러주세요.<br>
감사합니다.<br><br><br>
<input type="button" value="회원탈퇴" id="withdraw" >

</div>

	</section>
</body>
</html>