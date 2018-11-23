package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class MyProfileController implements Controller {
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("!!!");
		MemberVO user = new MemberVO();
		HttpSession session = request.getSession();
		user = (MemberVO) session.getAttribute("login_result");
		String id = user.getId();

		MemberDAO dao = new MemberDAO();
		MemberVO member = dao.selectById(id);

		request.setAttribute("member", member);

		return "/page/myPage/myProfile.jsp";
	}

}