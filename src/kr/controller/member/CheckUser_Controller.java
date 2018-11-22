package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class CheckUser_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDAO();
		MemberVO member = dao.selectById(id);
		
		if(member != null) {
			request.setAttribute("cnt", 1);
		}else {
			request.setAttribute("cnt", 0);
		}
		return "/jsp/member/checkUser.jsp";
	}

}
