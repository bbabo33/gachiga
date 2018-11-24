package kr.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ApplyDAO;
import kr.vo.ApplyVO;

public class ReservationListController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int no = Integer.parseInt(request.getParameter("no"));
		ApplyVO apply = new ApplyVO();
		ApplyDAO dao = new ApplyDAO();
		
		
		return "/page/myPage/reservationList.jsp";
	}

}
