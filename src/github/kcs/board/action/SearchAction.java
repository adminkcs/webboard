package github.kcs.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.util.WebUtil;
import github.kcs.board.vo.PostVO;

public class SearchAction implements IAction {

    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PostDao dao = (PostDao) req.getServletContext().getAttribute("postDao");
        String sw   = req.getParameter("sw"); // 감자 고구마  => 감자, 고구마]
        int tnum  = WebUtil.Int(req, "type", 1);
        
        String [] columns = chooseColumns ( tnum );
        System.out.println("type number: " + tnum  +  Arrays.toString(columns));
        
        List<PostVO> posts = dao.findBySearch(columns, sw);
        req.setAttribute("seachWord", sw);
        req.setAttribute("allPosts", posts );
        return "forward:/WEB-INF/views/search.jsp";
    }

    private String[] chooseColumns(int tnum) {
        final int COL_TITLE = 1;
        final int COL_CONTENT = 2;
        /*
         * 1. 비트 연산으로 필요한 컬럼들만 추려냅니다.
         */
        List<String> cols = new ArrayList<>();
        if ( (tnum & COL_TITLE) != 0 ) {
            cols.add("title");
        }
        if ( (tnum & COL_CONTENT) != 0 ) {
            cols.add("content");
        }
        /*
         * 2. ArrayList에 있는 element들을 raw array로 반환합니다.
         */
        String [] columns = cols.toArray(new String[0]);
        return columns;
    }

}
