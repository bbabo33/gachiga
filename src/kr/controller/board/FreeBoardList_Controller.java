package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class FreeBoardList_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		/*
		 * BoardDAO dao = new BoardDAO(); List<BoardVO> BoardList =
		 * dao.selectAllBoard();
		 * 
		 * request.setAttribute("boardList", BoardList);
		 * request.setAttribute("boardList", BoardList);
		 */

		BoardDAO dao = new BoardDAO();
		int start = 1;
		int allRows = dao.cntAllRows(); // workign ok
		int end = 0;
		System.out.println("paging line20 allrows:" + allRows);

		if (allRows % 5 != 0) {
			end = (allRows / 5) + 1;

		} else {
			end = allRows / 5;
		}

		request.setAttribute("start", start);
		request.setAttribute("end", end);

		String pageData = request.getParameter("pageNo");
		System.out.println(pageData);
		int pageNo = (pageData != null) ? Integer.parseInt(pageData) : 1;

		List<BoardVO> boardList = dao.getPage(pageNo);

		request.setAttribute("boardList", boardList);

		return "/page/board/free_list.jsp";
	}

}
