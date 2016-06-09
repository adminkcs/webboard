package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.UserDao;
import github.kcs.board.vo.UserVO;

public class LoginAction implements IAction {

	@Override
	public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		UserDao userDao = (UserDao) context.getAttribute("userDao");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		UserVO loginUser = userDao.login ( id, password );
		System.out.println(loginUser);
		// session에다가 담아둘겁니다.
		if ( loginUser != null ) {
			/*
			 * 1. 로그인이 성공했으면 리스트로 이동
			 */
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", loginUser);
			
			return "redirect:" + req.getContextPath() + "/list";
		} else {
			/*
			 * 2. 로그인 실패시 로그인 페이지로 다시 이동
			 */
			return "redirect:" + req.getContextPath() + "/login";
		}
		
	}

}
