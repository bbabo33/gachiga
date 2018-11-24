package kr.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;

public class Update_form_controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("updateform con line 12:"+request.getParameter("no"));
		return "/page/board/update_board.jsp";
	}
}
