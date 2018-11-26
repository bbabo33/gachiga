package kr.controller.apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ApplyDAO;
import kr.vo.ApplyVO;

public class CancelApply_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ApplyDAO dao = new ApplyDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		ApplyVO apply = dao.selectByNo(no);
		int result = dao.cancelApply(apply);
		
		request.setAttribute("cnt", result);
	
		return "/jsp/apply/cancel_apply.jsp";
	}

}
