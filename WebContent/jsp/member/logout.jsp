<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.vo.MemberVO"%>
<%
	request.setCharacterEncoding("utf-8");

	MemberVO member = (MemberVO) session.getAttribute("member");
	if (member != null) {
		session.removeAttribute("member");
	}
	MemberVO member2 = (MemberVO) session.getAttribute("member");

	String back_url = request.getHeader("REFERER");
%>
<script>
	
<%if (member != null && member2 == null) {%>
	alert("로그아웃 성공");
<%} else {%>
	alert("로그아웃 실패");
<%}%>
	location.href = "<%=back_url%>";
</script>