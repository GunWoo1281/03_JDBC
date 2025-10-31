package edu.kh.jdbc.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.dao.UserDAO;
import edu.kh.jdbc.model.dto.User;

public class UserService {
	private UserDAO dao = new UserDAO();
	int result = 0;
	
	public int insertUser(User user) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		result = dao.insertUser(user, conn);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<User> selectAll() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> list = dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<User> selectName(String userName) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> list = dao.selectName(userName, conn);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public User selectUser(int userNo) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		User user = dao.selectUser(userNo, conn);
		
		JDBCTemplate.close(conn);
		return user;
	}

	public boolean deleteUser(int userNo) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		boolean result = dao.deleteUser(userNo, conn);
		
		JDBCTemplate.close(conn);
		
		if (result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public boolean updateName(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		boolean result = dao.updateName(user, conn);
		
		JDBCTemplate.close(conn);
		if (result) {
			JDBCTemplate.commit(conn);
			return true;
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		return false;
	}

	public int idCheck(String userId) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.idCheck(userId, conn);
		JDBCTemplate.close(conn);
		if (result > 0) {
			return result;
		}
		return 0;
	}

	public int multiInsertUser(List<User> userList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
