package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		int minSalary = 0;
		int maxSalary = 0;
		
		try {
			System.out.print("최소 급여 입력 : ");
			minSalary = sc.nextInt();
			System.out.print("최대 급여 입력 : ");
			maxSalary = sc.nextInt();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kh_ygw","kh1234");
			
			String query = "SELECT EMP_ID, EMP_NAME, SALARY "
					+ "FROM EMPLOYEE WHERE SALARY BETWEEN " 
					+ minSalary	+ " AND " + maxSalary + "ORDER BY SALARY DESC";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String empID = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d \n", empID, empName, salary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
