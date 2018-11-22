package kr.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.HugiDAO;
import kr.vo.BoardVO;

public class HugiBoardList_Controller implements Controller {
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HugiDAO dao = new HugiDAO();
		List<BoardVO> HugiList = dao.selectAllBoard();
		
		request.setAttribute("hugiList", HugiList);
		return "/page/board/hugi_list.jsp";
	}

	
}
