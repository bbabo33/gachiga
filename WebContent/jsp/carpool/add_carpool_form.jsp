<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/add_carpool_form.css">
<script src="<%= request.getContextPath() %>/assets/js/place_search.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/add_carpool.js"></script>
<script>
$(document).ready(function(){
	$("form[name=cForm]").submit(function(e) {
		if( ! checkForm() )
			return false;
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
				'smoke' : $("select[name=smoke]").val(),
				'money' : $("input[name=money]").val(),
				'user_cnt' : $("input[name=user_cnt]").val(),
				'post_type' : $("select[name=post_type]").val(),
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
			alert('카풀이 등록되었습니다');
		else 
			alert('카풀 등록에 실패했습니다');
		location.href="<%=request.getContextPath()%>/carpool/list_carpool.do";
	}
	
    function sample6_execDaumPostcode(e) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }
                
                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $(e.target).parent().find("p").html(fullAddr);
                $(e.target).parent().find("input[type=hidden]").val(fullAddr);
                $(e.target).parent().find("input[type=hidden]").trigger('change');
            }
        }).open();
    }
	$("input[name=addr_search]").click(function(e){
		sample6_execDaumPostcode(e); 
	});
	
	function checkForm() {
		if ($("input[name=start_date]").val() == '' ) {
			alert('출발 날짜를 입력하세요');
			$("input[name=start_date]").focus();
			return false;
		}
		if ($("input[name=start_time]").val() == '' ) {
			alert('출발 시간를 입력하세요');
			$("input[name=start_time]").focus();
			return false;
		}
		if ($("input[name=start_place_name]").val() == '' ) {
			alert('출발지를 입력하세요');
			$("input#start_place_btn").click();
			return false;
		}
		if ($("input[name=end_place_name]").val() == '' ) {
			alert('도착지를 입력하세요');
			$("input#end_place_btn").click();
			return false;
		}
		if ($("input[name=money]").val() == '' ) {
			alert('카풀 금액을 입력하세요');
			$("input[name=money]").focus();
			return false;
		}
		if ($("input[name=user_cnt]").val() == '' ) {
			alert('카풀 이용 인원수를 입력하세요');
			$("input[name=user_cnt]").focus();
			return false;
		}
		return true;
	}
	
});
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b"></script>

<div align="center">
	<h3>카풀 등록</h3>
	<form name="cForm" method="post">
		<div id="form_wrap">
			<div class="input_wrap">
				<span class="cell cell1">유형</span>
				<div class="cell cell2">
					<p>카풀 유형을 선택하세요</p>
					<select name="post_type">
						<option value="driver">타세요</option>
						<option value="rider">태워주세요</option>
					</select>
				</div>
			</div>
			<div class="input_wrap">
				<span class="cell cell1">흡연여부</span>
				<div class="cell cell2">
					<p>흡연여부를 선택하세요</p>
					<select name="smoke" class="cell cell2">
						<option value="1">흡연</option>
						<option value="0">비흡연</option>
					</select>
				</div>
			</div>
			<div class="input_wrap cell" id="start_info">
				<span class="cell cell1">출발일시</span>
				<div class="cell cell2">
					<p>출발날짜와 시간을 선택해주세요</p>
					<img src="/carpool/assets/images/icon_cal.gif">
					<input type="text" id="start_date" name="start_date"> 
					<input type="time" id="start_time" name="start_time">
				</div> 
			</div>
			<div class="input_wrap">
				<span class="cell cell1">츨발지</span>
				<div class="cell cell2">
					<p>출발지를 선택해주세요</p>
					<input type="button" name="addr_search" value="주소검색" id="start_place_btn">
					<input type="hidden" name="start_place_name" id="start_place_name" class="input_place_name">
				</div>
			</div>
			<div class="input_wrap">
				<span class="cell cell1">도착지</span>
				<div class="cell cell2">
					<p>도착지를 선택해주세요</p>
					<input type="button" name="addr_search" value="주소검색" id="end_place_btn">
					<input type="hidden" name="end_place_name" id="end_place_name" class="input_place_name">
				</div>
			</div>
			<div class="input_wrap">
				<span class="cell cell1">금액</span>
				<div class="cell cell2">
					<p>금액을 입력해주세요</p>
					<input type="text" name="money">
				</div>
			</div>
			<div class="input_wrap">
				<span class="cell cell1">금액</span>
				<div class="cell cell2">
					<p>카풀 이용 인원수를 입력해주세요</p>
					<input type="text" name="user_cnt">
				</div>
			</div>
			<div class="input_wrap">
				<span class="cell cell1">추가정보</span>
				<div class="cell cell2">
					<textarea rows="7" cols="20" name="add_info" style="width: 100%;"></textarea>
				</div>
			</div>
			<input type="hidden" name="writer_id" value="${ login_result.id }">
			<input type="submit" name="new_post" value="등록"> 
			<input type="button" name="post_list" value="목록">
		</div>
	<div id="map_wrap">
		<div id="map" style="width:80%;height:400px;"></div>
	</div>
	</form>
</div>
