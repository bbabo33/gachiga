package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.vo.MemberVO;

public class MyCarFormController implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("login_result");
		if (user == null) {
			return "redirect:" + request.getContextPath();
		}
		return "/page/myPage/myCarForm.jsp";
	}
}