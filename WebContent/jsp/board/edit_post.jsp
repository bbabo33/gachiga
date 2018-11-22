<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="util.*"%>
<%@page import="notice.vo.BoardVO"%>
<%@page import="notice.dao.BoardDAO"%>
<%@page import="java.sql.*"%>

<%
	request.setCharacterEncoding("utf-8");

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	int no = Integer.parseInt(request.getParameter("no"));

	BoardVO board = new BoardVO();
	board.setTitle(title);
	board.setWriter(writer);
	board.setContent(content);
	board.setNo(no);

	BoardDAO dao = new BoardDAO();
	int cnt = dao.updateBoard(board);
%>
<script>
	
<%if (cnt != 0) {%>
	alert("글 수정완료");
<%} else {%>
	alert("글 수정실패");
<%}%>
	location.href = "/Mission-Web/page/board/detail_post.jsp?no=<%= no %>";
</script>