<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/carpool/assets/css/myPageCss.css">
<link rel="stylesheet" href="/carpool/assets/css/table.css">
<script>
	$(document).ready(function(){
		$('#reservation').click(function(){
			location.href="";
		});
		
		$('#detail').click(function(){
			location.href="<%=request.getContextPath()%>/myPage/reservationDetail.do";
		});
		$("input[name=apply_cancel]").click(function(e){
			var no = $(e.target).parent().find("input[name=no]").val();
			$.ajax({
				url : '<%=request.getContextPath()%>/apply/cancel_apply.do',
				type : 'post',
				data : {
					'no' : no
				},
				success : function(data){
					if(data.trim()==1){
						alert("예약이 취소되었습니다");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status + " " + thrownError);
				}
			});
		});
	});
</script>
<div class="myProfileArea">
	<div class="wrap" id="reserv_list">
		<h2>나의예약내역</h2>
		<div class="row">
			<span class="cell col1 head">작성자</span> 
			<span class="cell col1 head">출발</span> 
			<span class="cell col1 head">도착</span> 
			<span class="cell col1 head">자리</span>
			<span class="cell col1 head">상태</span>
		</div>
		<c:forEach items="${requestScope.applyList }" var="apply">
		<div class="row">
			<span class="cell col1">${apply.writer_id }<br>${apply.start_date}:${apply.start_time}</span> 
			<span class="cell col1">${apply.start_place_name }</span> 
			<span class="cell col1">${apply.end_place_name }</span> 
			<span class="cell col1">${apply.user_cnt }</span>
			<span class="cell col1">
			<c:if test="${apply.status == 'success' }">
				예약성공
				<input class="btn" type="button" value="예약취소" name="apply_cancel">
				<input type="hidden" value="${apply.no }" name="no">
			</c:if>
			<c:if test="${apply.status == 'cancel' }">
				예약취소
			</c:if>
			</span>
			
		</div>
		</c:forEach>
		<c:if test="${ empty applyList }">
			<h2>예약 내역이 없습니다</h2>
		</c:if>
	</div>
	<jsp:include page="/jsp/myPage/myPageMenu.jsp" />
	<div class="clear"></div>
</div>