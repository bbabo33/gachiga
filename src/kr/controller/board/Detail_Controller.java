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
		// 게시글 내용 가져오기

		int boardno = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.selectByNo(boardno);
		System.out.println(board.toString());

		request.setAttribute("board", board);

		// 댓글 내용 가져오기
		List<CommentVO> commentList = dao.selectAllComment();
		request.setAttribute("commentList", commentList);

		return "/page/board/detail_board.jsp";
	}
}