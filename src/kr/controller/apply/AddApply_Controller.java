package kr.controller.apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ApplyDAO;
import kr.vo.ApplyVO;

public class AddApply_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ApplyDAO dao = new ApplyDAO();
		ApplyVO apply = new ApplyVO();
		
		apply.setId(request.getParameter("id"));
		apply.setUser_cnt(Integer.parseInt(request.getParameter("user_cnt")));
		apply.setPost_no(Integer.parseInt(request.getParameter("post_no")));
		apply.setWriter_id(request.getParameter("writer"));
		
		int result = dao.insertApply(apply);
		
		request.setAttribute("cnt", result);
	
		return "/jsp/apply/add_apply.jsp";
	}

}