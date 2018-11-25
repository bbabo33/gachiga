package kr.controller.carpool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.vo.BoardVO;
import kr.vo.CarpoolVO;

public class ListCarpool_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		CarpoolDAO dao = new CarpoolDAO();
		
		int allRows=dao.cntAllRows();	// 모든 컬럼수
		int start=1;
		int end=0;

		if(allRows%5 !=0){
			end = (allRows/5)+1;
		}else{
			end = allRows/5;
		}
				
		String pageData = request.getParameter("pageNo");
		int pageNo = (pageData != null) ? Integer.parseInt(pageData) : 1;
		
		List<CarpoolVO> carpool_list = dao.getPage(pageNo);
		
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("carpool_list", carpool_list);
		
		return "/page/carpool/list_carpool.jsp";
	}

}
