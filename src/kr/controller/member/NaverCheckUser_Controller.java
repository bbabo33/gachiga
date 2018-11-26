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

		String token = request.getParameter("token");// 네이버 로그인 접근 토큰;
		
		String header = "Bearer " + token; // Bearer 다음에 공백 추가

		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
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
			
			if (NaverUser == null ) { // 로그인 실패
				String email = (String) resp.get("email");
				
				String naver_id = email.substring(0, email.indexOf("@"));
				request.getSession().setAttribute("naver_id", naver_id);
				request.getSession().setAttribute("Nid", resp.get("id"));
				request.getSession().setAttribute("gender", resp.get("gender"));
				request.getSession().setAttribute("name", resp.get("name"));
				request.getSession().setAttribute("email", email);
				
				request.setAttribute("value", 1);

			} else { // 로그인 성공
				request.setAttribute("value", 0);
				request.getSession().setAttribute("login_result", NaverUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/login/naver_login_callback.jsp";
	}

}
