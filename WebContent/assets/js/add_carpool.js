/**
 * 글 작성 페이지
 */
$(document).ready(function() {
	$(function() {
		var dateToday = new Date(); 

	    $( "input#start_date" ).datepicker({
	    	dateFormat: 'yy.mm.dd',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	        dayNames: ['일','월','화','수','목','금','토'],
	        dayNamesShort: ['일','월','화','수','목','금','토'],
	        dayNamesMin: ['일','월','화','수','목','금','토'],
	        showMonthAfterYear: true,
	        changeMonth: true,
	        changeYear: true,
	        yearSuffix: '년',
	        minDate:dateToday
	    });
	});
	
	$("input[name=post_list]").click(function() {
		location.href = "/carpool/carpool/list_carpool.do";
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
	
	$("input.input_place_name").on('change', function(e){
		var geocoder = new daum.maps.services.Geocoder();
	
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch($(this).val(), function(result, status) {
				
		    // 정상적으로 검색이 완료됐으면 
		     if (status === daum.maps.services.Status.OK) {
		    	 
		    	var coords = new daum.maps.LatLng(result[0].y, result[0].x);

		    	if( $(e.target).attr('name') == 'start_place_name'){
					// 결과값으로 받은 위치를 마커로 표시합니다
					if(start_marker != null)
						start_marker.setMap(null);
			        start_marker = new daum.maps.Marker({
			            map: map,
			            position: coords
			        });
				}
				if( $(e.target).attr('name') == 'end_place_name'){
					// 결과값으로 받은 위치를 마커로 표시합니다
					if(end_marker != null)
						end_marker.setMap(null);
					end_marker = new daum.maps.Marker({
						map: map,
						position: coords
					});
				}
				
				if( start_marker != null && end_marker != null ){
					start_pos = start_marker.getPosition();
					end_pos = end_marker.getPosition();
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
			        coords = new daum.maps.LatLng( (start_pos.getLat() + end_pos.getLat()) / 2, (start_pos.getLng() + end_pos.getLng()) / 2)
			        map.setCenter(coords);
				} else {
					map.setCenter(coords);
				}
		    } 
		});     
	});
});
