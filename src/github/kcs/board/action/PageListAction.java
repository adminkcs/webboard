package github.kcs.board.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
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
		PostDao dao = (PostDao) req.getServletContext().getAttribute("postDao");
		String cname = req.getParameter("c");
		
		if ( cname == null ) {
			cname = "free";
		}
//		int pnum = Integer.parseInt(req.getParameter("pnum"));
		/*
		 * 1단계 - 글 자체에 대해 페이지내이션 적용
		 */
		int pnum = WebUtil.Int(req, "pnum", 1);
		int T = btx.getDefaultPageSize() ;  // 한페이지당 보여줄 글의 갯수
		int N = dao.countPage();
		int offset = -1;
		int length = -1;
		offset = (pnum -1) * T;
		length = T;
		int totalPage = N / T + ( N % T > 0 ? 1 : 0);
		
		/*
		 * 2단계 - 페이지 번호들에 대해서 페이지내이션을 재적용
		 *          일단은 모든 페이지 번호를 다 찍어줍니다.
		 */
		List<Integer> pageNums = new ArrayList<>();
		
		for (int i = 1; i <= totalPage; i++) {
			pageNums.add(i);
		}
		req.setAttribute("category", cname);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("curPage", pnum);
		req.setAttribute("pageNums", pageNums);
		req.setAttribute("allPosts", dao.findByRange(offset, length));
		return "forward:/WEB-INF/views/list.jsp";
	}
}
