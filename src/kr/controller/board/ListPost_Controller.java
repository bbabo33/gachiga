package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class ListPost_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		BoardDAO dao = new BoardDAO();

		// ∆‰¿Ã¬°
		List<BoardVO> allBoardList = dao.selectAllBoard();

		String r_page = request.getParameter("page");

		int _page = r_page == null ? 1 : Integer.parseInt(r_page);
		int post_size = 5;
		int start = 1 + (_page - 1) * post_size; // 1:1 2:6
		int end = _page * post_size; // 1:5 2:10

		List<BoardVO> BoardList = dao.selectBoardList(start, end);

		int step = (int) Math.ceil((double) allBoardList.size() / post_size);
		
		//√∑∫Œ∆ƒ¿œ «•Ω√
		List<Integer> BoardNoList = dao.selectAllFileList();
		
		request.setAttribute("step", step);
		request.setAttribute("page", _page);
		request.setAttribute("boardList", BoardList);
		request.setAttribute("boardNoList", BoardNoList);
		
		return "/page/board/list_post.jsp";
	}

}
