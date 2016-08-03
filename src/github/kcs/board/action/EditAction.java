package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.util.WebUtil;
import github.kcs.board.vo.UserVO;

public class EditAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        IPostDao postDao = (IPostDao) context.getAttribute("postDao");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int seq = WebUtil.Int(req, "pnum", 0);
        int category = WebUtil.Int(req, "category", 1);
		
        //글쓴이를 변조할수 있기에 세션에서 받아옵니다
//        HttpSession session = req.getSession(); 
//        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        postDao.updatePost ( title, content, seq, category );

        return "redirect:" + req.getContextPath() + "/list";		

    }

}
