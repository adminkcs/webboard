package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.IPostDao;

public class DeleteAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    ServletContext context = req.getServletContext();
	    IPostDao postDao = (IPostDao) context.getAttribute("postDao");
		
	    int seq = Integer.parseInt(req.getParameter("pnum"));  
	    postDao.deletePost ( seq );
//		res.sendRedirect("list");

	    return "redirect:" + req.getContextPath() + "/list";
	}

}
