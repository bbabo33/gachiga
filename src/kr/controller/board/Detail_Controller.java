package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;
import kr.vo.CommentVO;

public class Detail_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = new BoardDAO();
		int boardno = Integer.parseInt(request.getParameter("no"));
		String post_type = request.getParameter("post_type");

		//조회수 처리
		String back_url = request.getHeader("referer"); 
		if (back_url.indexOf("board/update_board_form") == -1){
			dao.updateViewCnt(boardno);
		}
		BoardVO board = dao.selectByNo(boardno);
		// 댓글정보
		List<CommentVO> commentList = dao.selectAllComment(boardno);

		request.setAttribute("no", boardno);
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
		request.setAttribute("post_type", post_type);
		return "/page/board/detail_board.jsp";
	}
}
