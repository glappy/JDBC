package com.biz.oracle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * dao클래스는 db와 연동하는 기능을 수행한다
 * db에 연결하고 sql을 전송하고 db에 데이터를 보내고(insert, update, delete) db로부터 데이터를 받고(select, **) 
 */
public class IoListDao {
	/*
	 * db연결을 위한 connection개체를 선언
	 */
	Connection dbConn;
	/*
	 * db에 연결하기
	 * driver를 loading하고 connection을 준비하는 method
	 */
	public void dbConnection() {
		/*
		 * db driver를 loading 한다 
		 * 지금부터 아래 파일을 읽어서 실행 시킨 후 조용히 감추어 대기시켜라
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/* 외부 파일을 실행시켜 작동하는 경우 try catch해야 함
			* jdbc에서 db에 연결할 수 있도록 프로파일 설정 
			*
			*/
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "myMem";
			String password = "1234";
			
			dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("db연결 성공");
			/*
			 * db연결 profile을 가지고 db에 접속하여 접속 권한을 획득한다
			 * 내가 전달하는 url, user, 비밀번호 정보를 사용해서 db에 접속하고 내가 보내는 sql문을 db에 전달할 수 있도록 준비하라
			 */
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectIoList() {
		// db에 보낼 sql
		// join을 해서 문자열이 지나치게 길어진다면? view만들어서 불러올 것
		String sql = "SELECT * FROM tbl_iolist";
		/*
		 * sql명령문을 jdbc에게 전달하는 절차
		 */
		PreparedStatement ps;
		try {
			/*
			 * String으로 만든 sql명령문을 jdbc에게 전달하라(String을 jdbc가 알아먹도록 변환 후 전달)
			 */
			ps = dbConn.prepareStatement(sql);
			/*
			 * jdbc야 지금부터 db에게 sql 전송하고 결과를 가져와라
			 * sql이 정상적으로 db로부터 수행되면  그 결과를 rs변수에 받아라
			 */
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			/*
			 * 결과를 console에 보여주기
			 * 몇개가 들어있는지 알수가 없다 for로 list처럼 돌릴 수가 없음
			 */
			while(true) {
				/*
				 * rs에 읽을 데이터가 있는가 물으려면..
				 * ※※ 칼럼 이름을 문자열로 집어넣으면 저절로 오류 검사가 되지 않음.
				 * 본인이 작성하는 것이 문자열인지 숫자열인지 다 알고 있어야 함
				 */
				if(rs.next() == false) break;
				String strDate = rs.getString("io_date");
				String strInout = rs.getString("io_inout");
				String strPCode = rs.getString("io_pcode");
				int intPrice = rs.getInt("io_price");
				int intQuan = rs.getInt("io_quan");
				int intSum = intPrice*intQuan;
			
				System.out.print(strDate + "\t");
				System.out.print(strInout + "\t");
				System.out.print(strPCode + "\t");
				System.out.print(intPrice + "\t");
				System.out.print(intQuan + "\t");
				System.out.println(intSum + "\n");
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
}
