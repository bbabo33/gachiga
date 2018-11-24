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
		
		int boardno = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		System.out.println(boardno+":detail-con line 22");
		String back_url = request.getHeader("referer"); 
		
		if (back_url.indexOf("board/update_board_form") == -1){
			dao.updateViewCnt(boardno);
		}
			
		BoardVO board = dao.selectByNo(boardno);
		
		request.setAttribute("no", boardno);
		request.setAttribute("board", board);
		System.out.println(board.toString()+":detail-con line 33");
		
		//댓글 내용 가져오기

		List<CommentVO> commentList = dao.selectAllComment();
		request.setAttribute("commentList", commentList);
		
		
		return "/page/board/detail_board.jsp";
	}
}
