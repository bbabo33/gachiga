<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/page/include/head.jsp" %>
<script>
<c:if test="${ not empty login_result }">
alert("잘못된 접근입니다");
location.href="<%=request.getContextPath()%>";
</c:if>

if(window.sessionStorage.getItem('token') != null && document.referrer.indexOf("login/naver_login.do") != -1){
	$.ajax({
		url : '<%=request.getContextPath()%>/member/naver_check_user.do',
		type : 'post',
		data : {
			'token' : window.sessionStorage.getItem('token')
		},
		success : getData,
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		},
		async:false
	});
}	

function getData(data){
	if( data == 0) { //로그인성공
		location.href="<%=request.getContextPath()%>";
		}
}
</script>
</head>
<body>
	<header>
		<%@ include file="/page/include/header.jsp" %> 
	</header>
	<section>
		<%@ include file="/jsp/member/add_user_form.jsp" %>
	</section>
	<footer>
		<%@ include file="/page/include/footer.jsp" %>
	</footer>
</body>
</html>