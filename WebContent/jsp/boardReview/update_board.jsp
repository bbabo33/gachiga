<%@page import="kr.dao.ReviewDAO"%>
<%@page import="kr.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>

<%
	request.setCharacterEncoding("utf-8");

	int no = Integer.parseInt(request.getParameter("no"));
	String title = request.getParameter("title");
	String id = request.getParameter("id");
	String content = request.getParameter("content");

	BoardVO board = new BoardVO();
	board.setBoard_no(no);
	board.setTitle(title);
	board.setId(id);
	board.setContent(content);

	ReviewDAO dao = new ReviewDAO();
	int cnt = dao.updateBoard(board); 
%>
<script>
	
<%if (cnt != 0) {%>
	alert("글 수정완료");
<%} else {%>
	alert("글 수정실패");
<%}%>
	location.href = "/carpool/page/boardReview/detail_board.jsp?no=<%= no %>";
</script>