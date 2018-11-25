package kr.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class Update_form_controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		String post_type = request.getParameter("post_type");
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVO board = dao.selectByNo(no);
		
		request.setAttribute("post_type", post_type);
		request.setAttribute("no", no);
		request.setAttribute("board", board);
		request.setAttribute("post_type", post_type);

		return "/page/board/update_board.jsp";
	}
}
