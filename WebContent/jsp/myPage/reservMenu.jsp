<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$('#getReserv').click(function(){
		location.href="<%=request.getContextPath()%>/myPage/MyReservation.do";
	});
	$('#sentReserv').click(function(){
		location.href="<%=request.getContextPath()%>/myPage/SentReserv.do";
	});
});
</script>


<hr style="clear: both;">
<h2>예약내역</h2>
<input type="button" value="받은 예약" id="getReserv">
&nbsp;
<input type="button" value="한 예약" id="sentReserv">
<br>
<br>