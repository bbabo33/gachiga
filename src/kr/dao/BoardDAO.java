package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.util.ConnectionFactory;
import kr.util.JDBCClose;
import kr.vo.BoardVO;
import kr.vo.CommentVO;

public class BoardDAO {
	/**
	 * 모든 글
	 */
	public List<BoardVO> selectAllBoard(String post_type) {
		List<BoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select board_no, title, content, id, cnt, ");
			sql.append(" case when to_char(reg_date, 'yyyy-mm-dd') >= to_char(sysdate, 'yyyy-mm-dd')  ");
			sql.append(" then to_char(reg_date,'HH24.mi') ");
			sql.append(" else to_char(reg_date, 'yyyy.mm.dd') end reg_date, ");
			sql.append("  from c_board ");
			sql.append(" order by board_no desc ");
			sql.append(" where post_type = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, post_type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setRegDate(rs.getString("reg_date"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return boardList;
	}

	// 최신글 5개
	public List<BoardVO> selectFive(String post_type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BoardVO> newList = new ArrayList<>();
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from ( "); // 갯수 필터링
			sql.append(" select rownum as rnum, c.* from ( "); // 번호 매기기
			sql.append(" select board_no , title, id, reg_date "); //
			sql.append(" from ( select * from c_board where post_type = ? order by reg_date desc ) ");
			sql.append(" ) c ");
			sql.append(" )where rnum between 1 and 5 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, post_type);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setRegDate(rs.getString("reg_date"));

				newList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return newList;
	}

	/**
	 * 글 추가
	 * 
	 * @return
	 */
	public int insertBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into c_board( board_no, title, id, content, post_type ) ");
			sql.append(" values( seq_c_board_no.nextval, ?, ?, ?, ? ) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getPost_type());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return result;

	}

	/**
	 * 글 조회
	 */
	public BoardVO selectByNo(int boardNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVO board = null;
		ResultSet rs = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("  from c_board ");
			sql.append(" where board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setCnt(rs.getInt("cnt"));
				board.setRegDate(rs.getString("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return board;
	}

	/**
	 * 글 업데이트
	 */
	public int updateBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("update c_board ");
			sql.append(" set title = ?, content = ? ");
			sql.append(" where board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoard_no());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 조회수 업데이트
	 */
	public void updateViewCnt(int board_no) {

		StringBuilder sql = new StringBuilder();
		sql.append("update c_board ");
		sql.append(" set cnt = cnt + 1 ");
		sql.append(" where board_no = ? ");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 글 삭제
	 * 
	 * @return
	 */
	public int deleteBoard(int no) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from c_board ");
		sql.append(" where board_no = ?  ");

		int result = 0;
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int cntAllRows(String post_type) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) as count from c_board ");
		sql.append(" where post_type = ? ");

		int cntRows = 0;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, post_type);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cntRows = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cntRows;
	}

	// 페이징
	public List<BoardVO> getPage(int pageNo, String post_type) {
		List<BoardVO> boardList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select *  ");
		sql.append(" from ( select rowNum as rnum, c.* ");
		sql.append(" from ( select board_no, title, content, id, cnt, ");
		
		sql.append(" case when to_char(reg_date, 'yyyy-mm-dd') >= to_char(sysdate, 'yyyy-mm-dd')  ");
		sql.append(" then to_char(reg_date,'HH24.mi') ");
		sql.append(" else to_char(reg_date, 'yyyy.mm.dd') end reg_date ");
		
		sql.append(" from c_board where post_type = ? order by board_no desc) c )  ");
		sql.append(" where rnum between ? and ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, post_type);
			pstmt.setInt(2, (pageNo * 5) - 4);
			pstmt.setInt(3, pageNo * 5);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setCnt(rs.getInt("cnt"));
				board.setRegDate(rs.getString("reg_date"));

				boardList.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	// 글검색
	public List<BoardVO> searchBoard(int pageNo, String category, String word, String post_type) {
		List<BoardVO> searchedList = new ArrayList<>();;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select *  ");
			sql.append(" from ( select rowNum as rnum, c.* ");
			sql.append(" from ( select board_no, title, content, id, cnt ");
			
			sql.append(" , case when to_char(reg_date, 'yyyy-mm-dd') >= to_char(sysdate, 'yyyy-mm-dd')  ");
			sql.append(" then to_char(reg_date,'HH24.mi') ");
			sql.append(" else to_char(reg_date, 'yyyy.mm.dd') end reg_date ");
			
			sql.append(" from c_board  ");
			if (category.equals("rname")) {	// 작성자아이디
				sql.append(" where id like ? and post_type = ? ");
			} else if (category.equals("rtitle")) {	// 제목
				sql.append(" where title like ? and post_type = ? ");
			}
			
			sql.append(" order by board_no desc) c )  ");
			sql.append(" where rnum between ? and ? "); //
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+word+"%");
			pstmt.setString(2, post_type);
			pstmt.setInt(3, (pageNo * 5) - 4);
			pstmt.setInt(4, pageNo * 5);

			System.out.println("검색 :" + category + "," + word);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setCnt(rs.getInt("cnt"));
				board.setRegDate(rs.getString("reg_date"));

				searchedList.add(board);
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
	
	// 검색결과 컬럼수
	public int cntSearchAllRows(String post_type, String category, String word) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*) as count from c_board ");
		
		if (category.equals("rname")) {	// 작성자아이디
			sql.append(" where id like ? and post_type = ? ");
		} else if (category.equals("rtitle")) {	// 제목
			sql.append(" where title like ? and post_type = ? ");
		}
		
		int cntRows = 0;
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, "%"+word+"%");
			pstmt.setString(2, post_type);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cntRows = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cntRows;
	}
	
	// 댓글 추가
	public int insertComment(CommentVO comment) {

		int commentResult = 0;

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into c_comment(no, writer_id, post_no, content) ");
		sql.append(" values (seq_c_comment_no.nextval, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, comment.getWriter());
			pstmt.setInt(2, comment.getPost_no());
			pstmt.setString(3, comment.getContent());
			commentResult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentResult;
	}

	// 모든 댓글
	public List<CommentVO> selectAllComment(int post_no) {
		List<CommentVO> comments = new ArrayList<>();

		StringBuffer sql = new StringBuffer();
		sql.append(" select no, writer_id, content, ");
		sql.append(" case when to_char(reg_date, 'yyyy-mm-dd') >= to_char(sysdate, 'yyyy-mm-dd')  ");
		sql.append(" then to_char(reg_date,'HH') ");
		sql.append(" else to_char(reg_date, 'yyyy.mm') end date_1, ");
		sql.append(" case when to_char(reg_date, 'yyyy-mm-dd') >= to_char(sysdate, 'yyyy-mm-dd')  ");
		sql.append(" then to_char(reg_date,'mi') ");
		sql.append(" else to_char(reg_date, 'dd') end date_2 ");
		sql.append(" from c_comment ");
		sql.append(" where post_no = ? ");
		sql.append(" order by reg_date asc ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, post_no);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVO CVO = new CommentVO();
				CVO.setNo(rs.getInt("no"));
				CVO.setWriter(rs.getString("writer_id"));
				CVO.setContent(rs.getString("content"));
				CVO.setReg_date(rs.getString("date_1"));
				CVO.setReg_date2(rs.getString("date_2"));
				comments.add(CVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
}
