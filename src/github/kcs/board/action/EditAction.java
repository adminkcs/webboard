package github.kcs.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.UserVO;

public class EditAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        PostDao postDao = (PostDao) context.getAttribute("postDao");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String category = req.getParameter("category");
		
        //글쓴이를 변조할수 있기에 세션에서 받아옵니다
        HttpSession session = req.getSession(); 
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        postDao.insertPost ( title, content, loginUser.getSeq(), category );

        return "redirect:" + req.getContextPath() + "/list";		

    }

}
