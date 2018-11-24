<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<script>
	$(document).ready(function(){
		$('#updateBtn').click(function(){
			location.href="<%=request.getContextPath()%>/member/UpdateMForm.do";
		});
	});
</script>
<div class="myProfileArea">
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />

	<hr style="clear: both;">
</div>