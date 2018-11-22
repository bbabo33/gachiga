package kr.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;

public class NaverLogin_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/jsp/login/naver_callback.jsp";
	}
}
