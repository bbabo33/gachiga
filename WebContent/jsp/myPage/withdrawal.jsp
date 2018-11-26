<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script>
	$(document).ready(function(){
		$('#withdraw').click(function(){
			var result = confirm("회원탈퇴 하시겠습니까?");
			
			if(result){
				location.href="<%=request.getContextPath()%>/member/WithdrawalProcess.do";
				} else {
					
				}
			});
		});
</script>
<div class="myProfileArea">
	<div class="wrap C">
	<h2>회원탈퇴</h2>
		<p>회원 탈퇴하시면 등록한 글과 정보가 모두 삭제됩니다.</p>
		<p>기존 활동 내역 및 정보는 재가입시 복구가 불가능합니다.</p>
		<p>그래도 탈퇴를 원하실 경우 하단의 탈퇴버튼을 눌러주세요.</p>
		<p> 감사합니다.</p> 
		<input type="button" value="회원탈퇴" id="withdraw">
	</div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
</div>