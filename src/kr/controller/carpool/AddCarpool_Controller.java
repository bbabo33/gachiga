package kr.controller.carpool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.CarpoolDAO;
import kr.vo.CarpoolVO;

public class AddCarpool_Controller implements Controller{
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// carpool
		request.setCharacterEncoding("utf-8");
		CarpoolDAO dao = new CarpoolDAO();
		CarpoolVO post = new CarpoolVO();

		System.out.println(request.getParameter("smoke"));
		System.out.println(request.getParameter("start_date"));
		post.setId(request.getParameter("writer_id"));
		post.setStart_date(request.getParameter("start_date"));
		post.setStart_place_name(request.getParameter("start_place_name"));
		post.setStart_time(request.getParameter("start_time"));
		post.setEnd_place_name(request.getParameter("end_place_name"));
		post.setSmoke(request.getParameter("smoke"));
		post.setMoney(Integer.parseInt(request.getParameter("money")));
		post.setUser_cnt(Integer.parseInt(request.getParameter("user_cnt")));
		post.setPost_type(request.getParameter("post_type"));
		post.setAdd_info(request.getParameter("add_info"));

		int cnt = dao.insertCarpoolPost(post);
		request.setAttribute("cnt", cnt);
		
		return "/jsp/carpool/add_carpool.jsp";
	}

}
