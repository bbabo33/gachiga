package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.util.ConnectionFactory;
import kr.util.JDBCClose;
import kr.vo.BoardVO;
import kr.vo.CommentVO;

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
	public BoardVO selectByNo(int boardNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVO board = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("  from c_board ");
			sql.append(" where board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			
			int loc = 1;
			pstmt.setInt(loc++, boardNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				board =new BoardVO();
				int board_no = rs.getInt("board_no");	//게시글 번호
				String title = rs.getString("title");	//게시글 제목
				String id = rs.getString("id");			//게시글 작성자
				String content = rs.getString("content");	//게시글 내용
				int cnt = rs.getInt("cnt");				//게시글 조회수
				String regDate = rs.getString("reg_date");	//게시글 작성일
				
				board.setBoard_no(board_no);
				board.setTitle(title);
				board.setId(id);
				board.setContent(content);
				board.setCnt(cnt);
				board.setRegDate(regDate);
				System.out.println(board.toString()+":dao line 160");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
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
			
			System.out.println(pstmt + " : dao line195");
			
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
	
	public int cntAllRows() {
		
		String sql="select count(*) as count from c_board";
		int cntRows=0;
		
		try(
			Connection conn=new ConnectionFactory().getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);	
		){
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cntRows = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cntRows;
	}
	
	public List<BoardVO> getPage(int pageNo) {
		
		List<BoardVO> boardList=new ArrayList<>();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select *  ");
		sql.append(" from ( select rowNum as rnum, c.* ");
		sql.append(" from ( select * from c_board order by board_no desc) c ) ");
		sql.append(" where rnum between ? and ? ");
		
		try(
			Connection conn=new ConnectionFactory().getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());	
		){
			pstmt.setInt(1, (pageNo*5)-4);
			pstmt.setInt(2, pageNo*5);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board=new BoardVO();
				board.setBoard_no(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setCnt(rs.getInt("cnt"));
				board.setRegDate(rs.getString("reg_date"));
				//System.out.println(board.toString()); //board객체확인 Ok 
				boardList.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	public int insertComment(CommentVO comment) {
		
		int commentResult = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into c_comment(no,writer_id,post_no,content) ");
		sql.append(" values (seq_c_comment_no.nextval, ?,?,?) ");
		
		try(
				Connection conn=new ConnectionFactory().getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());	
			){
			int i=1;
			pstmt.setString(i++, comment.getWriter());
			pstmt.setInt(i++, comment.getPost_no());
			pstmt.setString(i++, comment.getContent());
			commentResult = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commentResult;
	}
	
	public List<CommentVO> selectAllComment() {
		List<CommentVO> comments =new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, writer_id, post_no, content, to_char(reg_date,'yyyy-MM-dd') as regDate ");
		sql.append(" from c_comment ");
		sql.append(" order by reg_date desc ");
		CommentVO CVO = null;
		
		try(
				Connection conn=new ConnectionFactory().getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());	
			){
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CVO = new CommentVO();
				CVO.setNo(rs.getInt("no"));
				CVO.setWriter(rs.getString("writer_id"));
				CVO.setPost_no(rs.getInt("post_no"));
				CVO.setContent(rs.getString("content"));
				CVO.setRegDate(rs.getString("regDate"));
				comments.add(CVO);
				System.out.println(CVO.toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	public List<BoardVO> searchBoard(String category, String word){
		
		List<BoardVO> searchedList = null;
		BoardVO searchedBoard =null;
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//num rname
		
		try{
			conn = new ConnectionFactory().getConnection();
			
			if(category.equals("rname")) {
				sql.append(" select board_no, title, id, reg_date from c_board ");
				sql.append(" where id = ? ");
				sql.append(" order by board_no asc ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, word);
			}
		
			System.out.println("dao 483:"+category+","+word);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				searchedList = new ArrayList<>();
			}
			while(rs.next()) {
				searchedBoard = new BoardVO();
				searchedBoard.setBoard_no(rs.getInt("board_no"));
				searchedBoard.setTitle(rs.getString("title"));
				searchedBoard.setContent(rs.getString("reg_date"));
				searchedBoard.setId(rs.getString("id"));
				searchedList.add(searchedBoard);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return searchedList;
	}
}
