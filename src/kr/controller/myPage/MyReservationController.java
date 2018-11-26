package kr.controller.myPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Controller;
import kr.dao.ApplyDAO;
import kr.dao.CarpoolDAO;
import kr.vo.CarpoolVO;
import kr.vo.MemberVO;

public class MyReservationController implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO user = new MemberVO();
		user = (MemberVO)session.getAttribute("login_result"); //�꽭�뀡 �쁺�뿭�뿉 �엳�뜕 login_result瑜�  user�뿉 �떞�븘以��떎
		if (user == null) {
 			return "redirect:" + request.getContextPath();
		}
		CarpoolDAO dao = new CarpoolDAO();

		List<CarpoolVO> carpoolList = new ArrayList<>();
		carpoolList = dao.selectById(user.getId()); // 본인글들
		
		
		List<Integer> noList = new ArrayList<>();
		noList = dao.selectBoardNO(user.getId()); //본인이 등록한 카풀글 번호리스트
		
		ApplyDAO aDao = new ApplyDAO();
		
		Map<Integer, List<MemberVO>> reservList = new HashMap<>(); 
		reservList = aDao.selectReserv(noList); 
		
		request.setAttribute("carpoolList", carpoolList); 
		request.setAttribute("reservList", reservList); //由ы�섏뒪�듃 �쁺�뿭�뿉 map�씤 reservList �벑濡�

		System.out.println("reserv"+reservList);
		return "/page/myPage/myReservation.jsp";
	}

}
