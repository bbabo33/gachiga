package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.controller.Controller;
import kr.dao.CarDAO;
import kr.vo.CarVO;
import kr.vo.MemberVO;

public class MyCarController implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberVO user = new MemberVO();
		user = (MemberVO) request.getSession().getAttribute("login_result");
		// 차량 등록 여부를 확인하기 위해 세션 영역에 등록된 user를 가져옴
		CarVO car = new CarVO();
		CarDAO dao = new CarDAO();
		car = dao.selectByID(user.getId());

		request.setAttribute("car", car);//리퀘스트 영역에 등록함
		return "/page/myPage/myCar.jsp";
	}
}