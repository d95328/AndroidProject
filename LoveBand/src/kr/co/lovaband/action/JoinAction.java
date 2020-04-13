package kr.co.lovaband.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsondata = new JsonParser().jsonParser(request.getReader());
		MemberDTO mDto = new MemberDTO();
		//JSONOBJECT에서 넘어온 데이터를 DTO에 담아주기 -> DAO에서 dto로 사용
		
		mDto.setM_userid(jsondata.get("m_userid").toString());
		mDto.setM_userpw(jsondata.get("m_userpw").toString());
		mDto.setM_nickname(jsondata.get("m_nickname").toString());
		mDto.setM_email(jsondata.get("m_email").toString());
		mDto.setM_birth(jsondata.get("m_birth").toString());
		mDto.setM_gender(jsondata.get("m_gender").toString());
		mDto.setM_interest(jsondata.get("m_interest").toString());
		
		////////////////////////////////////////////////
		
		//안드로이드로 반환해줄 값 
		JSONObject data = new JSONObject();
		MemberDAO mDao = MemberDAO.getInstance();
		int joinResult = mDao.userJoin(mDto);
		//DAO의 메소드반환값이 int인 1,0 으로 올거임.
		if(joinResult == 1) {
			data.put("result", "success");
			System.out.println("회원가입 성공");
		} else {
			data.put("result", "fail");
			System.out.println("회원가입 실패 (json통신실패)");
		}
		
		//안드로이드로 response값에 JSONObject 타입으로 data를 보내줌!
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(data);
		
		return null;
	}

}
