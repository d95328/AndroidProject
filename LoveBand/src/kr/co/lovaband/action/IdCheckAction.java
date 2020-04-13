package kr.co.lovaband.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonData = new JsonParser().jsonParser(request.getReader());
		String m_userid = jsonData.get("m_userid").toString();
		
		MemberDTO memberDTO = new MemberDTO();
		JSONObject responeseData = new JSONObject();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDTO = memberDAO.userIdCheck(m_userid);
		
		if( memberDTO != null) {
			responeseData.put("result", "fail");
			System.out.println("이미 사용중인 아이디");
		} else {
			responeseData.put("result", "success");
			System.out.println("사용 가능한 아이디");
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(responeseData);
		
		return null;
	}

}
