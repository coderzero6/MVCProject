package com.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	//싱글톤 처리
	private static AdminDAO instance=null;
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		if(instance==null) {instance = new AdminDAO();}
		return instance;
	}
	
	private Connection getConnection() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/xe");
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int adminCheck(String admin_id, String admin_pwd) throws Exception{
		int result = -1;
		try {
			sql="select admin_pwd from admin where admin_id=?";
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, admin_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(admin_pwd.equals(rs.getString(1))) {
					result = 1;
				}else {
					result = 0;
				}
			}else {
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
