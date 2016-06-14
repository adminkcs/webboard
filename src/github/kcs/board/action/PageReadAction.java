package github.kcs.board.action;

import static github.kcs.board.util.WebUtil.Int;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.PostVO;

public class PageReadAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PostDao postDao = (PostDao) context.getAttribute("postDao");
        
        
        PostVO post = postDao.findBySeq ( Int(req, "pnum", 1) );
        
        postDao.updateViewCount ( Int(req, "pnum", 1), post.getViewCount() + 1 );
        
        req.setAttribute("p", post);
        return "forward:/WEB-INF/read.jsp";
    }
}
