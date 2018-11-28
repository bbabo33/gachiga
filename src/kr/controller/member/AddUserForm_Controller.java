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

public class AddUserForm_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		
		if (request.getParameter("is_naver") != null) { // 네이버로그인했을때
			request.setAttribute("is_naver", 1);
		} else {
			request.setAttribute("is_naver", 0);
		}

		if (token != null) {
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

//				MemberVO NaverUser = dao.selectById((String) resp.get("id"));

				if (resp.get("id") != null) { // 회원가입
					String email = (String) resp.get("email");
					String naver_id = email.substring(0, email.indexOf("@"));
					request.setAttribute("naver_id", naver_id);
					request.setAttribute("Nid", resp.get("id"));
					request.setAttribute("gender", resp.get("gender"));
					request.setAttribute("name", resp.get("name"));
					request.setAttribute("email", email);

					request.setAttribute("value", 1);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "/page/member/add_user.jsp";
	}

}
