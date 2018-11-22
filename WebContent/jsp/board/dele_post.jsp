<%@page import="kr.vo.BoardFileVO"%>
<%@page import="kr.dao.BoardDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	int no = request.getParameter("no") == null ? 0 : Integer.parseInt(request.getParameter("no"));

	BoardDAO dao = new BoardDAO();
	
	List<BoardFileVO> fileList = dao.selectFileList(no);
	
	for(BoardFileVO fileVO : fileList){
		File file = new File("C:/lecture/web-workspace/carpool/WebContent/upload/" + fileVO.getFileSaveName() );
		if(file.exists())
			file.delete();
	}
	//dao.deleteFile(no);

	int cnt = dao.deleteBoard(no);
%>
<script>
<%if (cnt != 0) {%>
	alert("글 삭제완료");
<%} else {%>
	alert("글 삭제실패");
<%}%>
	location.href = "/carpool/page/board/list_post.jsp";
</script>