package kr.controller.carpool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.vo.BoardVO;
import kr.vo.CarpoolVO;

public class SearchCarpool_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		String word = request.getParameter("word");
		CarpoolDAO dao = new CarpoolDAO();

		int allRows = dao.cntSearchAllRows(category, word); // 검색 모든 결과수
		System.out.println("모든 컬럼수 :" + allRows);
		int start = 1;
		int end = 0;

		if (allRows % 5 != 0) { // 5개로 딱떨어질경우
			end = (allRows / 5) + 1;
		} else { 
			end = allRows / 5;
		}
		System.out.println("start :" + start + "end : " + end);
		String pageData = request.getParameter("pageNo");
		int pageNo = (pageData != null) ? Integer.parseInt(pageData) : 1;

		List<CarpoolVO> searchedList = dao.searchBoard(pageNo, category, word);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("category", category);
		request.setAttribute("word", word);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("searchedList", searchedList);
		System.out.println("검색결과 갯수 : " + searchedList.size());
		return "/page/carpool/search_carpool.jsp";
	}

}
