package kr.co.lovaband.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import kr.co.lovaband.common.DBManager;
import kr.co.lovaband.dto.MemberDTO;

public class MemberDAO {
	//default 생성자
	private MemberDAO() {
		
	}
	
	//싱글톤 패턴 private static을 걸어줘서 공유해서 사용하는 instance 객체를 메모리(시스템영역)에 올려놓고 
	//다른 클래스에서는 새로운 instance 객체를 new 연산자와 함께 생성하지 못하도록 제한 (getInstance()까지가 싱글톤의 사용)
	/****************************싱글톤 디자인패턴*********************************/
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	/******************************************************************************/
	
	//회원가입 닉네임중복확인 메소드
	public MemberDTO userNicknameCheck(String nickname) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO check = null;
		
		String sql = "select * from members where m_nickname=?";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				check = new MemberDTO();
				check.setM_userid(rs.getString("m_nickname"));
			}
		} catch (Exception e) {
			System.out.println("DAO 쿼리에러 : " + e);
		} finally {
			DBManager.close(conn, ps, rs);
		}
		return check;
	}
	
	//회원가입 이메일중복확인 메소드
	public MemberDTO userEmailCheck(String m_email) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO check = null;
		
		String sql = "select * from members where m_email=?";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m_email);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				check = new MemberDTO();
				check.setM_userid(rs.getString("m_email"));
			}
		} catch (Exception e) {
			System.out.println("DAO 쿼리에러 : " + e);
		} finally {
			DBManager.close(conn, ps, rs);
		}
		return check;
	}
	
	//회원가입 아이디중복확인 메소드
	public MemberDTO userIdCheck(String m_userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO check = null;
		
		String sql = "select * from members where m_userid=?";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m_userid);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				check = new MemberDTO();
				check.setM_userid(rs.getString("m_userid"));
			}
		} catch (Exception e) {
			System.out.println("DAO 쿼리에러 : " + e);
		} finally {
			DBManager.close(conn, ps, rs);
		}
		
		return check;
	}
	
	
	//회원정보수정 메소드
	public int userUpdate(MemberDTO member) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "UPDATE members SET m_nickname=?, m_interest=?, m_email=? WHERE m_userid=?";
		
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getM_nickname());
			ps.setString(2, member.getM_interest());
			ps.setString(3, member.getM_email());
			ps.setString(4, member.getM_userid());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("정보수정 실패 (쿼리문 에러)");
		} finally {
			DBManager.close(conn, ps);
		}
		return result;
	}
	
	//회원가입 메소드
	public int userJoin(MemberDTO member) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "INSERT INTO members ("
				+ "m_userid, m_userpw, m_nickname, m_email, m_birth, m_gender, m_interest, m_accept) "
				+ "values(?, ?, ?, ?, ?, ?, ?, default)";
		
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getM_userid());
			ps.setString(2, member.getM_userpw());
			ps.setString(3, member.getM_nickname());
			ps.setString(4, member.getM_email());
			ps.setString(5, member.getM_birth());
			ps.setString(6, member.getM_gender());
			ps.setString(7, member.getM_interest());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage() + "회원가입 실패-쿼리문삽입실패");
		} finally {
			DBManager.close(conn, ps);
		}
		return result;
	}
	
	
	
	//회원로그인 메소드
	public MemberDTO userLogin(String m_userid, String m_userpw) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO member = null; 
		
		String sql = "SELECT * FROM members WHERE m_userid=? and m_userpw=?";
		
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m_userid);
			ps.setString(2, m_userpw);
			rs = ps.executeQuery();
			
			//쿼리문이 실행되고 맞는게 1개 나오겠지? cnt에? 그게 1이면 성공이고 0이면 실패고!
			if( rs.next() ) {
				member = new MemberDTO();
				member.setM_userid(rs.getString("m_userid"));
				member.setM_nickname(rs.getString("m_nickname"));
				member.setM_interest(rs.getString("m_interest"));
				member.setM_gender(rs.getString("m_gender"));
				member.setM_email(rs.getString("m_email"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_joindate(rs.getDate("m_joindate"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"userLoginMethod");
		} finally {
			DBManager.close(conn, ps, rs);
		}
		//1이나 0이 리턴됨!
		return member;
	}
	
	//회원전체출력 메소드
	public ArrayList<MemberDTO> listMember() {
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		//회원목록조회 쿼리문
		String sql = "SELECT * "
					+ "FROM members";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				
				member.setM_userid(rs.getString("m_userid"));
				member.setM_nickname(rs.getString("m_nickname"));
				member.setM_interest(rs.getString("m_interest"));
				member.setM_gender(rs.getString("m_gender"));
				member.setM_email(rs.getString("m_email"));
				member.setM_birth(rs.getString("m_birth"));
				member.setM_joindate(rs.getDate("m_joindate"));
				
				memberList.add(member);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBManager.close(conn, ps, rs);
		}
		return memberList;
	}
}
