package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCExample7 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			String gender = null;
			int minsalary = 0;
			int maxsalary = 0;
			int orderby = 0;
			
			System.out.print("조회할 성별(M/F) : ");
			gender = sc.next().toUpperCase();
			System.out.print("급여 범위(최소, 최대 순서로 작성): ");
			minsalary = sc.nextInt(); sc.nextLine();
			maxsalary = sc.nextInt(); sc.nextLine();
			System.out.print("급여 정렬(1. ASC, 2.DESC): ");
			orderby = sc.nextInt();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kh_ygw","kh1234");
			
			String query = "SELECT * FROM "
                    +"(SELECT a.EMP_ID, a.EMP_NAME, CASE SUBSTR(EMP_NO, 8, 1) "
				    + "WHEN '1' THEN 'M' "
                    + "WHEN '2' THEN 'F' "
				    + "END AS GENDER , a.SALARY, b.JOB_NAME, NVL(c.DEPT_TITLE,'부서없음') AS DEPT_TITLE "
                    + "FROM EMPLOYEE a "
				    + "JOIN JOB b ON a.JOB_CODE = b.JOB_CODE "
                    + "LEFT JOIN DEPARTMENT c ON a.DEPT_CODE = c.DEPT_ID) "
				    + "WHERE GENDER = ? "
                    + "AND (SALARY BETWEEN ? AND ?) ";
			
			if (orderby == 1) {
				query += "ORDER BY SALARY ASC";
			} else {
				query += "ORDER BY SALARY DESC";
			}
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender);
			pstmt.setInt(2, minsalary);
			pstmt.setInt(3, maxsalary);
			
			rs = pstmt.executeQuery();

			System.out.println("사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("--------------------------------------------------------");
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String gen = rs.getString("GENDER");
				int salary = rs.getInt("SALARY");
				String jobName = rs.getString("JOB_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				
				System.out.printf("%-4s | %3s | %-4s | %7d | %-3s  | %s \n",
						empId, empName, gen, salary, jobName, deptTitle);
			}
			System.out.println("--------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
