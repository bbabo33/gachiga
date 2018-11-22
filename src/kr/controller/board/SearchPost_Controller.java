package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class SearchPost_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		
		BoardDAO dao = new BoardDAO();
		
		//ÆäÀÌÂ¡
		List<BoardVO> allBoardList = dao.searchAllBoard(search, category);
		String r_page = request.getParameter("page");

		int _page = r_page == null ? 1 : Integer.parseInt(r_page);
		int post_size = 5;
		int start = 1 + (_page - 1) * post_size; // 1:1 2:6
		int end = _page * post_size; // 1:5 2:10

		List<BoardVO> BoardList = dao.searchBoardList(start, end, search, category);

		int step = (int) Math.ceil((double) allBoardList.size() / post_size);
		
		System.out.println(allBoardList.size());
		//º¯¼ö
		request.setAttribute("search", search);
		request.setAttribute("category", category);
		request.setAttribute("step", step);
		request.setAttribute("boardList", BoardList);
		
		return "/page/board/search_post.jsp";
	}

}
