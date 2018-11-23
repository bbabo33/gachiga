package kr.controller.myPage;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 import kr.controller.Controller;
 public class MyPageMainController implements Controller {
 	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("login_result") != null) {
 			return "/page/myPage/myPageMain.jsp";
		}
		return "redirect:" + request.getContextPath();
	}
 }