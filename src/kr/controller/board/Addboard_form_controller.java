package kr.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;

public class Addboard_form_controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/page/board/add_board.jsp";
	}

}
