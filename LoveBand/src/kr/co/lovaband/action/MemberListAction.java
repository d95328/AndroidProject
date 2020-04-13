package kr.co.lovaband.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		List<MemberDTO> memberList = mDao.listMember();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
//		for(MemberDTO dto : memberList) {
//			JSONObject data = new JSONObject();
//			data.put("m_userid", dto.getM_userid());
//			data.put("m_nickname", dto.getM_nickname());
//			data.put("m_email", dto.getM_email());
//			data.put("m_gender", dto.getM_gender());
//			data.put("m_birth", dto.getM_birth());
//			data.put("m_joindate", dto.getM_joindate());
//			data.put("m_interest", dto.getM_interest());
//			
//			jsonArray.add(data);
//		}
		
		//JSON 포매팅 람다식 표현 사용
		memberList.forEach(dto -> {
			JSONObject data = new JSONObject();
			data.put("m_userid", dto.getM_userid());
			data.put("m_nickname", dto.getM_nickname());
			data.put("m_email", dto.getM_email());
			data.put("m_gender", dto.getM_gender());
			data.put("m_birth", dto.getM_birth());
			data.put("m_joindate", dto.getM_joindate());
			data.put("m_interest", dto.getM_interest());
			
			jsonArray.add(data);
		});	
		
		//			      key   	value
		jsonObject.put("members", jsonArray);
		
		// 값 확인하려면 url에 http://192.168.0.104:81/LoveBand/AndroidLoveBandJsonAction.love
		System.out.println("jsonObject ===> " + jsonObject );
		response.setContentType("application/x-json; charset=UTF-8");
		
		response.getWriter().print(jsonObject);
		
		return null;
	}

}
