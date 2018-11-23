package kr.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.CommentVO;

public class AddComment_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String writer = request.getParameter("writer");
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		String content = request.getParameter("content");

		CommentVO comment = new CommentVO();
		comment.setWriter(writer);
		comment.setPost_no(post_no);
		comment.setContent(content);

		BoardDAO dao = new BoardDAO();
		int commentResult = dao.insertComment(comment);

		request.setAttribute("no", post_no); // 다시 디테일보드로 돌아가기 위해 지정*/
		request.setAttribute("commentResult", commentResult);

		return "/jsp/board/detail_board_commentResult.jsp";
	}
}