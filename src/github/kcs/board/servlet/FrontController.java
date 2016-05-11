package github.kcs.board.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import github.kcs.board.action.PageListAction;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.PostVO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet( urlPatterns = {"/login", "/list", "/read", "/edit", "/delete"} )
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctxpapth = request.getContextPath();
		String fullPath = request.getRequestURI();
		String uri = fullPath.substring(ctxpapth.length());
		
		ServletContext context = request.getServletContext();
		PostDao postDao = (PostDao) context.getAttribute("postDao");
		if ( uri.equals("/list")) {
			/*
			 * 게시판 리스트를 보여줍니다.
			 */
			PageListAction listAction = new PageListAction();
			listAction.process(request, response);
		} else if ( uri.equals("/read")) {
			String pnum = request.getParameter("pnum");
			
			PostVO post = null;
			try {
				int postSeq = 1;
				postSeq = Integer.parseInt(pnum);				
				post = postDao.findBySeq ( Integer.parseInt(pnum) );
			} catch( NumberFormatException e) {
				
			}
			request.setAttribute("p", post);
			request.getRequestDispatcher("WEB-INF/read.jsp").forward(request, response);
		} else if ( uri.equals("/edit")) {
			;
		} else if ( uri.equals("/delete")) {
			;
		}
		
		else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("what?" + request.getRequestURI());
		doGet(request, response);
	}

}
