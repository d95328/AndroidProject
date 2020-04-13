package kr.co.lovaband.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class EmailCheckAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JSONObject jsonData = new JsonParser().jsonParser(request.getReader());
		String m_email = jsonData.get("m_email").toString();
		
		MemberDTO memberDTO = new MemberDTO();
		JSONObject responeseData = new JSONObject();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDTO = memberDAO.userEmailCheck(m_email);
		
		if( memberDTO != null) {
			responeseData.put("result", "fail");
			System.out.println("이미 사용중인 이메일" + responeseData);
		} else {
			responeseData.put("result", "success");
			System.out.println("사용 가능한 이메일" + responeseData);
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(responeseData);
		
		
		return null;
	}

}
