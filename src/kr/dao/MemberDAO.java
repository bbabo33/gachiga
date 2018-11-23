package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.util.ConnectionFactory;
import kr.util.JDBCClose;
import kr.vo.MemberVO;

public class MemberDAO {
	/**
	 * ��� ȸ�� ��ȸ
	 * 
	 * @return MemberVO��ü ����Ʈ
	 */
	public List<MemberVO> selectAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<MemberVO> memberList = new ArrayList<>();
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			// TODO Auto-generated catch block
			sql.append(" select id, name, to_char(reg_date, 'YYYY-mm-dd') reg_date ");
			sql.append(" from t_member ");
			sql.append(" order by reg_date asc ");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO member = new MemberVO();

				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getString("reg_date"));
				
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return memberList;
	}
	
	public List<MemberVO> selectAllMember(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<MemberVO> memberList = new ArrayList<>();
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			// TODO Auto-generated catch block
			sql.append(" select id, name, to_char(reg_date, 'YYYY-mm-dd') reg_date ");
			sql.append(" from t_member ");
			sql.append(" where rownum >= ? and rownum <= ? ");
			sql.append(" order by reg_date asc ");
			
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO member = new MemberVO();
				
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getString("reg_date"));
				
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return memberList;
	}

	/**
	 * ȸ�� �߰�
	 * @param board
	 * @return �ݿ��� �� ����
	 */
	public int insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
 			sql.append(" insert into c_member (name, id, password, email, birth, tel, age, addr) ");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?) ");
 			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getBirth());
			pstmt.setString(6, member.getTel());
			pstmt.setInt(7, member.getAge());
			pstmt.setString(8, member.getAddr());
			
 			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 로그인을 위한 쿼리문
	 * @param member
	 * @return
	 */
	public MemberVO selectForLogin(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO result = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select name, id, password, email, birth ");
			sql.append(" tel, age, addr,  to_char(reg_date, 'yyyy-mm-dd hh24-mi') as reg_date ");
			sql.append(" from c_member ");
			sql.append(" where id = ? ");
			sql.append(" and password = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new MemberVO();
				
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public MemberVO selectById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO member = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select name, id, email, password, ");
			sql.append(" birth, tel, age, ");
			sql.append(" addr, reg_date ");
			sql.append(" from c_member ");
			sql.append(" where id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setBirth(rs.getString("birth"));
				member.setTel(rs.getString("tel"));
				member.setAge(rs.getInt("age"));
				member.setAddr(rs.getString("addr"));
				member.setRegDate(rs.getString("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public int dele_user(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(" delete from c_member ");
			sql.append(" where id = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateUser(MemberVO member) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update c_member ");
		sql.append(" set age=? , tel=?, addr=?, birth=? ");
		sql.append(" where id = ? ");
		int result = -1;
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, member.getAge());
			pstmt.setString(2, member.getTel());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getId());
			
			result =  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
}
