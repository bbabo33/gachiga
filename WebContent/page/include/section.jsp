<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.vo.BoardVO"%>
<%@ page import="kr.dao.BoardDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/* BoardDAO dao = new BoardDAO();
	pageContext.setAttribute("boardList", dao.selectBoardList(1, 5)); */
%>
<div style="float: left; width: 20%;" align="center" id="logo">
	<img src="/carpool/assets/images/carpool1.jpg"> 
	<br>1. 출발지와 목적지가 비슷한 운전자 또는 승객을 고릅니다.
</div> 
<div style="float: left; width: 20%;" align="center" id="logo">
	<img src="/carpool/assets/images/carpool2.png">
	<br>2. 운전자 또는 승객을 고르면 서로 연락을 해서 약속을 합니다.
</div>
<div style="float: left; width: 20%;" align="center" id="logo">
	<img src="/carpool/assets/images/carpool3.jpg">
	<br>3. 만나서 상대방을 확인합니다.
</div>
<div style="float: left; width: 20%;" align="center" id="logo">
	<img src="/carpool/assets/images/carpool4.jpg">
	<br>4. 목적지로 출발합니다.
</div> 
<div style="float: left; width: 20%;" align="center" id="logo">
	<img src="/carpool/assets/images/carpool5.png">
	<br>5. 승객은 운전자에게 사전에 협의한 금액을 지불합니다.
</div>