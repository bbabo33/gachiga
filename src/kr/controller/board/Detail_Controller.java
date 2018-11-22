package kr.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class Detail_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int boardno = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();

		BoardVO board = dao.selectByNo(boardno);
		
		request.setAttribute("board", board);
		return "/page/board/detail_board.jsp";
	}
}
