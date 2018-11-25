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
		request.setCharacterEncoding("utf-8");
		String post_type = request.getParameter("post_type");
		String category = request.getParameter("category");
		String word = request.getParameter("word");
		BoardDAO dao = new BoardDAO();

		int allRows = dao.cntSearchAllRows(post_type, category, word); // ÃÑ °Ô½Ã±Û¼ö
		System.out.println("°Ë»ö ÃÑ°¹¼ö :" + allRows);
		int start = 1;
		int end = 0;

		if (allRows % 5 != 0) { // 5°³ ´ÜÀ§ÀÏ°æ¿ì µü¶³¾îÁö°Ô
			end = (allRows / 5) + 1;
		} else { 
			end = allRows / 5;
		}
		System.out.println("start :" + start + "end : " + end);
		String pageData = request.getParameter("pageNo");
		int pageNo = (pageData != null) ? Integer.parseInt(pageData) : 1;

		List<BoardVO> searchedList = dao.searchBoard(pageNo, category, word, post_type);
		request.setAttribute("post_type", post_type);
		request.setAttribute("category", category);
		request.setAttribute("word", word);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("searchedList", searchedList);
		System.out.println("°Ë»ö °á°ú °¹¼ö : " + searchedList.size());
		return "/page/board/search_board.jsp";
	}

}
