package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class UpdateMFormController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO) session.getAttribute("login_result");
		MemberDAO dao = new MemberDAO();
		MemberVO member= new MemberVO();
		member = dao.selectById(user.getId());
		request.setAttribute("member", member);
		
		
		return "/page/myPage/updateForm.jsp";
	}

}
