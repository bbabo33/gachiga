package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kr.util.ConnectionFactory;
import kr.vo.ApplyVO;
import kr.vo.CarVO;

public class ApplyDAO {

	public int insertApply(ApplyVO apply) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into c_apply(id, user_cnt, post_no, writer_id) ");
		sql.append(" values(?, ?, ?, ?) ");
		
		int result = 0;
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, apply.getId());
			pstmt.setInt(2, apply.getUser_cnt());
			pstmt.setInt(3, apply.getPost_no());
			pstmt.setString(4, apply.getWriter_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CarpoolDAO dao = new CarpoolDAO();
		dao.CountApply(apply.getPost_no(), apply.getUser_cnt());

		return result;
	}
}