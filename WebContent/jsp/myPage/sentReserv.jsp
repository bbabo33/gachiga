<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<link rel="stylesheet" href="/carpool/assets/css/table.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

	$(document).ready(function(){
		$('#reservation').click(function(){
			location.href="";
		});
		
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
		
		
	});
</script>
</head>
<body>
	<section>
		<div>
			<jsp:include page="/jsp/myPage/myPageMenu.jsp"></jsp:include>
		</div>
		<div>
			<jsp:include page="/jsp/myPage/reservMenu.jsp"></jsp:include>
		</div>
		
		<div id="table">
			<div class = "row">
				<span>프로필</span>
				<span>출발</span>
				<span>도착</span>
				<span>타입</span>
				<span>자리</span>
			</div>
		</div>
		
		
	
				<c:forEach items="${requestScope.applyList }" var="apply">
				<div class="row">
					<span>${apply.writer_id }</span>
					<span>${apply.start_place_name }<br>
							${apply.start_time}</span>
					<span>${apply.end_place_name }</span>
					<span>${apply.type }</span>
					<span>${apply.user_cnt }</span>
				</div>
					<div>
							<input type="button" value="상세보기" id="detail">
					</div>
				</c:forEach>
			</tbody>

		</table>
	</section>
</body>
</html>