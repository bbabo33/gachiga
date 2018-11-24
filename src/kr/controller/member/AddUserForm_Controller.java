package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.MemberDAO;

public class AddUserForm_Controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String back_url = request.getHeader("referer");
		if( back_url.indexOf("login/naver_login.do") != -1) { //네이버 로그인한경우
			request.setAttribute("is_naver", 1);
		}else {
			request.setAttribute("is_naver", 0);
		}
		return "/page/member/add_user.jsp";
	}

}
