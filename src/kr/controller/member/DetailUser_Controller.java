package kr.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardFileVO;
import kr.vo.BoardVO;

public class DetailUser_Controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no")); // 게시물 번호
		BoardDAO dao = new BoardDAO();

		/*String type = request.getParameter("type");
		 if(! type.equals("edit"))
			dao.viewBoard(no); */
		
		/*String back_url = request.getHeader("REFERER"); // 이전url이 수정페이지가 아니면 조회수 업
		System.out.println(back_url);
		if (back_url.indexOf("board/edit_post") == -1)
			dao.viewBoard(no);*/

		BoardVO board = dao.selectByNo(no); // 현재 게시물 
		List<BoardFileVO> fileList = dao.selectFileList(no);
		
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);
		
		return "/jsp/login/logout.jsp";
	}

}
