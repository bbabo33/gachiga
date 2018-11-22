package kr.controller.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.controller.Controller;

public class CheckUserNaver_Controller implements Controller {

	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			/*System.out.println(msg.toString());
			Object test = msg;*/
			request.setAttribute("json_response", setMsg(msg.toString()));

		} catch (Exception e) {
			System.out.println(e);
		}

		return "/jsp/login/naver_callback.jsp";
	}

	public String setMsg(String msg) {

		JSONParser jsonParser = new JSONParser();
		JSONArray jarray = null;
		String name="";
		try {
			JSONObject jsonObj = (JSONObject)jsonParser.parse(msg);
			
			JSONObject response = (JSONObject) jsonObj.get("response");
			System.out.println(response.get("id"));
			name = (String) response.get("name");
            
		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}

}
