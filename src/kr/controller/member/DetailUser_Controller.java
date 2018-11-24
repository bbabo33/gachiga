package kr.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Controller;
import kr.dao.BoardDAO;
import kr.vo.BoardVO;

public class DetailUser_Controller implements Controller{
	
	@Override
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no")); // �Խù� ��ȣ
		BoardDAO dao = new BoardDAO();

		/*String type = request.getParameter("type");
		 if(! type.equals("edit"))
			dao.viewBoard(no); */
		
		/*String back_url = request.getHeader("REFERER"); // ����url�� ������������ �ƴϸ� ��ȸ�� ��
		System.out.println(back_url);
		if (back_url.indexOf("board/edit_post") == -1)
			dao.viewBoard(no);*/

		BoardVO board = dao.selectByNo(no); // ���� �Խù� 
		/*List<BoardFileVO> fileList = dao.selectFileList(no);
		
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);*/
		
		return "/jsp/login/logout.jsp";
	}

}
