package kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kr.util.ConnectionFactory;
import kr.vo.CarVO;

public class CarDAO {

	/**
	 * id로 차량등록여부를 확인하고 정보를 리턴해주는 메소드
	 */
	public CarVO selectByID(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select car_name, car_no, smoke ,ride_no, direct from c_car ");
		sql.append(" where id = ? ");
		CarVO car = null;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String carName = rs.getString("car_name");
				String carNo = rs.getString("car_no");
				String smoke = rs.getString("smoke");
				int rideNo = rs.getInt("ride_no");
				String direct = rs.getString("direct");
				car = new CarVO(id, carName, carNo, smoke, rideNo, direct);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return car;
	}

	public void insertCar(CarVO car) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into c_car(id, car_name, car_no, direct, smoke, ride_no ) ");
		sql.append(" values(? , ?, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, car.getId());
			pstmt.setString(2, car.getCarName());
			pstmt.setString(3, car.getCarNo());
			pstmt.setString(4, car.getDirect());
			pstmt.setString(5, car.getSmoke());
			pstmt.setInt(6, car.getrideNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * id를 받아와서 차정보를 삭제시키는 메소드 
	 * @param id
	 */
	public int deleteCar(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from c_car " );
		sql.append(" where id = ? ");
		int result = -1;
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}