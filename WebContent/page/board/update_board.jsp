<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	pageContext.setAttribute("no", no);
%>
<%@ include file="/page/include/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		<c:if test="${ empty login_result }">
		alert("로그인하세요");
		history.back(1);
		</c:if>
	});

</script>
</head>
<body>
	<header>
		<%@ include file="/page/include/header.jsp"%>
	</header>
	<section>
		<jsp:include page="/jsp/board/update_board_form.jsp">
			<jsp:param name="no" value="${no}"/>
		</jsp:include>
	</section>
	<footer>
		<%@ include file="/page/include/footer.jsp"%>
	</footer>
</body>
</html>
