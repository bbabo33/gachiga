package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class SearchBoard_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("searchboard line13:"+request.getParameter("category"));
		System.out.println("searchboard line14:"+request.getParameter("word"));
		
		String category = request.getParameter("category");
		String word = request.getParameter("word");
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> searchedList = dao.searchBoard(category, word);
		for(BoardVO vo : searchedList) {
			System.out.println(":searchBoard line 26"+vo.toString());
		}
		request.setAttribute("searchedList", searchedList);
		return "/page/board/search_board.jsp";
	}

}
