package com.example.AndroidProject.dto;


public class MemberDTO {
    private String m_userid;
    private String m_userpw;
    private String m_nickname;
    private String m_email;
    private String m_gender;
    private String m_interest;

    public MemberDTO() {

    }
    public MemberDTO(String m_userid, String m_userpw, String m_nickname, String m_email, String m_gender, String m_interest) {
        this.m_userid = m_userid;
        this.m_userpw = m_userpw;
        this.m_nickname = m_nickname;
        this.m_email = m_email;
        this.m_gender = m_gender;
        this.m_interest = m_interest;
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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "m_userid='" + m_userid + '\'' +
                ", m_nickname='" + m_nickname + '\'' +
                ", m_email='" + m_email + '\'' +
                ", m_gender='" + m_gender + '\'' +
                ", m_interest='" + m_interest + '\'' +
                '}';
    }
}
