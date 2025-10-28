package edu.kh.jdbc;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {

	public static void main(String[] args) {
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type="jdbc:oracle:thin:@";
			String host="localhost";
			String port=":1521";
			String dbName=":XE";
			
			String userName="kh_ygw";
			String password="kh1234";
			
			conn = DriverManager.getConnection(type+host+port+dbName,userName,password);
			
			String query = "SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY, BONUS, HIRE_DATE "
					+ "FROM KH_YGW.EMPLOYEE";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String empID = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				int salary= rs.getInt("SALARY");
				Date hireDate = rs.getDate("HIRE_DATE");

				System.out.printf("사번 : %s / 이름 : %s / 부서코드 : %s / 직급코드 : %s / 급여 : %d / 입사일 : %s \n" , empID,empName,deptCode,jobCode,salary,hireDate.toString());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	

	}

}