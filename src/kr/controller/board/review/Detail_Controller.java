package kr.controller.board.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ReviewDAO;
import kr.vo.BoardVO;

public class Detail_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int boardno = Integer.parseInt(request.getParameter("no"));
		ReviewDAO dao = new ReviewDAO();
	
		String back_url = request.getHeader("referer"); 
		
		if (back_url.indexOf("boardReview/update_board_form") == -1){
		dao.updateViewCnt(boardno);
		}
		
		BoardVO board = dao.selectByNo(boardno);
		
		request.setAttribute("no", boardno);
		request.setAttribute("board", board);
		
		return "/page/boardReview/detail_board.jsp";
	}
}
