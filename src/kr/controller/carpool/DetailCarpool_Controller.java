package kr.controller.carpool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.dao.MemberDAO;
import kr.vo.CarpoolVO;
import kr.vo.MemberVO;

public class DetailCarpool_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarpoolDAO cdao = new CarpoolDAO();
		MemberDAO mdao = new MemberDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		CarpoolVO carpool = cdao.selectByNo(no);
		MemberVO writer = mdao.selectById(carpool.getId());
		
		
		request.setAttribute("carpool", carpool);
		request.setAttribute("writer", writer);
		
		return "/page/carpool/detail_carpool.jsp";
		
	}

}
