package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet cnt_rs = null;
		int count = 0;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			while(true) {
				System.out.print("부서명 입력 : ");
				String depart = sc.next();
				
				String cntquery = "SELECT COUNT(*) AS cnt FROM EMPLOYEE a "
						+ "JOIN JOB b ON a.JOB_CODE = b.JOB_CODE "
						+ "LEFT JOIN DEPARTMENT c ON a.DEPT_CODE=c.DEPT_ID "
						+ "WHERE c.DEPT_TITLE = '" + depart + "' "
						+ "ORDER BY a.DEPT_CODE ASC ";
				
				String query = "SELECT a.EMP_ID, a.EMP_NAME, NVL(c.DEPT_TITLE,'부서없음') AS DEPT_NAME, b.JOB_NAME FROM EMPLOYEE a "
								+"JOIN JOB b ON a.JOB_CODE = b.JOB_CODE "
						        +"LEFT JOIN DEPARTMENT c ON a.DEPT_CODE=c.DEPT_ID "
								+"WHERE c.DEPT_TITLE = '" + depart + "' "
						        + "ORDER BY a.DEPT_CODE ASC ";
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kh_ygw","kh1234");
				stmt = conn.createStatement();
				cnt_rs = stmt.executeQuery(cntquery);
				
				while (cnt_rs.next()) {
					count = cnt_rs.getInt("cnt");
				}
				stmt.close();
				
				if (count == 0) {
					System.out.println("일치하는 부서가 없습니다!");
					return;
				}
				else {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query);
					
					while(rs.next()) {
						String empID = rs.getString("EMP_ID");
						String empName = rs.getString("EMP_NAME");
						String dept_title= rs.getString("DEPT_NAME");
						String job_name= rs.getString("JOB_NAME");
						
						System.out.printf("%s / %s / %s / %s \n" , empID,empName,dept_title,job_name);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cnt_rs != null) cnt_rs.close();
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
