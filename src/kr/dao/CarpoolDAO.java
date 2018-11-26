package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.util.ConnectionFactory;
import kr.util.JDBCClose;
import kr.vo.CarpoolVO;

public class CarpoolDAO {

	/**
	 * 최신글 5개
	 * 
	 * @param postNo
	 * @param cnt
	 */

	public List<CarpoolVO> selectFive() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<CarpoolVO> newList = new ArrayList<>();
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from ( ");
			sql.append(" select rownum as rnum, c.* from ( ");
			sql.append(" select writer_id, start_place_name, end_place_name, post_type , user_cnt, start_time, no ");
			sql.append(" from ( select * from c_carpool_post order by reg_date desc) ");
			sql.append(" ) c ");
			sql.append(" )where rnum between 1 and 5 ");
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CarpoolVO carpool = new CarpoolVO();
				carpool.setWriter_id(rs.getString("writer_id"));
				carpool.setStart_place_name(rs.getString("start_place_name"));
				carpool.setEnd_place_name(rs.getString("end_place_name"));
				carpool.setPost_type(rs.getString("post_type"));
				carpool.setUser_cnt(rs.getInt("user_cnt"));
				carpool.setStart_time(rs.getString("start_time"));
				System.out.println(rs.getInt("no"));
				carpool.setNo(rs.getInt("no"));
				
				newList.add(carpool);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return newList;

	}

	// 예약자 수 처리
	public void CountApply(int postNo, int cnt) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update c_carpool_post ");
		if (cnt > 0) {
			sql.append(" set apply_cnt = apply_cnt + ? ");
		} else {
			sql.append(" set apply_cnt = apply_cnt - ? ");
		}
		sql.append(" where no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, Math.abs(cnt));
			pstmt.setInt(2, postNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 카풀 등록
	public int insertCarpoolPost(CarpoolVO post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(" insert into c_carpool_post (no, writer_id, start_date, ");
			sql.append(" start_place_name, start_time, end_place_name, ");
			sql.append(" smoke, money, user_cnt, post_type, add_info ) ");
			sql.append(" values (seq_c_carpool_post_no.nextval , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, post.getId());
			pstmt.setString(2, post.getStart_date());
			pstmt.setString(3, post.getStart_place_name());
			pstmt.setString(4, post.getStart_time());
			pstmt.setString(5, post.getEnd_place_name());
			pstmt.setString(6, post.getSmoke());
			pstmt.setInt(7, post.getMoney());
			pstmt.setInt(8, post.getUser_cnt());
			pstmt.setString(9, post.getPost_type());
			pstmt.setString(10, post.getAdd_info());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 회원이 등록한 카풀조회
	 * 
	 * @param id
	 * @return
	 */
	public List<CarpoolVO> selectById(String id) {

		List<CarpoolVO> carpoolList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(
				" select no, start_date, start_time, money, user_cnt, post_type, add_info, reg_date, smoke, start_place_name, end_place_name, apply_cnt ");
		sql.append(" from c_carpool_post ");
		sql.append(" where writer_id = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String startDate = rs.getString("start_date");
				String startTime = rs.getString("start_time");
				int money = rs.getInt("money");
				int userCnt = rs.getInt("user_cnt");
				String postType = rs.getString("post_type");
				String addInfo = rs.getString("add_info");
				String regDate = rs.getString("reg_date");
				String smoke = rs.getString("smoke");
				String startPlaceName = rs.getString("start_place_name");
				String endPlaceName = rs.getString("end_place_name");
				int applyCnt = rs.getInt("apply_cnt");

				CarpoolVO carpool = new CarpoolVO();
				carpool.setNo(no);
				carpool.setStart_date(startDate);
				carpool.setStart_time(startTime);
				carpool.setMoney(money);
				carpool.setUser_cnt(userCnt);
				carpool.setPost_type(postType);
				carpool.setAdd_info(addInfo);
				carpool.setReg_date(regDate);
				carpool.setSmoke(smoke);
				carpool.setStart_place_name(startPlaceName);
				carpool.setEnd_place_name(endPlaceName);
				carpool.setWriter_id(id);
				carpool.setApply_cnt(applyCnt);

				carpoolList.add(carpool);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return carpoolList;
	}

	/**
	 * 본인이 등록한 카풀글 번호리스트
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> selectBoardNO(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select no ");
		sql.append(" from c_carpool_post ");
		sql.append(" where writer_id = ? ");
		List<Integer> list = new ArrayList<>();
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public CarpoolVO selectByNo(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		CarpoolVO post = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			// sql.append(" to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from c_carpool_post ");
			sql.append(" where no = ? ");
			sql.append(" order by no desc ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			;
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				post = new CarpoolVO();
				post.setNo(rs.getInt("no"));
				post.setId(rs.getString("writer_id"));
				post.setPost_type(rs.getString("post_type"));
				post.setSmoke(rs.getString("smoke"));
				post.setStart_date(rs.getString("start_date"));
				post.setStart_time(rs.getString("start_time"));
				post.setStart_place_name(rs.getString("start_place_name"));
				post.setEnd_place_name(rs.getString("end_place_name"));
				post.setMoney(rs.getInt("money"));
				post.setUser_cnt(rs.getInt("user_cnt"));
				post.setApply_cnt(rs.getInt("apply_cnt"));
				post.setAdd_info(rs.getString("add_info"));
				post.setReg_date(rs.getString("reg_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return post;
	}

	// 모든 카풀목록
	public List<CarpoolVO> selectAllPost() {
		List<CarpoolVO> carpoolList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			// sql.append(" to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from c_carpool_post	 ");
			sql.append(" order by no desc ");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CarpoolVO post = new CarpoolVO();
				post.setNo(rs.getInt("no"));
				post.setId(rs.getString("writer_id"));
				post.setPost_type(rs.getString("post_type"));
				post.setSmoke(rs.getString("smoke"));
				post.setStart_date(rs.getString("start_date"));
				post.setStart_time(rs.getString("start_time"));
				post.setStart_place_name(rs.getString("start_place_name"));
				post.setEnd_place_name(rs.getString("end_place_name"));
				post.setMoney(rs.getInt("money"));
				post.setUser_cnt(rs.getInt("user_cnt"));
				post.setAdd_info(rs.getString("add_info"));
				post.setReg_date(rs.getString("reg_date"));
				carpoolList.add(post);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return carpoolList;
	}
	
	// 카풀 삭제
	public int deleteCarpool(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from c_carpool_post ");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			;
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return result;
	}

	// 페이징
	public List<CarpoolVO> getPage( int pageNo ) {
		List<CarpoolVO> carpoolList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select *  ");
		sql.append(" from ( select rowNum as rnum, c.* ");
		sql.append(" from ( select no, writer_id, start_place_name, end_place_name, post_type, user_cnt, apply_cnt, start_date ");

		sql.append(" from c_carpool_post order by no desc) c )  ");
		sql.append(" where rnum between ? and ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, (pageNo * 5) - 4);
			pstmt.setInt(2, pageNo * 5);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CarpoolVO post = new CarpoolVO();
				post.setNo(rs.getInt("no"));
				post.setWriter_id(rs.getString("writer_id"));
				post.setPost_type(rs.getString("post_type"));
				post.setStart_date(rs.getString("start_date"));
				post.setStart_place_name(rs.getString("start_place_name"));
				post.setEnd_place_name(rs.getString("end_place_name"));
				post.setUser_cnt(rs.getInt("user_cnt"));
				post.setApply_cnt(rs.getInt("apply_cnt"));
				carpoolList.add(post);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return carpoolList;
	}

	// 페이징위한 모든 글 수
	public int cntAllRows() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) as count from c_carpool_post ");

		int cntRows = 0;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cntRows = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cntRows;
	}

	// 글 검색 페이징
	public List<CarpoolVO> searchBoard(int pageNo, String category, String word) {
		List<CarpoolVO> searchedList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select *  ");
			sql.append(" from ( select rowNum as rnum, c.* ");
			sql.append(" from ( select no, writer_id, start_place_name, end_place_name, post_type, user_cnt, apply_cnt, start_date ");

			sql.append(" from c_carpool_post  ");
			if (category.equals("rname")) { // 작성자
				sql.append(" where writer_id like ? ");
			} else if (category.equals("rstart")) { // 출발지
				sql.append(" where start_place_name like ? ");
			} else if (category.equals("rend")) { // 도착지
				sql.append(" where end_place_name like ?");
			}

			sql.append(" order by no desc) c )  ");
			sql.append(" where rnum between ? and ? "); //

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + word + "%");
			pstmt.setInt(2, (pageNo * 5) - 4);
			pstmt.setInt(3, pageNo * 5);

			System.out.println("카풀검색 :" + category + "," + word);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CarpoolVO post = new CarpoolVO();
				post.setNo(rs.getInt("no"));
				post.setWriter_id(rs.getString("writer_id"));
				post.setPost_type(rs.getString("post_type"));
				post.setStart_date(rs.getString("start_date"));
				post.setStart_place_name(rs.getString("start_place_name"));
				post.setEnd_place_name(rs.getString("end_place_name"));
				post.setUser_cnt(rs.getInt("user_cnt"));
				post.setApply_cnt(rs.getInt("apply_cnt"));
				searchedList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return searchedList;
	}

	// 검색 페이징위한 모든 글수
	public int cntSearchAllRows(String category, String word) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) as count from c_carpool_post ");

		if (category.equals("rname")) { // 작성자
			sql.append(" where writer_id like ? ");
		} else if (category.equals("rstart")) { // 출발지
			sql.append(" where start_place_name like ? ");
		} else if (category.equals("rend")) { // 도착지
			sql.append(" where end_place_name like ?");
		}

		int cntRows = 0;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, "%" + word + "%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cntRows = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cntRows;
	}
}
