package kr.controller.carpool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.dao.MemberDAO;
import kr.vo.CarpoolVO;
import kr.vo.MemberVO;

public class DeleCarpool_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarpoolDAO cdao = new CarpoolDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		int cnt = cdao.deleteCarpool(no);
		
		request.setAttribute("cnt", cnt);
		
		return "/jsp/carpool/dele_carpool.jsp";
		
	}

}
