<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.dao.MemberDAO"%>
<%@page import="kr.vo.MemberVO"%>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAO dao = new MemberDAO();
	MemberVO m = new MemberVO();

	m.setId(request.getParameter("id"));
	m.setPassword(request.getParameter("password"));

	MemberVO member = dao.selectForLogin(m);

	if (member != null) {
		session.setAttribute("member", member);
	}

	String back_url = request.getHeader("REFERER");
%>
<script>
	
<%if (member != null) {%>
	alert("로그인 성공");
<%} else {%>
	alert("로그인 실패");
<%}%>
	location.href = "<%=back_url%>
	";
</script>