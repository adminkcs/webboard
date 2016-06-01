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
		
		
		return "/WEB-INF/welcome.jsp";
	}

}
