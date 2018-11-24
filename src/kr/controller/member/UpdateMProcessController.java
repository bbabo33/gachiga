package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class UpdateMProcessController implements Controller{

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO)session.getAttribute("login_result");
		
		MemberVO member = new MemberVO();
		member.setAddr(request.getParameter("addr"));
		member.setTel(request.getParameter("tel"));
		member.setId(user.getId());
		
		MemberDAO dao = new MemberDAO();
		int result = dao.updateUser(member);
		String msg ="";
		if(result==1) {
			msg = "회원정보가 수정되었습니다.";
		}else {
			msg="회원정보가 수정되지 못하였습니다.";
		}
		request.setAttribute("msg", msg);
		
		return "/jsp/myPage/updateProcess.jsp";
	}
	

}
