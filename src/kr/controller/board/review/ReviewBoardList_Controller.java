package kr.controller.board.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.ReviewDAO;
import kr.vo.BoardVO;

public class ReviewBoardList_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReviewDAO dao = new ReviewDAO();
		List<BoardVO> ReviewList = dao.selectAllBoard();
		
		request.setAttribute("reviewList", ReviewList);
		return "/page/boardReview/review_list.jsp";
	}

	
}
