package kr.controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.dao.CarpoolDAO;
import kr.dao.ReviewDAO;
import kr.vo.BoardVO;
import kr.vo.CarpoolVO;

public class MainController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarpoolDAO cDao = new CarpoolDAO();
		List<CarpoolVO> cList = cDao.selectFive();
		request.setAttribute("cList", cList);
		
		BoardDAO bDao = new BoardDAO();
		List<BoardVO> bList = bDao.selectFive();
		request.setAttribute("bList", bList);
		
		ReviewDAO rDao = new ReviewDAO();
		List<BoardVO> rList = rDao.selectFive();
		request.setAttribute("rList", rList);
		
		
		return "/page/main/main.jsp";
	}

}
