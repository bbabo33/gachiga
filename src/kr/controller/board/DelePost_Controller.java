package kr.controller.board;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardFileVO;

public class DelePost_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		int no = request.getParameter("no") == null ? 0 : Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = new BoardDAO();

		int cnt = dao.deleteBoard(no);
		
		request.setAttribute("cnt", cnt);
		
		return "/page/board/list_post.jsp";
	}

}
