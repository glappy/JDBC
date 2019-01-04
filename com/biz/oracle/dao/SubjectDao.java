package com.biz.oracle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDao {

	Connection dbConn;
	
	public void subConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "myMem";
			String password = "1234";
			
			dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subSelect() {
		String sql = "SELECT * FROM tbl_subject";
		
		PreparedStatement ps;
		try {
			ps = dbConn.prepareStatement(sql);
			ps.executeQuery(sql);
			ResultSet rs = ps.executeQuery();
			while(true) {
				if(rs.next() == false) break ;
				String strCode = rs.getString("sb_code");
				String strName = rs.getString("sb_name");
				
				System.out.print(strCode + "\t");
				System.out.println(strName + "\n");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
