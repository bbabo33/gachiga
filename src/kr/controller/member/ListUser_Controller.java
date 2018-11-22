package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.MemberDAO;

public class ListUser_Controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO dao = new MemberDAO();

		request.setAttribute("memberList", dao.selectAllMember());
		
		return "/page/member/list_user.jsp";
	}

}
