package kr.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ReviewDAO;


public class Delete_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));

		ReviewDAO dao = new ReviewDAO();

		int cnt = dao.deleteBoard(no);

		request.setAttribute("cnt", cnt);

		return "/jsp/boardReview/delete_board.jsp";
	}

}
