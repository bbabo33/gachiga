<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.vo.MemberVO"%>
<%@ page import="kr.dao.MemberDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id") == null ? "" : request.getParameter("id");
	MemberDAO dao = new MemberDAO();
	MemberVO de_member = dao.selectById(id);
	pageContext.setAttribute("member", de_member);
	String email = "";
	String tel = "";
	String addr = "";
	/* if (de_member.getEmail_id() != null && de_member.getEmail_domain() != null) {
		email = de_member.getEmail_id() + '@' + de_member.getEmail_domain();
	}
	if (de_member.getTel1() != null && de_member.getTel2() != null && de_member.getTel3() != null) {
		tel = de_member.getTel1() + '-' + de_member.getTel2() + '-' + de_member.getTel3();
	}
	if (de_member.getBasic_addr() != null && de_member.getDetail_addr() != null) {
		addr = de_member.getBasic_addr() + '-' + de_member.getDetail_addr() + '(' + de_member.getPost() + ')';
	}
	pageContext.setAttribute("email", email);
	pageContext.setAttribute("tel", tel);
	pageContext.setAttribute("addr", addr); */
%>

<script type="text/javascript">
	$(document).ready(function() {
		$("input").click(function(event) {
			var name = $(event.target).attr("name");
			switch (name) {
			case "list_member":
				location.href = "list_user.jsp";
				break;
			}
		});
	});
</script>
<div align="center">
	<hr>
	<h3>회원 상세</h3>
	<hr>
	<table style="width: 100%;">
		<tr>
			<td width="23%">아이디</td>
			<td>${member.id}</td>
		</tr>
		<tr>
			<td width="23%">이름</td>
			<td>${member.name}</td>
		</tr>
		<tr>
			<td width="23%">이메일</td>
			<td>${ email }</td>
		</tr>
		<tr>
			<td width="23%">전화번호</td>
			<td>${ tel }</td>
		</tr>
		<tr>
			<td width="23%">주소</td>
			<td>${ addr }</td>
		</tr>
		<tr>
			<td width="23%">가입날짜</td>
			<td>${member.regDate }</td>
		</tr>
	</table>
	<input type="button" name="list_member" value="목록">
</div>