package kr.controller.board.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class Update_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		BoardVO board = new BoardVO();
		BoardDAO dao = new BoardDAO();
		
		int no = Integer.parseInt(request.getParameter("board_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		board.setBoard_no(no);
		board.setTitle(title);
		board.setContent(content);
		int cnt = dao.updateBoard(board);
		
		request.setAttribute("cnt", cnt);
		return "/page/boardReview/update_board.jsp";
	}
	

}
