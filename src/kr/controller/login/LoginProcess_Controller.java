package kr.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class LoginProcess_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println(id);
		System.out.println(password);
		
		MemberDAO dao = new MemberDAO();
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		
		MemberVO login_result = dao.selectForLogin(member);
		
		if( login_result != null){
			request.setAttribute("login_name", login_result.getId());

			HttpSession session = request.getSession();
			session.setAttribute("login_result", login_result);		
		} else {
			request.setAttribute("login_name", 0);
		}
		
		return "/jsp/login/loginProcess.jsp";
	}
}
