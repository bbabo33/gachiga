<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.vo.BoardVO"%>
<%@ page import="kr.dao.BoardDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/* BoardDAO dao = new BoardDAO();
	pageContext.setAttribute("boardList", dao.selectBoardList(1, 5)); */
%>
 
<div align="center" id="carpoolList">
	<h3>카풀 게시판</h3>
	<div id="table">
		<div id="row">
			<span>글쓴이</span>
			<span>출발지</span>
			<span>도착지</span>
			<span>유형</span>
			<span>좌석수</span>
			<span>출발일</span>
		</div>
		<c:forEach items="${cList }" var="carpool">
		<div id="row">
			<span>${carpool.writer_id }</span>
			<span>${carpool.start_place_name}</span>
			<span>${carpool.end_place_name }</span>
			<span>${carpool.post_type }</span>
			<span>${carpool.user_cnt}</span>
			<span>${carpool.start_time}</span>
		</div>
		</c:forEach>
	</div>
</div>
<div align="center" id="boardList">
	<h3>자유 게시판</h3>
	<div id="table">
		<div id="row">
			<span>번호</span>
			<span>제목</span>
			<span>글쓴이</span>
			<span>등록시간</span>
		</div>
		<c:forEach items="${bList }" var="board">
		<div id="row">
			<span>${board.board_no}</span>
			<span>${board.title}</span>
			<span>${board.id }</span>
			<span>${board.regDate}</span>
		</div>
		</c:forEach>
	</div>
</div>
<div align="center" id="reviewList">
	<h3>후기 게시판</h3>
	<div id="table">
		<div id="row">
			<span>번호</span>
			<span>제목</span>
			<span>글쓴이</span>
			<span>등록시간</span>
		</div>
		<c:forEach items="${rList }" var="review">
		<div id="row">
			<span>${review.board_no}</span>
			<span>${review.title}</span>
			<span>${review.id }</span>
			<span>${review.regDate}</span>
		</div>
		</c:forEach>
	</div>
</div>

