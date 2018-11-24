package kr.controller.review;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;

public class Addboard_form_controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/page/boardReview/add_board.jsp";
	}

}
