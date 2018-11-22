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
		System.out.println("checkusercontrol line 26:" + token);
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

			System.out.println(resp.toJSONString());

			String YandD = (String) resp.get("birthday");
			String[] days = YandD.split("-");
			String birthday = days[0] + days[1];

			MemberDAO dao = new MemberDAO();

			MemberVO NaverUser = dao.selectById((String) resp.get("id"));

			if (NaverUser == null) {

				request.getSession().setAttribute("Nid", resp.get("id"));
				request.getSession().setAttribute("birthday", birthday);
				request.getSession().setAttribute("gender", resp.get("gender"));
				request.getSession().setAttribute("name", resp.get("name"));
				request.getSession().setAttribute("email", resp.get("email"));
				System.out.println("checkusercon line 75:naveruserNull");
				request.setAttribute("value", 1);

			} else {
				System.out.println("checkusercon line 78:naveruserNotNull");
				System.out.println("checkusercon line 78:" + NaverUser.toString());

				request.setAttribute("value", 0);
				request.getSession().setAttribute("login_result", NaverUser);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return "/jsp/login/naver_login_callback.jsp";
	}

}
