package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.PostVO;

public class PageEditAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PostDao postDao = (PostDao) context.getAttribute("postDao");
        String pnum = req.getParameter("pnum");
        System.out.println("edit_pnum: " + pnum);
        PostVO post = null;
        try {            
            post = postDao.findBySeq ( Integer.parseInt(pnum) );
            req.setAttribute("p", post);
        } catch( NumberFormatException e ) {
            e.printStackTrace();
        }
        
        return "forward:/WEB-INF/edit.jsp";
    }

}
