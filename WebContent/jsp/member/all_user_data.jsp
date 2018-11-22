<%@page import="notice.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="notice.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>

<%
	if (request.getMethod().equalsIgnoreCase("POST")) {
		request.setCharacterEncoding("utf-8");
	}
	MemberDAO dao = new MemberDAO();

	MemberVO member = dao.selectById(request.getParameter("id"));
	pageContext.setAttribute("member", member);
%>
[<json:object>
	<json:property name="id" value="${member.id}" />
</json:object>]