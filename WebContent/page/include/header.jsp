<%@page import="kr.vo.MemberVO"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVO member = (MemberVO)session.getAttribute("member");
%>
<link rel="stylesheet" href="/carpool/assets/css/header.css">
<link rel="stylesheet" href="/carpool/assets/css/login.css">
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var modal = $("#myModal");
		var modalContent = $(".modal_content");
		
		$("form[name=login_form]").submit(function(e){
			var id = $("input[name=login_id]").val();
			var password = $("input[name=login_password]").val();
			
			if( id == "" ){
				$("input[name=login_id]").focus();
				alert("아이디를 입력하세요");
				return false;
			} else if( password == "" ){
				$("input[name=login_password]").focus();
				alert("비밀번호를 입력하세요");
				return false;
			}
			e.preventDefault();

			$.ajax({
				url : '<%=request.getContextPath()%>/login/loginProcess.do',
				type : 'post',
				data : {
					'id' : id,
					'password' : password
				},
				success : callback,
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
			return true;
		});
		
		$("input[name=logout]").click(function(){
			$.ajax({
				url : '<%=request.getContextPath()%>/login/logout.do',
				type : 'post',
				success : callback,
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status + " " + thrownError);
				}
			});
		});
		function callback(data) {
			alert(data.trim());
			location.reload();
		}
		
		$("#loginBtn").click(function(){
			
			/* modal.style.display ="block";
			modalContent.style.display = "inline-block"; */
			modal.css("display","block");
			modalContent.css("display","inline-block");
			
		}); 
		
		$('.close').click(function(){
			/* modal.style.display ="none"; */
			modal.css("display","none");
		});
	});
</script>
<div id="header_content">
	<a id="main_link" href="/carpool"> 
		<img alt="google_logo" src="/carpool/assets/images/logo.png">
	</a>
	<nav id="gnb">
		<ul>
			<li><a href="<%=request.getContextPath()%>/member/list_user.do">회원관리</a></li>
			<li><a href="<%=request.getContextPath()%>/board/hugi_board_list.do">후기게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/free_board_list.do">자유게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/add_user_form.do">회원가입</a></li>
			<li><a href="<%=request.getContextPath()%>/carpool/add_carpool_form.do">카풀등록</a></li>
			<li><a href="<%=request.getContextPath()%>/carpool/list_carpool.do">카풀목록</a></li>
		</ul>
	</nav>
	
	<div id="user_info">
	<c:choose>
		<c:when test="${ empty login_result }">
		<%-- <form name="login_form" action="<%=request.getContextPath()%>/login/login.do" method="POST">
			<input class="login" type="text" name="login_id" placeholder="Id">
			<input class="login" type="password" name="login_password" placeholder="Password">
	
			<input type="submit" class="btn" value="로그인">
		</form> --%>
		<input type="button" class="btn" id="loginBtn" value="로그인">
		</c:when>
		
		<c:otherwise>
			<a>${ login_result.name }님</a>
			<input class="btn" type="button" name="logout" value="로그아웃">	
			<a href="<%=request.getContextPath()%>/member/detail_user.jsp?id=${login_result.id}">마이페이지</a>
		</c:otherwise>
	</c:choose>
		<div id="myModal" class="modal">
			<div class="modal_content">
				<span class="close">&times;</span>
				<form action ="<%= request.getContextPath() %>/login/loginProcess.do" name="login_form" class="loginBox">
					<table>
						<tr>
							<td align="center">아이디</td>
							<td><input type="text" name="login_id"></td>
						</tr>
						<tr>
							<td align="center">패스워드</td>
							<td><input type="password" name="login_password"></td>
						</tr>	
					</table>
				<div id="login_btn" align= "right">	
					<input type="submit" value="로그인">
				</div>	
				</form>	
				<div class="loginBox">
					<jsp:include page="/jsp/login/login_naver.jsp"/>
				</div>
			</div>
		</div>
	</div>
</div>