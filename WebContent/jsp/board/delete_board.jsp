<%@page import="kr.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int no = request.getParameter("no") == null ? 0 : Integer.parseInt(request.getParameter("no"));

	BoardDAO dao = new BoardDAO();

	int cnt = dao.deleteBoard(no);
%>
<script>
<%if (cnt != 0) {%>
	alert("삭제완료");
<%} else {%>
	alert("삭제실패");
<%}%>
	location.href = "/carpool/page/board/free_board.do";
</script>
