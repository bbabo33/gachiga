package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.controller.Controller;

public class MyCarFormController implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/jsp/myPage/myCarForm.jsp";
	}
}