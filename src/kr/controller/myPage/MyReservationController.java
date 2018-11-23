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
		user = (MemberVO)session.getAttribute("login_result"); //세션 영역에 있던 login_result를  user에 담아준다
		
		CarpoolDAO dao = new CarpoolDAO();
//		CarpoolVO carpool = new CarpoolVO();
		List<CarpoolVO> carpoolList = new ArrayList<>(); //carpoolVO를 배열로 하는 arrayList 생성
		carpoolList = dao.selectById(user.getId());//유저의 아이디로 작성한 글의 내용을 담은 List 반환
		
		request.setAttribute("carpoolList", carpoolList); //리퀘스트 영역에 등록
		
		
		List<Integer> noList = new ArrayList<>();
		noList = dao.selectBoardNO(user.getId()); //유저의 아이디로 작성한 카풀글의 번호 배열을 가져옴
		
		ApplyDAO aDao = new ApplyDAO();
		
		Map<Integer, List<MemberVO>> reservList = new HashMap<>(); 
		reservList = aDao.selectReserv(noList); 
		
		//글번호에 따라 예약자의 정보를 담은 memberVO List를 받아서 글번호를 key로, memberVO List를 value로 하는 map객체 반환
		
		
		
		request.setAttribute("reservList", reservList); //리퀘스트 영역에 map인 reservList 등록
		
		
		
		return "/page/myPage/myReservation.jsp";
	}

}
