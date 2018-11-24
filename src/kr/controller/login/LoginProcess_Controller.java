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
		
		MemberDAO dao = new MemberDAO();
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		
		MemberVO login_result = dao.selectForLogin(member);
		
		if( login_result != null){
<<<<<<< HEAD
			msg = login_result.getId() + "님 환영합니다";
			
=======
			request.setAttribute("login_name", login_result.getId());

>>>>>>> branch 'master' of https://github.com/bbabo33/gachiga.git
			HttpSession session = request.getSession();
			session.setAttribute("login_result", login_result);		
		} else {
<<<<<<< HEAD
			msg = "다시 로그인하세요";
=======
			request.setAttribute("login_name", 0);
>>>>>>> branch 'master' of https://github.com/bbabo33/gachiga.git
		}
		
		return "/jsp/login/loginProcess.jsp";
	}
/*	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "redirect:" + request.getContextPath() + "/login/login.do";
	}
*/	
}
