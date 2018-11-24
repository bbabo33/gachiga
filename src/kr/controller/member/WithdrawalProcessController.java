package kr.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.CarDAO;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class WithdrawalProcessController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO)session.getAttribute("login_result");
		CarDAO cardao = new CarDAO();
		cardao.deleteCar(user.getId());
		MemberDAO dao = new MemberDAO();
		int delete = dao.dele_user(user.getId());
		String msg="";
		if(delete==1) {
			 msg="회원탈퇴되셨습니다.";
		}
		request.setAttribute("msg", msg);
		session.invalidate();
		return "/jsp/myPage/withdrawalProcess.jsp";
	}

}
