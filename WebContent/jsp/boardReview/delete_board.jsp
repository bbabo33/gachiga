<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));

	pageContext.setAttribute("no", no);
	%>
<script>
	$(document).ready(function() {
		<c:if test="${ empty login_result }">
		alert("로그인이 필요합니다.");
		history.back(1);
		</c:if>
	});
</script>
<script>
	alert("글 삭제완료");
    location.href = "/carpool/boardReview/review_board_list.do";
</script>