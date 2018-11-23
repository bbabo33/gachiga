package kr.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class AddUser_Controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberVO user = new MemberVO();
		
		String id = request.getParameter("id");
		String name =request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String birth = request.getParameter("birth");
		String tel = request.getParameter("tel1");
		String addr = request.getParameter("basic_addr");
		
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setAge(age);
		user.setBirth(birth);
		user.setTel(tel);
		user.setAddr(addr);
		
		int i = dao.insertMember(user);
		
		request.getSession().setAttribute("login_result", user);				
		request.setAttribute("cnt", i);
		
		return "/jsp/member/add_user.jsp";
	}

}
