package com.our.sms.student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.our.sms.student.vo.StudentVo;

// 'maven repository' 페이지에서 3가지 코드 복사 (mariadb & jdbc & dbcp) > pom.xml에 붙여넣기!!
// 1. mariadb 검색 > 라인 118 
// 2. jdbc > (Spring JDBC » 5.3.9) > mariadb 코드밑 
// 3. dbcp(database connection pool) setting > (Commons DBCP) > jdbc 코드밑 
// servlet-context.xml에 dataSource 객체(Bean) 만들기 
//servlet-context.xml에 dataSource 객체(Bean) 만들기 
//servlet-context.xml에 jdbcTemplate 객체(Bean) 만들기 

// JDBC > dbcp > jdbcTemplate > mybatis
@Component
public class StudentDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
//	@Autowired
//	DataSource dataSource; 
	
	
		
	public int insertStudentVo(StudentVo studentVo) {
		
		System.out.println("[StudentDao] insertStudentVo() INIT!");
		
		String sql = "INSERT INTO tbl_student_info(s_name, s_gender, s_grade, s_number, s_major, "
				+ "s_phone, s_mail, s_hobby, s_absence, s_reg_date, s_mod_date) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		// object.args : 값이 몇개든지 다받음 (*과 같은역할)
		int result = jdbcTemplate.update(sql, 
					studentVo.getS_name(),
					studentVo.getS_gender(),
					studentVo.getS_grade(),
					studentVo.getS_number(),
					studentVo.getS_major(),
					studentVo.getS_phone(),
					studentVo.getS_mail(),
					studentVo.getS_hobby(),
					studentVo.getS_absence());
				
		return result;
		
	}
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO tbl_student_info(s_name, s_gender, s_grade, s_number, s_major, "
//					+ "s_phone, s_mail, s_hobby, s_absence, s_reg_date, s_mod_date) "
//					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setNString(1, studentVo.getS_name());
//			pstmt.setNString(2, studentVo.getS_gender());
//			pstmt.setInt(3, studentVo.getS_grade());
//			pstmt.setNString(4, studentVo.getS_number());
//			pstmt.setNString(5, studentVo.getS_major());
//			pstmt.setNString(6, studentVo.getS_phone());
//			pstmt.setNString(7, studentVo.getS_mail());
//			pstmt.setNString(8, studentVo.getS_hobby());
//			pstmt.setInt(9, studentVo.getS_absence());
//			
//			result = pstmt.executeUpdate();
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//			
//			try {
//				
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return result;
	
	
	
	
	public List<StudentVo> selectStudentVos() {
		
		System.out.println("[StudentDao] selectStudentVos() INIT!");
		
		String sql = "SELECT * FROM tbl_student_info";
		
		// rowMapper 역할 : 	while (rs.next())
		// List > java.util 임폴트 
		List<StudentVo> studentVos = jdbcTemplate.query(sql, new RowMapper<StudentVo>() {

			@Override
			public StudentVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				StudentVo studentVo = new StudentVo();
				
				studentVo.setS_no( rs.getInt("s_no"));
				studentVo.setS_name(rs.getString("s_name"));
				studentVo.setS_gender(rs.getString("s_gender"));
				studentVo.setS_grade(rs.getInt("s_grade"));
				studentVo.setS_number(rs.getString("s_number"));
				studentVo.setS_major(rs.getString("s_major"));
				studentVo.setS_phone(rs.getString("s_phone"));
				studentVo.setS_mail(rs.getString("s_mail"));
				studentVo.setS_hobby(rs.getString("s_hobby"));
				studentVo.setS_absence(rs.getInt("s_absence"));
				studentVo.setS_reg_date(rs.getString("s_reg_date"));
				studentVo.setS_mod_date(rs.getString("s_mod_date"));
				
				
				return studentVo;
			}
			
		});
		
		return studentVos;

	}
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		ArrayList<StudentVo> studentVos = new ArrayList<StudentVo>();
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * FROM tbl_student_info";
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//		
//			while (rs.next()) {
//				
//				int s_no = rs.getInt("s_no");
//				String s_name = rs.getString("s_name");
//				String s_gender = rs.getString("s_gender");
//				int s_grade = rs.getInt("s_grade");
//				String s_number = rs.getString("s_number");
//				String s_major = rs.getString("s_major");
//				String s_phone = rs.getString("s_phone");
//				String s_mail = rs.getString("s_mail");
//				String s_hobby = rs.getString("s_hobby");
//				int s_absence = rs.getInt("s_absence");
//				String s_reg_date = rs.getString("s_reg_date");
//				String s_mod_date = rs.getString("s_mod_date");
//				
//				
//				StudentVo studentVo = new StudentVo(s_no, s_name, s_gender, s_grade, s_number, s_major, 
//											s_phone, s_mail, s_hobby, s_absence, s_reg_date, s_mod_date);
//				
//				studentVos.add(studentVo);
//				
//				
//			}
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//			
//			try {
//				
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//				if (rs != null) rs.close();
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	
//		
		
	
	
	public int modifyStudentVo(StudentVo studentVo) {
		
		System.out.println("[StudentDao] modifyStudentVo() INIT!");
		
		String sql = " UPDATE tbl_student_info SET"
				+ " s_name= ?, "
				+ "s_gender= ?, "
				+ "s_grade= ?, "
				+ "s_number= ?, "
				+ "s_major= ?, "
				+ "s_phone= ?, "
				+ "s_mail= ?, "
				+ "s_hobby= ?, "
				+ "s_absence= ?, "
				+ "s_mod_date = NOW() WHERE s_no = ?";
				
		int result = jdbcTemplate.update(sql,
					studentVo.getS_name(),
					studentVo.getS_gender(),
					studentVo.getS_grade(),
					studentVo.getS_number(),
					studentVo.getS_major(),
					studentVo.getS_phone(),
					studentVo.getS_mail(),
					studentVo.getS_hobby(),
					studentVo.getS_absence(),
					studentVo.getS_no());
		
		return result;
	

	}
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			String sql = " UPDATE tbl_student_info SET"
//					+ " s_name= ?, "
//					+ "s_gender= ?, "
//					+ "s_grade= ?, "
//					+ "s_number= ?, "
//					+ "s_major= ?, "
//					+ "s_phone= ?, "
//					+ "s_mail= ?, "
//					+ "s_hobby= ?, "
//					+ "s_absence= ?, "
//					+ "s_mod_date = NOW() WHERE s_no = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setNString(1, studentVo.getS_name());
//			pstmt.setNString(2, studentVo.getS_gender());
//			pstmt.setInt(3, studentVo.getS_grade());
//			pstmt.setNString(4, studentVo.getS_number());
//			pstmt.setNString(5, studentVo.getS_major());
//			pstmt.setNString(6, studentVo.getS_phone());
//			pstmt.setNString(7, studentVo.getS_mail());
//			pstmt.setNString(8, studentVo.getS_hobby());
//			pstmt.setInt(9, studentVo.getS_absence());
//			pstmt.setInt(10, studentVo.getS_no());
//			
//			result = pstmt.executeUpdate();
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//			
//			try {
//				
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return result;
//		

	

	public int deleteStudentVo(StudentVo studentVo) {
		
		System.out.println("[StudentDao] deleteStudentVo() INIT!");
		
		String sql = " DELETE FROM tbl_student_info WHERE s_no = ?";
		
		int result = jdbcTemplate.update(sql, studentVo.getS_no());
		
		return result;
		
	}
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			String sql = " DELETE FROM tbl_student_info WHERE s_no = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, studentVo.getS_no());
//			
//			result = pstmt.executeUpdate();
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//			
//			try {
//				
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return result;
//	}



	public StudentVo selectStudentVo(int sNo) {
		
		System.out.println("[StudentDao] selectStudentVo() INIT!");
		
		String sql = "SELECT * FROM tbl_student_info WHERE s_no = ?";
		
		StudentVo studentVo = jdbcTemplate.queryForObject(sql, new RowMapper<StudentVo>(){

			@Override
			public StudentVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				StudentVo studentVo = new StudentVo();
				
				studentVo.setS_no( rs.getInt("s_no"));
				studentVo.setS_name(rs.getString("s_name"));
				studentVo.setS_gender(rs.getString("s_gender"));
				studentVo.setS_grade(rs.getInt("s_grade"));
				studentVo.setS_number(rs.getString("s_number"));
				studentVo.setS_major(rs.getString("s_major"));
				studentVo.setS_phone(rs.getString("s_phone"));
				studentVo.setS_mail(rs.getString("s_mail"));
				studentVo.setS_hobby(rs.getString("s_hobby"));
				studentVo.setS_absence(rs.getInt("s_absence"));
				studentVo.setS_reg_date(rs.getString("s_reg_date"));
				studentVo.setS_mod_date(rs.getString("s_mod_date"));
				
				
				return studentVo;
			}
	
		}, sNo);
			return studentVo;
			
		
	}
}
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		ArrayList<StudentVo> studentVos = new ArrayList<StudentVo>();
//		
//		try {
//			
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * FROM tbl_student_info WHERE s_no = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, sNo);
//			
//			rs = pstmt.executeQuery();
//		
//			
//			while (rs.next()) {
//				
//				int s_no = rs.getInt("s_no");
//				String s_name = rs.getString("s_name");
//				String s_gender = rs.getString("s_gender");
//				int s_grade = rs.getInt("s_grade");
//				String s_number = rs.getString("s_number");
//				String s_major = rs.getString("s_major");
//				String s_phone = rs.getString("s_phone");
//				String s_mail = rs.getString("s_mail");
//				String s_hobby = rs.getString("s_hobby");
//				int s_absence = rs.getInt("s_absence");
//				String s_reg_date = rs.getString("s_reg_date");
//				String s_mod_date = rs.getString("s_mod_date");
//				
//				
//				StudentVo studentVo = new StudentVo(s_no, s_name, s_gender, s_grade, s_number, s_major, 
//											s_phone, s_mail, s_hobby, s_absence, s_reg_date, s_mod_date);
//				
//				studentVos.add(studentVo);
//				
//				
//			}
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//			
//			try {
//				
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//				if (rs != null) rs.close();
//				
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	
//		
//		return studentVos.size() > 0 ? studentVos.get(0) : null;
//	
//	}
//
//}
