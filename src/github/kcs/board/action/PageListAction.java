package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;

public class PageListAction implements IAction {
	
//	private PostDao dao = new PostDao() ;
	
	/* (non-Javadoc)
	 * @see github.kcs.board.action.IAction#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String proccess ( BoardContext btx, HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		PostDao dao = (PostDao) req.getServletContext().getAttribute("postDao");
		String cname = req.getParameter("c");
		
		if ( cname == null ) {
			cname = "free";
		}
		
		req.setAttribute("category", cname);
		req.setAttribute("allPosts", dao.findAll());
		return "forward:/WEB-INF/views/list.jsp";
	}
}
