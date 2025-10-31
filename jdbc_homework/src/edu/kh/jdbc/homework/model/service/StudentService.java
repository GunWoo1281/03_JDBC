package edu.kh.jdbc.homework.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import edu.kh.jdbc.homework.common.JDBCTemplate;
import edu.kh.jdbc.homework.model.dao.StudentDAO;
import edu.kh.jdbc.homework.model.dto.Student;

public class StudentService {

	private StudentDAO dao = new StudentDAO();
	int result = 0;
	
	public ArrayList<Student> selectAllStudents() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Student> list = dao.selectAllStudents(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Student> selectStudentsByMajor(String major) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Student> list = dao.selectStudentsByMajor(conn, major);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public Student selectStudentByNum(int studentNum) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		Student student = dao.selectStudentByNum(conn, studentNum);
		JDBCTemplate.close(conn);
		return student;
	}
	
	public int addStudent(Student student) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.addStudent(conn, student);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateStudent(Student student) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateStudent(conn, student);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteStudent(int studentNum) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteStudent(conn, studentNum);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}
