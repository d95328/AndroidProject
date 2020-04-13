package kr.co.lovaband.dto;

import java.sql.Date;

public class MemberDTO {
	private String m_userid;
	private String m_userpw;
	private String m_nickname;
	private String m_email;
	private String m_gender;
	private String m_interest;
	private String m_accept;
	private String m_userimg;
	private String m_admin;
	private String m_birth;
	private Date m_joindate;
	
	
	
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
	public String getM_userid() {
		return m_userid;
	}
	public void setM_userid(String m_userid) {
		this.m_userid = m_userid;
	}
	public String getM_userpw() {
		return m_userpw;
	}
	public void setM_userpw(String m_userpw) {
		this.m_userpw = m_userpw;
	}
	public String getM_nickname() {
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public String getM_interest() {
		return m_interest;
	}
	public void setM_interest(String m_interest) {
		this.m_interest = m_interest;
	}
	public String getM_accept() {
		return m_accept;
	}
	public void setM_accept(String m_accept) {
		this.m_accept = m_accept;
	}
	public String getM_userimg() {
		return m_userimg;
	}
	public void setM_userimg(String m_userimg) {
		this.m_userimg = m_userimg;
	}
	public String getM_admin() {
		return m_admin;
	}
	public void setM_admin(String m_admin) {
		this.m_admin = m_admin;
	}
	public Date getM_joindate() {
		return m_joindate;
	}
	public void setM_joindate(Date m_joindate) {
		this.m_joindate = m_joindate;
	}
	
	
}
