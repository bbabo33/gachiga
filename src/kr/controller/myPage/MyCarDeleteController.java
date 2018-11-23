package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.CarDAO;
import kr.vo.MemberVO;

public class MyCarDeleteController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO)session.getAttribute("login_result");
		
		CarDAO dao = new CarDAO();
		int delete = dao.deleteCar(user.getId());
		String msg="";
		if(delete==1) {
			msg="등록된 차량이 삭제되었습니다.";
		}
		request.setAttribute("carDeleteMsg", msg);
		
		return "/jsp/myPage/myCarDelete.jsp";
	}

}
