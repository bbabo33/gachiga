package kr.controller.myPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.ApplyDAO;
import kr.vo.ApplyVO;
import kr.vo.MemberVO;

public class SentReservController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO)session.getAttribute("login_result");
		if (user == null) {
 			return "redirect:" + request.getContextPath();
		}
		ApplyDAO dao = new ApplyDAO();
		List<ApplyVO> list = new ArrayList<>();
		
		list = dao.selectById(user.getId());
		request.setAttribute("applyList", list);
		
		return "/page/myPage/sentReserv.jsp";
	}

}
