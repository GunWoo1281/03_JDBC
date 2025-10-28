package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample5 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			System.out.print("이름 입력 : ");
			String name = sc.nextLine();
			
			String query = "INSERT INTO TB_USER (USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE) "
					+ "VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kh_ygw","kh1234");
			
			pstmt = conn.prepareStatement(query);
			conn.setAutoCommit(false); // 자동커밋 해제
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("회원가입 성공");
				conn.commit(); // 커밋
			} else {
				System.out.println("회원가입 실패");
				conn.rollback(); // 롤백
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
