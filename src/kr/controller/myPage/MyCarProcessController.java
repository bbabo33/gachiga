package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.controller.Controller;
import kr.dao.CarDAO;
import kr.vo.CarVO;
import kr.vo.MemberVO;

public class MyCarProcessController implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberVO user = new MemberVO();
		HttpSession session = request.getSession();
		user = (MemberVO) session.getAttribute("login_result");

		request.setCharacterEncoding("utf-8");
		String carName = request.getParameter("carName");
		String carNo = request.getParameter("carNo");
		String smoke = request.getParameter("smoking");
		if (smoke.equals("smoker")) {
			smoke = "흡연";
		} else if (smoke.equals("nonSmoker")) {
			smoke = "금연";
		}

		int rideNo = Integer.parseInt(request.getParameter("rideNo"));
		String direct = request.getParameter("direct");

		if (direct.equals("none")) {
			direct = "미가입";
		} else if (direct.equals("joined")) {
			direct = "가입";
		} else {
			direct = "모름";
		}

		CarVO car = new CarVO();
		CarDAO dao = new CarDAO();
		car.setCarName(carName);
		car.setCarNo(carNo);
		car.setDirect(direct);
		car.setSmoke(smoke);
		car.setrideNo(rideNo);
		car.setId(user.getId());

		dao.insertCar(car);
		return "redirect:" + request.getContextPath() + "/myPage/MyCar.do";

	}
}