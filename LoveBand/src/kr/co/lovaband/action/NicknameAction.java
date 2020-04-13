package kr.co.lovaband.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.lovaband.common.JsonParser;
import kr.co.lovaband.dao.MemberDAO;
import kr.co.lovaband.dto.MemberDTO;

public class NicknameAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JsonParser().jsonParser(request.getReader());
		String nickname = jsonObject.get("m_nickname").toString();
		
		MemberDTO memberDTO = new MemberDTO();
		JSONObject data = new JSONObject();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDTO = memberDAO.userNicknameCheck(nickname);
		
		if( memberDTO != null) {
			data.put("result", "fail");
			System.out.println("이미 사용중인 닉네임" + data);
		} else {
			data.put("result", "success");
			System.out.println("사용 가능한 닉네임" + data);
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(data);
		
		return null;
	}

}
