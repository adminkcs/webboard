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

public class PageWriteAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PostDao postDao = (PostDao) context.getAttribute("postDao");
        List<CodeVO> code = null;
        try {            
            code = postDao.findAllCategory(); 
            req.setAttribute("codes", code); // ${codes}
        } catch( NumberFormatException e ) {
            e.printStackTrace();
        }
        
        return "forward:/WEB-INF/write.jsp";
    }

}
