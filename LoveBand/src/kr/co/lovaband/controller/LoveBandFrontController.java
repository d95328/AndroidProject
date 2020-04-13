package kr.co.lovaband.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.lovaband.action.Action;
import kr.co.lovaband.action.ActionForward;
import kr.co.lovaband.action.EmailCheckAction;
import kr.co.lovaband.action.IdCheckAction;
import kr.co.lovaband.action.JoinAction;
import kr.co.lovaband.action.LoginAction;
import kr.co.lovaband.action.MemberListAction;
import kr.co.lovaband.action.NicknameAction;
import kr.co.lovaband.action.UpdateAction;


@WebServlet("/LoveBandFrontController")
public class LoveBandFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoveBandFrontController() {
    	
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//캐릭터셋
		request.setCharacterEncoding("UTF-8");
		
    	Action action = null;
    	ActionForward forward = null;
    	
    	String uri = request.getRequestURI();
    	String ctx = request.getContextPath();
       	String command = uri.substring(ctx.length());
       	
//       	System.out.println("uri ===> " + uri);
//       	System.out.println("ctx ===> " + ctx);
//       	System.out.println("command ===> " + command);
       	
       	//안드로이드가 쏴줄 URL 위치 
       	if (command.equals("/MemberList.love")) {
       		action = new MemberListAction();
       		forward = action.excute(request, response);
		} else if (command.equals("/Login.love")) {
			//로그인요청
//			http://192.168.0.104:81/LoveBand/Login.love?m_userid=downey&m_userpw=0000
			action = new LoginAction();
			forward = action.excute(request, response);
		} else if (command.equals("/Join.love")) {
			//회원가입요청
			action = new JoinAction();
			forward = action.excute(request, response);
		} else if (command.equals("/Update.love")) {
			//정보수정요청
			action = new UpdateAction();
			forward = action.excute(request, response);
		} else if (command.equals("/idCheck.love")) {
			//아이디중복검사요청
			action = new IdCheckAction();
			forward = action.excute(request, response);
		} else if (command.equals("/emailCheck.love" )) {
			//이메일중복검사요청
			action = new EmailCheckAction();
			forward = action.excute(request, response);
		} else if (command.equals("/nicknameCheck.love")) {
			//닉네임중복검사요청
			action = new NicknameAction();
			forward = action.excute(request, response);
		}
       	
       	
       	
       	/*공통 분기 작업*/
       	if (forward != null) {
       		if(forward.isRedirect()) {
       			response.sendRedirect(forward.getPath());
       		} else {
       			RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
       			rd.forward(request, response);
       		}
       	}
       	
	}

}
