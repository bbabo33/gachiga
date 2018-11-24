<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	alert("${carDeleteMsg}");
	location.href="<%=request.getContextPath()%>/myPage/MyCar.do";
</script>

