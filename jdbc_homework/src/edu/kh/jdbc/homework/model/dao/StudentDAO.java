package edu.kh.jdbc.homework.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import edu.kh.jdbc.homework.model.dto.Student;

public class StudentDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result = 0;
	
	public ArrayList<Student> selectAllStudents(Connection conn) throws Exception {
		try {
			ArrayList<Student> list = new ArrayList<Student>();
			
			String query = "SELECT STD_NO, STD_NAME, STD_AGE, MAJOR, ENT_DATE "
					+ "FROM KH_YGW.KH_STUDENT ORDER BY STD_NO";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int stdNo = rs.getInt("STD_NO");
				String stdName = rs.getString("STD_NAME");
				int stdAge = rs.getInt("STD_AGE");
				String major = rs.getString("MAJOR");
				String entDate = rs.getString("ENT_DATE");

				Student student = new Student(stdNo, stdName, stdAge, major, entDate);
				list.add(student);
			}
			
			return list;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public ArrayList<Student> selectStudentsByMajor(Connection conn, String major) throws Exception {
		try {
			ArrayList<Student> list = new ArrayList<Student>();
			
			String query = "SELECT * FROM KH_STUDENT WHERE MAJOR = '" + major + "'";
			
			stmt = conn.createStatement();
			rs	= stmt.executeQuery(query);
			
			while (rs.next()) {
				int stdNo = rs.getInt("STD_NO");
				String stdName = rs.getString("STD_NAME");
				int stdAge = rs.getInt("STD_AGE");
				String entDate = rs.getString("ENT_DATE");

				Student student = new Student(stdNo, stdName, stdAge, major, entDate);
				list.add(student);
			}
			
			return list;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public Student selectStudentByNum(Connection conn, int studentNum) throws Exception {
		Student student = null;
		try {
			String query = "SELECT * FROM KH_STUDENT WHERE STD_NO = " + studentNum;
			stmt = conn.createStatement();
			rs	= stmt.executeQuery(query);
			
			if (rs.next()) {
				int stdNo = rs.getInt("STD_NO");
				String stdName = rs.getString("STD_NAME");
				int stdAge = rs.getInt("STD_AGE");
				String major = rs.getString("MAJOR");
				String entDate = rs.getString("ENT_DATE");

				student = new Student(stdNo, stdName, stdAge, major, entDate);
			}
			
			return student;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}
	
	public int addStudent(Connection conn, Student student) throws Exception {
		try {
			String query = "INSERT INTO KH_STUDENT (STD_NO, STD_NAME, STD_AGE, MAJOR, ENT_DATE) "
					+ "VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, CURRENT_DATE)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudent_name());
			pstmt.setInt(2, student.getStudent_age());
			pstmt.setString(3, student.getStudent_major());
			result = pstmt.executeUpdate();

			return result;
		}
		finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	public int updateStudent(Connection conn, Student student) throws Exception {
		try {
			String query = "UPDATE KH_STUDENT SET STD_NAME = ?, STD_AGE = ?, MAJOR = ? WHERE STD_NO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudent_name());
			pstmt.setInt(2, student.getStudent_age());
			pstmt.setString(3, student.getStudent_major());
			pstmt.setInt(4, student.getStudent_num());
			result = pstmt.executeUpdate();

			return result;
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	public int deleteStudent(Connection conn, int studentNum) throws Exception {
		try {
			String query = "DELETE FROM KH_STUDENT WHERE STD_NO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, studentNum);
			result = pstmt.executeUpdate();
			
			return result;
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}
	
}
