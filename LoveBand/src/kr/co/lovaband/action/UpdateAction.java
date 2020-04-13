package kr.co.lovaband.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class UpdateAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonData = new JsonParser().jsonParser(request.getReader());
		MemberDTO mDto = new MemberDTO();
		
		mDto.setM_nickname(jsonData.get("m_nickname").toString());
		mDto.setM_email(jsonData.get("m_email").toString());
		mDto.setM_interest(jsonData.get("m_interest").toString());
		mDto.setM_userid(jsonData.get("m_userid").toString());
		
		System.out.println(mDto.getM_nickname());
		System.out.println(mDto.getM_email());
		System.out.println(mDto.getM_interest());
		System.out.println(mDto.getM_userid());
		
		JSONObject data = new JSONObject();
		MemberDAO mDao = MemberDAO.getInstance();
		int updateResult = mDao.userUpdate(mDto);
		
		if(updateResult == 1) {
			data.put("result", "success");
			System.out.println("정보수정 완료");
		} else {
			data.put("result", "fail");
			System.out.println("정보수정 실패 (json통신실패)");
		}
		
		//안드로이드로 response값에 JSONObject 타입으로 data를 보내줌!
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(data);
		
		return null;
	}

}
