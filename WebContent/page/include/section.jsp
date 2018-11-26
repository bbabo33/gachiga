<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.vo.BoardVO"%>
<%@ page import="kr.dao.BoardDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	
		function goPage(cate, board_no){
			console.log(cate);
			console.log(board_no);
			switch(cate){
				case 'free':
					location.href="<%=request.getContextPath()%>/board/detail_board.do?post_type=free&no="+board_no;
					break;
				case 'review':
					location.href="<%=request.getContextPath()%>/board/detail_board.do?post_type=review&no="+board_no;
					break;
				case 'carpool':
					location.href="<%=request.getContextPath()%>/carpool/detail_carpool.do?post_type=carpool&no="+board_no;
					break;
			}
		}
		
	
</script>
<ul id="logo_wrap">
	<li id="logo">
		<img src="/carpool/assets/images/carpool1.jpg"> 
		<span>1. 출발지와 목적지가 비슷한 운전자 또는 승객을 고릅니다</span>
	</li> 
	<li id="logo">
		<img src="/carpool/assets/images/carpool2.png">
		<span>2. 운전자 또는 승객을 고르면 서로 연락을 해서 약속을 합니다</span>
	</li>
	<li id="logo">
		<img src="/carpool/assets/images/carpool3.jpg">
		<span>3. 만나서 상대방을 확인합니다</span>
	</li>
	<li id="logo">
		<img src="/carpool/assets/images/carpool4.jpg">
		<span>4. 목적지로 출발합니다</span>
	</li> 
	<li id="logo">
		<img src="/carpool/assets/images/carpool5.png">
		<span>5. 승객은 운전자에게 사전에 협의한 금액을 지불합니다</span>
	</li>
 </ul>
<div align="center" id="carpoolList" style="clear:both">
	<h3>카풀 게시판</h3>
	<table id="table">
		<tr class="row">
			<th class="head cell col1">글쓴이</th>
			<th class="head cell col2">출발지</th>
			<th class="head cell col2">도착지</th>
			<th class="head cell col1">유형</th>
			<th class="head cell col1">좌석수</th>
			<th class="head cell col2">출발일</th>
		</tr>
		<c:forEach items="${cList }" var="carpool">
		<tr class="row" onclick="goPage('carpool','${carpool.no}')">
			
			<td class="cell col1">${carpool.writer_id }</td>
			<td class="cell col2">${carpool.start_place_name}</td>
			<td class="cell col2">${carpool.end_place_name }</td>
			<td class="cell col1">${carpool.post_type }</td>
			<td class="cell col1">${carpool.user_cnt}</td>
			<td class="cell col2">${carpool.start_time}</td>
			
		</tr>
		</c:forEach>
	</table>
	<c:if test="${ empty cList }">
		<h3>등록된 게시글이 없습니다</h3>
	</c:if>
</div>
<div align="center" id="freeboardList" class="boardList">
	<h3>자유 게시판</h3>
	<table id="table">
		<tr class="row">
			<th class="head cell col2">번호</th>
			<th class="head cell col2">제목</th>
			<th class="head cell col2">글쓴이</th>
			<th class="head cell col2">등록시간</th>
		</tr>
		<c:forEach items="${bList }" var="board">
		<tr class="row" onclick="goPage('free','${board.board_no}')">
			<td class="cell col2">${board.board_no}</td>
			<td class="cell col2" >${board.title}</td>
			<td class="cell col2">${board.id }</td>
			<td class="cell col2">${board.regDate}</td>
		</tr>
		</c:forEach>
	</table>
	<c:if test="${ empty bList }">
	<h3>등록된 게시글이 없습니다</h3>
	</c:if>
</div>
<div align="center" id="reviewList" class="boardList">
	<h3>후기 게시판</h3>
	<table id="table">
		<tr id="row">
			<th class="head cell col2">번호</th>
			<th class="head cell col2">제목</th>
			<th class="head cell col2">글쓴이</th>
			<th class="head cell col2">등록시간</th>
		</tr>
		<c:forEach items="${rList }" var="review">
		<tr id="row" onclick="goPage('review','${review.board_no}')">
			<td class="cell col2">${review.board_no}</td>
			<td class="cell col2" >${review.title}</td>
			<td class="cell col2">${review.id }</td>
			<td class="cell col2">${review.regDate}</td>
		</tr>
		</c:forEach>
	</table>
	<c:if test="${ empty rList }">
		<h3>등록된 게시글이 없습니다</h3>
	</c:if>
</div>
<div class="clear"></div>