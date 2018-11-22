<%@page import="kr.vo.MemberVO"%>
<%@page import="kr.dao.MemberDAO"%>

<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	MemberVO member = new MemberVO();

	member.setId(request.getParameter("id"));
	member.setName(request.getParameter("name"));
	member.setPassword(request.getParameter("password"));
	
	if (!request.getParameter("email").isEmpty()) {
		String[] email = request.getParameter("email").split("@");
		member.setEmail_id(email[0]);
		member.setEmail_domain(email[1]);
	}

	member.setTel1(request.getParameter("tel1"));
	member.setTel2(request.getParameter("tel2"));
	member.setTel3(request.getParameter("tel3"));
	member.setPost(request.getParameter("post"));
	member.setBasic_addr(request.getParameter("basic_addr"));
	member.setDetail_addr(request.getParameter("detail_addr"));

	MemberDAO dao = new MemberDAO();

	int cnt = dao.insertMember(member);
%>
<script>
	
<%if (cnt != 0) {%>
	alert("회원가입 완료");
<%} else {%>
	alert("회원가입 실패");
<%}%>
	location.href = "/carpool/page/member/list_user.jsp";
</script>