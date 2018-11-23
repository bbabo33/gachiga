package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.util.ConnectionFactory;
import kr.util.JDBCClose;
import kr.vo.BoardFileVO;
import kr.vo.BoardVO;
import kr.vo.CarpoolVO;

/**
 * 게시판(t_board)를 CRUD하는 기능클래스
 * 
 * @author acorn
 *
 */
public class BoardDAO {
	/**
	 * 조회기능
	 */
	public List<BoardVO> selectAllBoard() {

		List<BoardVO> boardList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			//sql.append(" select * ");
			sql.append(" select * ");
			//sql.append(" to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append("  from c_board ");
			sql.append(" order by board_no desc ");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("board_no");
				String title = rs.getString("title");
				String id = rs.getString("id");
				String regDate = rs.getString("reg_date");

				BoardVO board = new BoardVO();
				board.setBoard_no(no);
				board.setTitle(title);
				board.setId(id);
				board.setRegDate(regDate);

				boardList.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return boardList;
	}

	/**
	 * 게시물 삽입을 위한 시퀀시번호 추출(seq_t_board_no)
	 */

	public int selectNo() {
		String sql = "select seq_c_board_no.nextval from dual ";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * 게시글 삽입하는 기능
	 * @return 
	 */
	public int insertBoard(BoardVO board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into c_board( board_no, title, id, content ) ");
			sql.append(" values( seq_t_board_no.nextval, ?, ?, ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());

			int loc = 1;
			
			pstmt.setString(loc++, board.getTitle());
			pstmt.setString(loc++, board.getId());
			pstmt.setString(loc++, board.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;

	}

	/**
	 * 게시판 번호로 조회하는 기능
	 */
	public BoardVO selectByNo(int board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVO hugiboard = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("  from c_board ");
			sql.append(" where board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			
			int loc = 1;
			pstmt.setInt(loc++, board);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {

/*				hugiboard.setBoard_no(rs.getInt("board_no"));
				hugiboard.setTitle(rs.getString("Title"));
				hugiboard.setId(rs.getString("id"));
				hugiboard.setContent(rs.getString("content"));
				hugiboard.setCnt(rs.getInt("cnt"));
				hugiboard.setRegDate(rs.getString("reg_date"));
*/
				int no = rs.getInt("board_no");
				String title = rs.getString("title");
				String id = rs.getString("id");
				String content = rs.getString("content");
				int cnt = rs.getInt("cnt");
				String regDate = rs.getString("reg_date");
				//System.out.println(no +  " : dao");
				hugiboard = new BoardVO(no, title, id, content, cnt, regDate);
				//System.out.println(hugiboard + "dao ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hugiboard;
	}

	/**
	 * 게시물 수정하는 기능
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

			int loc = 1;
			pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setString(loc++, board.getTitle());
			pstmt.setString(loc++, board.getContent());
			pstmt.setInt(loc++, board.getBoard_no());
			
			System.out.println(pstmt + " : dao");

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	/**
	 * view_cnt를 증가하는 기능
	 */

	public void updateViewCnt(int board_no) {
//		finally에 close를 할 필요가 없다
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
		/*
		 * Connection conn = null;
		 * 
		 * try { conn = new ConnectionFactory().getConnection(); } catch(Exception e) {
		 * e.printStackTrace(); } finally {
		 * 
		 * }
		 */
	}

	/**
	 * 게시물 삭제하는 기능
	 * @return 
	 */
	public int deleteBoard(int no) {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from c_board ");
		sql.append(" where board_no = ?  ");
		
		int result = 0;
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			
			int loc = 1;
			
			pstmt.setInt(loc++, no);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	
	/**
	 * 최근글 5개를 반환하는 메소드 
	 */
	

	public List<BoardVO> selectFive(){
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      List<BoardVO> newList = new ArrayList<>();
	      try {
	         conn = new ConnectionFactory().getConnection();
	         StringBuilder sql = new StringBuilder();
	         sql.append(" select * from ( ");
	         sql.append(" select rownum as rnum, c.* from ( ");
	         sql.append(" select board_no , title, id, reg_date ");
	         sql.append(" from ( select * from c_board order by reg_date desc) ");
	         sql.append(" ) c ");
	         sql.append(" )where rnum between 1 and 5 ");
	          pstmt = conn.prepareStatement(sql.toString());
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

	// ----------------------------------첨부파일---------------------------------
	/**
	 * 첨부파일 저장하는기능
	 */

	/*public void insertFile(BoardFileVO fileVO) {
//		 System.out.println(fileVO.toString()); -ok
		StringBuilder sql = new StringBuilder();
		sql.append("insert into c_board_file (no, board_no, file_ori_name, file_save_name, file_size)  ");
		sql.append("values (seq_c_board_file_no.nextval, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {
			pstmt.setInt(1, fileVO.getBoard_no());
			pstmt.setString(2, fileVO.getFile_ori_name());
			pstmt.setString(3, fileVO.getFile_save_name());
			pstmt.setInt(4, fileVO.getFile_size());

			int i = pstmt.executeUpdate();
			System.out.println(i);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 게시물 번호에 해당 첨부파일 조회하는 기능
	 */

	/*public List<BoardFileVO> selectFileByNo(int boardNo) {

		List<BoardFileVO> fileList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select no,file_ori_name,file_save_name,file_size ");
		sql.append(" from t_board_file ");
		sql.append(" where board_no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("no"));
				fileVO.setFile_ori_name(rs.getString("file_ori_name"));
				fileVO.setFile_save_name(rs.getString("file_save_name"));
				fileVO.setFile_size(rs.getInt("file_size"));

				fileList.add(fileVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;

	}*/

	/**
	 * 첨부파일 삭제
	 */

/*	public void deleteFile(int boardNo) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_board_file ");
		sql.append(" where board_no = ?  ");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement setInt(int i, int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
