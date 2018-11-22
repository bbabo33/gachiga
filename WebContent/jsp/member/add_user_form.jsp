<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/carpool/assets/css/add_user_form.css">
<script src="/carpool/assets/js/httpRequest.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var is_idCheck = false;
		
		//회원가입
		$("form[name=signform]").submit(function(e) {
			if( !sign_check() )
				return false;
			e.preventDefault();
			$.ajax({
				url : '<%=request.getContextPath()%>/member/add_user.do',
				type : 'post',
				data : {
					'id' : $("input[name=id]").val(),
					'password' : $("input[name=password]").val(),
					'name' : $("input[name=name]").val(),
					'email' : $("input[name=email]").val(),
					'birth' : $("input[name=birth]").val(),
					'tel1' : $("input[name=tel1]").val(),
					'age' : $("input[name=age]").val(),
					'addr' : $("input[name=addr]").val(),
				},
				success : function(data){
					if(data == 1){
						alert("회원가입을 성공했습니다");
						location.href="<%=request.getContextPath()%>";
					} else {
						alert("회원가입을 실패했습니다");
						location.href="<%=request.getContextPath()%>/member/add_user_form.do";
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
		});
		
		$("input[name=idCheck]").click(function(){
			idCheck();
		});
		
		//중복체크
		function idCheck(){
			var id = document.signform.id;
			$.ajax({
				url : '<%=request.getContextPath()%>/member/check_user.do',
				type : 'post',
				data : {
					'id' : id.value
				},
				success : function(data){
					if(data == 1){
						$("div#id span").html("중복된 아이디입니다");
						is_idCheck = false;
					} else {
						$("div#id span").html("사용가능한 아이디입니다");
						is_idCheck = true;
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			});
		}
				
		// 입력체크
		function sign_check() {
			var is_ok = true;

			//아이디
			if (!check_value("id", true, 20) || !is_idCheck)
				is_ok = false;
			//이름
			if (!check_value("name", true, 20))
				is_ok = false;
			//비밀번호
			if (!check_value("password", true, 20))
				is_ok = false;

			//이메일
			if (!check_value("email", false, 20))
				is_ok = false;
			//생일
			if (!check_value("birth", false, 10))
				is_ok = false;
			//전화번호
			if (!check_value("tel1", false, 11))
				is_ok = false;
			// 나이
			if (!check_value("age", false, 3))
				is_ok = false;
			
			//기본주소
			if (!check_value("basic_addr", false, 200))
				is_ok = false;

			if (is_ok) {
				return is_ok;
			} else {
				return is_ok;
			}
		};

		function check_value(type, required, leng) {
			var value = $("form input[name=" + type + "]").val();
			var error_msg = $("form div#" + type + " span");

			if (!value && required) {
				switch (type) {
				case "id":
					$(error_msg).text('아이디를 입력하세요');
					break;
				case "password":
					$(error_msg).text('비밀번호를 입력하세요');
					break;
				case "name":
					$(error_msg).text('이름을 입력하세요');
					break;
				}
				return false;
			} else if (value.length > leng) {
				$(error_msg).text(leng + "자 이하로 입력해주세요");
				return false;
			} else {
				if(type=="id" && !is_idCheck){
					$(error_msg).text("중복체크를 진행해주세요");
					return false;
				}
					
				$(error_msg).text('');
			}
			return true;
		};
	});
</script>
<hr>
<div id="add_user_form">
	<h3>회원가입</h3>
	<form action="/carpool/jsp/member/add_user.jsp" name="signform"	method="post">
		<div id="id">
			<label for="id">* 아이디 : </label><input type="text" name="id">
			<input type="button" value="중복체크" name="idCheck">
			<span class="error_msg"></span><br>
		</div>
		<div id="name">
			<label for="name">* 이름 : </label><input type="text" name="name">
			<span class="error_msg"></span><br>
		</div>

		<div id="password">
			<label for="password">* 비밀번호 : </label>
			<input type="password" name="password">
			<span class="error_msg"></span><br>
		</div>

		<div id="email">
			<label for="email">이메일 : </label><input type="email" name="email"
				placeholder="xxx@xxx.com" pattern="{30}@{20}" /><br> <span
				class="error_msg"></span><br>
		</div>

		<div>
			<label for="age">나이 : </label><input type="text" name="age">
			<br> <span class="error_msg"></span><br>
		</div>

		<div>
			<label for="birth">생년월일 : </label><input type="text"
				placeholder=" -없이 6자리를 입력해주세요" name="birth"> <br> <span
				class="error_msg"></span><br>
		</div>

		<div id="tel">
			<label for="tel1">전화번호 : </label> <input type="text" name="tel1"
				placeholder="-없이 입력해주세요"> <br>
			<span class="error_msg"></span><br>
		</div>

		<div id="basic_addr">
			<label for="basic_addr">주소 : </label><input type="text"
				name="basic_addr"><br> <span class="error_msg"></span><br>
		</div>


		<input type="submit" value="회원가입" name="submit">
	</form>
</div>