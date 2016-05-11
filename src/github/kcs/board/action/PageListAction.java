package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.dao.PostDao;

public class PageListAction {
	
//	private PostDao dao = new PostDao() ;
	
	public void process ( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PostDao dao = (PostDao) req.getServletContext().getAttribute("postDao");
		String cname = req.getParameter("c");
		
		if ( cname == null ) {
			cname = "free";
		}
		
		req.setAttribute("category", cname);
		req.setAttribute("allPosts", dao.findAll());
		req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, res);
	}
}
