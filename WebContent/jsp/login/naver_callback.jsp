<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/carpool/assets/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript">
	var naver_id_login = new naver_id_login("vn1YKe4V6kRs4kKBMhb0",
			"http://localhost:8888/carpool/login/naver_login.do");
	// 네이버 로그인 후 이동할 페이지 
	//alert(naver_id_login.oauthParams.access_token);
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function

	function naverSignInCallback() {

		var name = naver_id_login.getProfileData('name');
		var gender = naver_id_login.getProfileData('gender');
		var email = naver_id_login.getProfileData('email');
		var id = naver_id_login.getProfileData('id');
		var loginId = email.substring(0, email.indexOf('@'));
		var opener = window.opener;

		opener.sessionStorage.setItem('token',
				naver_id_login.oauthParams.access_token);

		opener.location.href = "http://localhost:8888/carpool/member/add_user_form.do";
		window.close();

	}

</script>