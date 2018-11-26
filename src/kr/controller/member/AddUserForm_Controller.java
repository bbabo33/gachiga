package kr.controller.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.controller.Controller;
import kr.dao.MemberDAO;
import kr.vo.MemberVO;

public class AddUserForm_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");

		if (request.getParameter("is_naver") != null) { // 네이버 로그인한경우
			request.setAttribute("is_naver", 1);
		} else {
			request.setAttribute("is_naver", 0);
		}

		return "/page/member/add_user.jsp";
	}

}
