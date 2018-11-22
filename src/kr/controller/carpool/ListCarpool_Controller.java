package kr.controller.carpool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.vo.CarpoolVO;

public class ListCarpool_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		CarpoolDAO dao = new CarpoolDAO();
		CarpoolVO post = new CarpoolVO();

		List<CarpoolVO> carpool_list = dao.selectAllPost();
		request.setAttribute("carpool_list", carpool_list);
		
		return "/page/carpool/list_carpool.jsp";
	}

}
