<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/add_form.css">
<script src="<%= request.getContextPath() %>/assets/js/place_search.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/add_carpool.js"></script>
<script>
$(document).ready(function(){
	$("form[name=cForm]").submit(function(e) {
		e.preventDefault();
		console.log( $( this ).serializeArray() );
		$.ajax({
			url : '<%=request.getContextPath()%>/carpool/add_carpool.do',
			type : 'post',
			data : {
				'writer_id' : $("input[name=writer_id]").val(),
				'start_date' : $("input[name=start_date]").val(),
				'start_place_name' : $("input[name=start_place_name]").val(),
				'start_time' : $("input[name=start_time]").val(),
				'end_place_name' : $("input[name=end_place_name]").val(),
				'smoke' : $("input[name=smoke]:checked").val(),
				'money' : $("input[name=money]").val(),
				'user_cnt' : $("input[name=user_cnt]").val(),
				'post_type' : $("input[name=post_type]:checked").val(),
				'add_info' : $("textarea[name=add_info]").val()
			},
			success : callback,
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
			}
		}); 
	});
	function callback(data) {
		if( data.trim() == 1 )
			alert('글 작성 성공');
		else 
			alert('글 작성 실패');
		location.href="<%=request.getContextPath()%>/carpool/list_carpool.do	";
	}
	
	function checkForm() {
		var f = document.wForm;
		if (f.title.value == '') {
			alert('제목을 입력하세요');
			f.title.focus();
			return false;
		}

		return false;
	}
	
});
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b"></script>

<div align="center">
	<h3>카풀 등록</h3>

	<form name="cForm" method="post">
		<div class="input_wrap">
			<div>
				<span>글타입</span>
				운전자 : <input type="radio" value="driver" name="post_type" checked="checked">
				탑승자 : <input type="radio" value="rider" name="post_type">
			</div>
		</div>
		<div class="input_wrap">
			<div>
			<span>흡연여부</span>
			O <input type="radio" value="0" name="smoke" checked="checked">
			X <input type="radio" value="1" name="smoke">
			</div>
		</div>
		<div>
			<input type="hidden" name="writer_id" value="${ login_result.id }">
		</div>
		<div class="input_wrap">
			<span>출발일시</span>
			<label for="start_date">출발날짜</label>
			<!-- start_date  -->
			<input type="text" id="start_date" name="start_date"> 
			<label for="start_time">출발날짜</label>
			<!-- start_time  -->
			<input type="time" id="start_time" name="start_time" min="9:00" max="18:00"> 
		</div>
		<div class="input_wrap">
			<label for="start_place">출발지</label>
			<!-- start_place  -->
			<div id="start_place"></div>
			<input type="hidden" name="start_place_name" id="start_place_name" class="input_place_name">
		</div>
		<div class="input_wrap">
			<label for="end_place">도착지</label>
			<!-- end_place  -->
			<div id="end_place"></div>
			<input type="hidden" name="end_place_name" id="end_place_name" class="input_place_name">
		</div>
		<div class="input_wrap">
			<label for="money">금액</label>
			<!-- money  -->
			<input type="text" name="money">
		</div>
		<div class="input_wrap">
			<label for="money">탑승자수</label>
			<!-- user_cnt  -->
			<input type="text" name="user_cnt">
		</div>
		<div class="input_wrap">
			<label for="add_info">추가정보</label>
			<!-- add_info  -->
			<textarea rows="7" cols="20" name="add_info" style="width: 100%;"></textarea>
		</div>
		<div id="map" style="width:100%;height:350px;"></div>
		<input type="submit" name="new_post" value="등록"> 
		<input type="button" name="post_list" value="목록">
	</form>
</div>
