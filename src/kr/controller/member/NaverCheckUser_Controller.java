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

public class NaverCheckUser_Controller implements Controller{
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String token = request.getParameter("token");// �꽕�씠踰� 濡쒓렇�씤 �젒洹� �넗�겙;
		
		String header = "Bearer " + token; // Bearer �떎�쓬�뿉 怨듬갚 異붽�

		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // �젙�긽 �샇異�
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // �뿉�윭 諛쒖깮
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer msg = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				msg.append(inputLine);
			}
			br.close();

			Object obj = msg.toString();

			JSONParser json = new JSONParser();

			JSONObject jsonObj = (JSONObject) json.parse(obj.toString());
			JSONObject resp = (JSONObject) jsonObj.get("response");

			MemberDAO dao = new MemberDAO();

			MemberVO NaverUser = dao.selectByNidForNaver((String) resp.get("id"));
			
			if (NaverUser == null ) { // 회원가입ㄱ
				request.setAttribute("value", 1);

			} else { // 바로 로그인 ㄱ
				request.setAttribute("value", 0);
				request.getSession().setAttribute("login_result", NaverUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/login/naver_login_callback.jsp";
	}

}
