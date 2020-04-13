package kr.co.lovaband.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	//다른클래스에서 공용으로 사용할 수 있도록 static으로 선언해서 메모리에 올려준다.
	//Connection 은 java.sql 의 인터페이스 이고 import해서 메소드를 구현해 db연결함.
	public static Connection getConnection() {
		Connection conn = null;
		//Connection 객체 null로 초기화 
		try {
			Context initContext = new InitialContext();
			//객체 생성
			
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			//server.xml의 dbcp에 선언된 Context을 호출해 jdbc 드라이버 로딩 및 db접속.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			//lookup 메소드를 사용해서 등록된 naming 서비스로부터 자원을 찾음
			conn = ds.getConnection();
			//ds로부터 connection 객체를 획득하고 접속 마무리
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}//end of getConnection()
	
	//공통으로 사용될 자원회수 메소드 선언
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			//connect close 될 순서는 connect 시와 반대로(역순) 
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//end of close()
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		
	}
}//end of class
