package edu.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.dto.User;

public class UserDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result = 0;
	
	public int insertUser(User user, Connection conn) throws Exception {
		try {
			String query = "INSERT INTO KH_YGW.TB_USER VALUES(EMP_SEQ.NEXTVAL, ?, ?, ?, DEFAULT)";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
		}
		finally {
			if (pstmt != null)
				close(pstmt);
		}
		return result;
	}

	public ArrayList<User> selectAll(Connection conn) throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			String query = "SELECT USER_NO, USER_ID, USER_PW, USER_NAME,"
					+ "TO_CHAR(ENROLL_DATE,'YYYY\"년\" MM\"월\" DD\"일\"') AS ENROLL_DATE FROM KH_YGW.TB_USER "
					+ "ORDER BY USER_NO";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				User user = new User(userNo, userId, userPw, userName, enrollDate);
				list.add(user);
			}
		}
		finally {
			if (rs != null)
				close(rs);
			if (stmt != null)
				close(stmt);
		}
		return list;
	}

	public ArrayList<User> selectName(String userName, Connection conn) throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		try {
			String query = "SELECT USER_NO, USER_ID, USER_PW, USER_NAME, "
					+ "TO_CHAR(ENROLL_DATE,'YYYY\"년\" MM\"월\" DD\"일\"') AS ENROLL_DATE FROM KH_YGW.TB_USER "
					+ "WHERE USER_NAME LIKE '%"+ userName + "%'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName2 = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				User u = new User(userNo, userId, userPw, userName2, enrollDate);
				list.add(u);
			}
		} 
		finally {
			if (rs != null)
				close(rs);
			if (stmt != null)
				close(stmt);
		}
		return list;
	}

	public User selectUser(int userNo, Connection conn) throws Exception {
		User u = new User();
		try {
			String query = "SELECT USER_NO, USER_ID, USER_PW, USER_NAME, "
					+ "TO_CHAR(ENROLL_DATE,'YYYY\"년\" MM\"월\" DD\"일\"') AS ENROLL_DATE FROM KH_YGW.TB_USER "
					+ "WHERE USER_NO = " + userNo;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int userNo2 = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");

                u = new User(userNo2, userId, userPw, userName, enrollDate);
			}
		}
		finally {
			if (rs != null)
				close(rs);
			if (stmt != null)
				close(stmt);
		}
		return u;
	}

	public boolean deleteUser(int userNo, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		try {
			String query = "DELETE FROM KH_YGW.TB_USER WHERE USER_NO = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		}
		finally {
			if (pstmt != null)
				close(pstmt);
		}
		return false;
	}

	public boolean updateName(User user, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		try {
            String query = "UPDATE KH_YGW.TB_USER SET USER_NAME = ? "
            		+ "WHERE USER_ID = ? AND USER_PW = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserId());
            pstmt.setString(3, user.getUserPw());
            int result = pstmt.executeUpdate();
            if (result > 0) {
            	return true;
            }
        }
		finally {
			if (pstmt != null)
				close(pstmt);
		}
		return false;
	}

	public int idCheck(String userId, Connection conn) throws Exception{
		// TODO Auto-generated method stub
		try {
			String query = "SELECT COUNT(*) AS CNT FROM KH_YGW.TB_USER WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("CNT");
			}
		} finally {
			if (rs != null)
				close(rs);
			if (pstmt != null)
				close(pstmt);
		}
		return 0;
	}
}
