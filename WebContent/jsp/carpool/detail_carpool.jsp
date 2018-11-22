<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/detail.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=407280bcdba9bde46f3475b7659d876b"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("input").click(function(event){
		var name = $(event.target).attr("name");
		switch(name){
			case "carpool_dele":
				if( confirm("글을 삭제하시겠습니까?") )
					carpool_dele();
				break;
			case "carpool_list":
				location.href = "<%= request.getContextPath() %>/carpool/list_carpool.do";
				break;
			
		}
	});
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new daum.maps.Map(mapContainer, mapOption); 
	
	var start_marker = null;
	var end_marker = null;
	var linePath;
    var lineLine = new daum.maps.Polyline();
    var distance;
    var drawLine = null; // 선
    var bounds = null;
    var marker = new Array();
	
//    $("input.input_place_name").on('change', function(e){
	function set_map(place_name){
		var geocoder = new daum.maps.services.Geocoder();
	
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(place_name, function(result, status) {
				
		    // 정상적으로 검색이 완료됐으면 
		     if (status === daum.maps.services.Status.OK) {
		    	 
		    	var coords = new daum.maps.LatLng(result[0].y, result[0].x);

		        marker[marker.length] = new daum.maps.Marker({
		            map: map,
		            position: coords
		        });
				
				if( marker.length == 2 ){
					start_pos = marker[0].getPosition();
					end_pos = marker[1].getPosition();
					//라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
					linePath = [ start_pos, end_pos ];
					lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
		 
					if(drawLine != null)
						drawLine.setMap(null);
					
			        drawLine = new daum.maps.Polyline({
			            map : map, // 선을 표시할 지도입니다 
			            path : linePath,
			            strokeWeight : 3, // 선의 두께입니다 
			            strokeColor : '#db4040', // 선의 색깔입니다
			            strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			            strokeStyle : 'solid' // 선의 스타일입니다
			        });
			 
			        distance = Math.round(lineLine.getLength());
			        console.log(distance);
			        
			        bounds = new daum.maps.LatLngBounds();    
			        bounds.extend(start_pos);
			        bounds.extend(end_pos);
			        map.setBounds(bounds);
			     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        coords = new daum.maps.LatLng( (start_pos.getLat() + end_pos.getLat()) / 2,
			        		(start_pos.getLng() + end_pos.getLng()) / 2);
			        map.setCenter(coords);
				} else {
					map.setCenter(coords);
				}
		    } 
		});   
	}
	set_map('${carpool.start_place_name}');
	set_map('${carpool.end_place_name}');
	
	$("input[name=apply]").click(function(){
		var user_cnt = $("select[name=apply_user_cnt] option:selected").val();
		
		$.ajax({
			url : '<%=request.getContextPath()%>/apply/add_apply.do',
			type : 'post',
			data : {
				'id' : '${login_result.id}',
				'user_cnt' : user_cnt,
				'post_no' : '${carpool.no}',
				'writer' : '${carpool.writer_id}'
			},
			success : function(data){
				if(data.trim() == 1){
					alert("예약을 성공했습니다");
				}else{
					alert("예약을 실패했습니다");
				}
				location.reload();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	});
	function carpool_dele(){
		$.ajax({
			url : '<%=request.getContextPath()%>/carpool/dele_carpool.do',
			type : 'post',
			data : {
				'no' : '${carpool.no}',
			},
			success : function(data){
				if(data.trim() == 1){
					alert("글을 삭제했습니다");
					location.href = "<%= request.getContextPath() %>/carpool/list_carpool.do";
				}else{
					alert("글삭제에 실패했습니다");
					location.reload();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}
		});
	}
});
</script>
<div id="container">
	<h3>카풀 상세</h3>
	<div id="detail_header">
		<h4>카풀</h4>
		<h5>${ carpool.post_type == 'driver' ? '타세요' : '태워주세요' }</h5>
	</div>
	<div>
		<div class="wrap" id="detail_wrap">
	 		<c:if test="${ carpool != null }">
				<div class="row">
					<span class="cell">번호</span>
					<span class="cell">${ carpool.no }</span>
				</div>
				<div class="row">
					<span class="cell">출발이</span>
					<span class="cell">${ carpool.start_date }</span>
				</div>
				<div class="row">
					<span class="cell">출발지</span>
					<span class="cell"><c:out value="${ carpool.start_place_name }" /></span>
				</div>
	 			<div class="row">
					<span class="cell">도착지</span>
					<span class="cell">${ carpool.end_place_name }
					</span>
				</div>
				<div class="row">
					<span class="cell">흡연여부</span>
					<span class="cell"><c:out value="${ carpool.smoke }" /></span>
				</div>
				<div class="row">
					<span class="cell">탑승가능자수</span>
					<span class="cell"><c:out value="${carpool.user_cnt - carpool.apply_cnt}/${ carpool.user_cnt }" /></span>
				</div>
			</c:if>
		</div>
		<div class="wrap" id="apply_wrap">
			<div class="row">
				<!-- <span class="cell"></span> -->
				<img src="/carpool/assets/images/user_profile.jpg">
				<p>${ writer.name }</p>
				<!-- 예약가능한경우-->
				<c:if test="${ (carpool.user_cnt - carpool.apply_cnt) != 0 && login_result.id != carpool.writer_id}"> 
				<select name="apply_user_cnt">
				<c:forEach var = "i" begin = "1" end = "${carpool.user_cnt - carpool.apply_cnt}">>
					<option value="${i}">${i}명</option>
				</c:forEach>
				</select>
				<input type="button" value="예약하기" name="apply">	
				</c:if>
				<!-- 예약불가능한경우-->
				<c:if test="${ (carpool.user_cnt - carpool.apply_cnt) == 0 || login_result.id == carpool.writer_id}">
				<span>예약이 불가능합니다</span>
				</c:if>
				
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="map_wrap">
		<div id="map" style="width:100%;height:350px;"></div>
	</div>
	
	<div class="wrap" id="add_info">
		<span class="cell">추가 내용</span>
		<span class="cell">${ carpool.add_info }</span>
	</div>
	
	<div class="wrap" id="star_score">
		<span class="cell">별점</span>
		<span class="cell">★★★★★</span>
	</div>
	
	<c:if test="${ login_result.id == carpozol.writer_id }">
	<input class="btn" type="button" name="carpool_edit" value="수정"> 
	<input class="btn" type="button" name="carpool_dele" value="삭제">
	</c:if>
	<input class="btn" type="button" name="carpool_list" value="목록">
</div>