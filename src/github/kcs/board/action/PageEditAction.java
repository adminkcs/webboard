package github.kcs.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.CodeVO;
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
            List<CodeVO> codes = postDao.findAllCategory();
            req.setAttribute("p", post);
            req.setAttribute("codes", codes);
        } catch( NumberFormatException e ) {
            e.printStackTrace();
        }
        
        return "forward:/WEB-INF/edit.jsp";
    }

}
