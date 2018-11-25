<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/include/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
<c:if test="${ empty login_result }">
login_required();
</c:if>
 </script>
</head>
<body>
	<header>
		<%@ include file="/page/include/header.jsp" %> 
	</header>
	<section>
		<jsp:include page="/jsp/carpool/detail_carpool.jsp" />
	</section>
	<footer>
		<%@ include file="/page/include/footer.jsp" %>
	</footer>
</body>
</html> 