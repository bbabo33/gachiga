package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.util.ConnectionFactory;
import kr.vo.ApplyVO;
import kr.vo.MemberVO;

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
	
public List<ApplyVO> selectById(String id){
		
		List<ApplyVO> list= new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.writer_id as writer_id, a.post_no as post_no, " );
		sql.append("  a.user_cnt as user_cnt, a.status as status, c.start_place_name as start_place_name ");
		sql.append(" ,c.start_time as start_time, c.start_date as start_date, c.end_place_name as end_place_name, ");
		sql.append(" a.id as id, c.post_type as post_type ");
		sql.append(" from c_apply a, c_carpool_post c ");
		sql.append(" where a.post_no = c.no ");
		sql.append(" and a.id = ? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ApplyVO apply = new ApplyVO();
				apply.setId(id);
				apply.setWriter_id(rs.getString("writer_id"));
				apply.setPost_no(rs.getInt("post_no"));
				apply.setUser_cnt(rs.getInt("user_cnt"));
				apply.setStatus(rs.getString("status"));
				apply.setStart_place_name(rs.getString("start_place_name"));
				apply.setStart_time(rs.getString("start_time"));
				apply.setStart_date(rs.getString("start_date"));
				apply.setEnd_place_name(rs.getString("end_place_name"));
				apply.setType(rs.getString("post_type"));
				
				list.add(apply);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	public Map<Integer, List<MemberVO>> selectReserv(List<Integer> noList){
		
		List<MemberVO> memList=null;
		Map<Integer, List<MemberVO>> reservList = new HashMap<>();
		
		for(int no : noList) {
			memList = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append(" select m.name as name , m.id as id, m.age as age, SUBSTR(m.tel,1,3)||'-****-**'||substr(m.tel,10,2) as tel ");
			sql.append(" from c_member m, c_apply a ");
			sql.append(" where m.id = a.id ");
			sql.append(" and a.post_no = ? ");
			MemberVO member = null;
			
			try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
				pstmt.setInt(1, no);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("applydao try : "+no);
				
				while(rs.next()) {
				   member =new MemberVO();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setTel(rs.getString("tel"));
					memList.add(member);
				}
				if(memList.size() != 0) {
					reservList.put(no,memList);
				}
				System.out.println("applyDAO finally:" +reservList.get(no));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(reservList.size());
		System.out.println("dao 96");
		return reservList;
	}
}