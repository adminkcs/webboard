package github.kcs.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.util.WebUtil;

public class PageListAction implements IAction {
    
    /*
     * 전체 글의 갯수 N ( 11 )
     * 
     * 한페이지당 보여줄 글의 갯수 T ( 4 )
     * 
     * 전체 페이지 갯수 P = N / T + ( N % T > 0 ? 1 : 0 )
     */
    @Override
    public String proccess ( BoardContext btx, HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        IPostDao dao = (IPostDao) req.getServletContext().getAttribute("postDao");
        String cname = req.getParameter("c"); // 숫자
        
        if ( cname == null ) {
            cname = "";
        }
        /*
         * 1단계 - 글 자체에 대해 페이지내이션 적용
         */
        int pnum = WebUtil.Int(req, "pnum", 1);
        int T = btx.getDefaultPageSize() ;  // 한페이지당 보여줄 글의 갯수
        int N = countPage (dao,  cname ); 
        int [] pgn = getPagenation(N, T, pnum);
        int totalPage = N / T + ( N % T > 0 ? 1 : 0);
        
        /*
         * 2단계 - 페이지 번호들에 대해서 페이지내이션을 재적용
         *          일단은 모든 페이지 번호를 다 찍어줍니다.
         */
        List<Integer> pageNums = new ArrayList<>();
        
        for (int i = 1; i <= totalPage; i++) {
            pageNums.add(i);
        }
        
        req.setAttribute("prePage", cname);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("curPage", pnum);
        req.setAttribute("pageNums", pageNums);
        
        /*
         * 3단계 - 카테고리 필터
         */
        
        if ( "".equals(cname) ) {
            req.setAttribute("allPosts", dao.findByRange(pgn[0], pgn[1]));
        } else {
            int codeNum = WebUtil.Int(req, "c", 0);
            req.setAttribute("allPosts", dao.findByRange(pgn[0], pgn[1], codeNum));
        }
        return "forward:/WEB-INF/views/list.jsp";
    }
    

    private int countPage(IPostDao dao, String paramCode) {
        int codeNum = -1 ;
        
        try {
            codeNum = Integer.parseInt(paramCode);
        } catch ( NumberFormatException e) {
            ;
        }
        return dao.countPage(codeNum);
    }


    /**
     * 
     * @param N 전체 글 갯수
     * @param T 한페이당 보여질 글의 갯수
     * @param pnum 보려고 하는 페이지 번호
     * @return offset과 length 를 나타내는 길이가 2인 배열
     */
    int [] getPagenation ( int N, int T, int pnum ) {
        int [] pgn = new int[2]; // 0번째값은 offset, 1번째값은 length
        int offset = (pnum -1) * T;
        int length = T;
        return new int[]{offset, length} ;
    }
    
    /**
     * 전체 페이지 갯수를 구합니다.
     * @param nPost 전체 글의 갯수
     * @param T 한페이당 보여질 글의 갯수
     * @return 전체 페이지 갯수
     */
    int countTotalPage ( int nPost, int T) {
        return nPost / T + ( nPost % T > 0 ? 1 : 0);
    }
}
