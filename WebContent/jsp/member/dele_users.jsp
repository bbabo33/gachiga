<%@page import="java.util.Arrays"%>
<%@page import="util.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="notice.vo.MemberVO"%>
<%@ page import="notice.dao.MemberDAO"%>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAO dao = new MemberDAO();

	String[] id_arr = request.getParameterValues("m_check");

	int cnt = 0;
	for (String id : id_arr) {
		cnt += dao.dele_user(id);
	}
%>
<script>
	
<%if (cnt != 0) {%>
	alert(<%= cnt %> + "개의 회원이 삭제되었습니다");
<%} else { %>
	alert("회원삭제 실패");
<%}%>
	location.href = "/carpool/page/member/list_user.jsp";
</script>