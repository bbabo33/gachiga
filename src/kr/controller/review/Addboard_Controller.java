package kr.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class Addboard_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		BoardVO board = new BoardVO();

		board.setTitle(request.getParameter("title"));
		board.setId(request.getParameter("id"));
		board.setContent(request.getParameter("content"));

		int cnt = dao.insertBoard(board);
		request.setAttribute("cnt", cnt);
		
		return "/jsp/boardReview/add_board.jsp";
	}

}
