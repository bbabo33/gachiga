package kr.controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.dao.CarpoolDAO;
import kr.vo.BoardVO;
import kr.vo.CarpoolVO;

public class MainController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarpoolDAO cDao = new CarpoolDAO();
		List<CarpoolVO> cList = cDao.selectFive();
		request.setAttribute("cList", cList);
		
		// 자유 게시판
		BoardDAO bDao = new BoardDAO();
		List<BoardVO> bList = bDao.selectFive("free");
		request.setAttribute("bList", bList);
		
		//ReviewDAO rDao = new ReviewDAO();
		// 후기 게시판
		List<BoardVO> rList = bDao.selectFive("review");
		request.setAttribute("rList", rList);
		
		
		return "/page/main/main.jsp";
	}

}
