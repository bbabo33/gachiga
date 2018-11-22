<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naver_id_login"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->
<script type="text/javascript">
	var naver_id_login = new naver_id_login("f7aj3kWnxJf7Qzs_qweZ",	"http://127.0.0.1:8888/carpool/login/naver_login.do");
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white", 2, 40);
	naver_id_login.setDomain("http://127.0.0.1:8888/carpool");
	naver_id_login.setState(state);
	naver_id_login.setPopup();
	naver_id_login.init_naver_id_login();
</script>