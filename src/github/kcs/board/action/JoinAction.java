package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.UserDao;

public class JoinAction implements IAction {

	@Override
	public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext ctx  = req.getServletContext();
		
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		/*
		 * 1. 아이디 중복 여부
		 * 
		 * 2. 이메일 검증코드
		 * 
		 * 3. 비번 검증 코드
		 * 
		 */
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		System.out.println(id + ", " + pass);
		userDao.insertUser(id, pass);
		
		//    /success
		return "forward:/WEB-INF/welcome.jsp";
	}

}
