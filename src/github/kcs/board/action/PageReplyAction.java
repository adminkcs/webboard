package github.kcs.board.action;

import static github.kcs.board.util.WebUtil.Int;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.vo.PostVO;

public class PageReplyAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        IPostDao postDao = (IPostDao) context.getAttribute("postDao");
        
        
        PostVO post = postDao.findBySeq ( Int(req, "pnum", 1) );
        
        req.setAttribute("parent", post);
        return "forward:/WEB-INF/reply.jsp";
    }

}
