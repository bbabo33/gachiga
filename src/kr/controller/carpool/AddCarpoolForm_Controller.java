package kr.controller.carpool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;

public class AddCarpoolForm_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/page/carpool/add_carpool.jsp";
	}

}
