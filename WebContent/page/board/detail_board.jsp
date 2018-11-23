<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));

	pageContext.setAttribute("no", no);
%>
<script>
	 $(document).ready(function() {
		<c:if test="${ empty login_result }">
		alert("로그인이 필요합니다.");
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
		<jsp:include page="/jsp/board/detail_board.jsp" />
	</section>
	<footer>
		<%@ include file="/page/include/footer.jsp"%>
	</footer>
</body>
</html>
