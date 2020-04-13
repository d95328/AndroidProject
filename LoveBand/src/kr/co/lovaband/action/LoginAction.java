package kr.co.lovaband.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String m_userid = request.getParameter("m_userid");
//		String m_userpw = request.getParameter("m_userpw");
		
		
		/**************** 안드로이드에서 byte 타입으로 넘어온 json 데이터를 stringBuffer -> jsonObect -> String 으로 파싱 하는 과정 ***************/
		System.out.println(request.toString());
		JSONObject jsondata = new JsonParser().jsonParser(request.getReader());
		
		String m_userid = jsondata.get("m_userid").toString();
		String m_userpw = jsondata.get("m_userpw").toString();
		
		
		MemberDTO memberDTO = new MemberDTO();
		JSONObject data = new JSONObject();
		
		MemberDAO mDao = MemberDAO.getInstance();
		memberDTO = mDao.userLogin(m_userid, m_userpw);
		//아래 메소드에서 리턴값이 null이아니면
		if( memberDTO != null ) {
			//만들어놓은 JSONObject에 put으로 담아준다.
			data.put("result", "success");
			data.put("m_userid", memberDTO.getM_userid());
			data.put("m_nickname", memberDTO.getM_nickname());
			data.put("m_interest", memberDTO.getM_interest());
			data.put("m_gender", memberDTO.getM_gender());
			data.put("m_email", memberDTO.getM_email());
			data.put("m_birth", memberDTO.getM_birth());
			data.put("m_joindate", memberDTO.getM_joindate());
			
			
//			member.setM_nickname(rs.getString("m_nickname"));
//			member.setM_interest(rs.getString("m_interest"));
//			member.setM_gender(rs.getString("m_gender"));
//			member.setM_email(rs.getString("m_email"));
//			member.setM_birth(rs.getDate("m_birth"));
//			member.setM_joindate(rs.getDate("m_joindate"));
			System.out.println("로그인성공");
		} else {
			//리턴값 false면 fail 담아주기
			data.put("result", "fail");
			System.out.println("로그인실패");
		}
		
		//안드로이드로 response값에 JSONObject 타입으로 data를 보내줌!
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(data);
		
		return null;
	}
	
	/*//로그인 성공여부확인 메소드
	public boolean loginSuccess(MemberDTO memberDTO) {
		boolean success = false;
		MemberDAO mDao = MemberDAO.getInstance();
				//userLogin 메소드에서 리턴값이 1이면
		if( mDao.userLogin(memberDTO.getM_userid(), memberDTO.getM_userpw()) > 0) {
			//성공이면 success 값이 true
			success = true;
		}
		return success;
	}*/
}
