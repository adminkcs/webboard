package github.kcs.board.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import github.kcs.board.action.PageListAction;
import github.kcs.board.dao.PostDao;
import github.kcs.board.dao.UserDao;
import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet( urlPatterns = {    "/login"	//로그인   (로그인화면으로 이동)
							  , "/doLogin"	//로그인   (로그인 기능 /로그인 후 리스트로 이동)
							  , "/logout"	//로그아웃 (로그아웃 기능 /로그아웃 후 리스트로 이동)
							  , "/list"		//리스트   (리스트 화면으로 이동)
							  , "/read"		//상세보기 (게시글 상세화면으로 이동) 
							  , "/write" 	//글쓰기   (게시글 글쓰기 화면으로 이동)
							  , "/doWrite"	//글쓰기	 (게시글 저장/저장후 리스트로 이동)	
							  , "/edit"		//글수정   (게시글 글수정 화면으로 이동)
							  , "/delete"	//글삭제   (게시글 글삭제 /삭제 후 리스트로 이동)   
							  , "/doEdit"	//글수정	 (게시글 수정기능 / 수정 후 상세보기 화면으로 이동) 저장 후 상세 화면으로 이동하지 않음
						  } )
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();       
    }

	/**
	 * GET : 시스템에 별다른 변경을 일으키지 않는 읽기용 요청이인 경우
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctxpapth = request.getContextPath();
		String fullPath = request.getRequestURI();
		String uri = fullPath.substring(ctxpapth.length());
		
		ServletContext context = request.getServletContext();
		PostDao postDao = (PostDao) context.getAttribute("postDao");
		UserDao userDao = (UserDao) context.getAttribute("userDao"); //userDao를 BoardInitListener에서 선언 
		if ( uri.equals("/list")) {
			/*
			 * 게시판 리스트를 보여줍니다.
			 */
			PageListAction listAction = new PageListAction();
			listAction.process(request, response);
		} else if ( uri.equals("/write")) {
			/*
			 * 글쓰기 화면으로 이동
			 */
			UserVO fakeUser = new UserVO("fake", "1111");
			request.getRequestDispatcher("WEB-INF/write.jsp").forward(request, response);
		} else if(uri.equals("/login")){
			/*
			 * FIXME 만일 현재 사용자가 이미 로그인을 했다면 아래와 같이 로그인 페이지로 포워등을 하면 안됩니다. 
			 */
			
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else if(uri.equals("/logout")){
			HttpSession session = request.getSession();
			session.invalidate(); // 세션을 날려버립니다. 세션은 진짜 필요한 정보만 담아야 합니다.
//			request.getRequestDispatcher("WEB-INF/views/list.jsp").forward(request, response);
			response.sendRedirect("list");
		} else if ( uri.equals("/edit")) {
			/*
			 * 게시판 수정 화면으로 이동합니다.
			 */
			String pnum = request.getParameter("pnum");
			System.out.println("edit_pnum: " + pnum);
			PostVO post = null;
			try {			
				post = postDao.findBySeq ( Integer.parseInt(pnum) );
			} catch( NumberFormatException e) {
				e.printStackTrace();
			}
			request.setAttribute("p", post);
			request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request, response);
		} else if ( "/delete".equals(uri) ) {
			/*
			 * 게시글 글삭제 
			 */
			int seq = Integer.parseInt(request.getParameter("pnum"));  
			postDao.deletePost ( seq );
			response.sendRedirect("list");
		}
		
		else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
		}
	}

	/**
	 * POST 시스템의 상태가 변경되는 요청인 경우( 글쓰기, 편집, 삭제 insert, udpate, delete)
	 * PUT
	 * DELETE
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 첫번재 방식 ( 직접 코드로 인코딩을 지정해줍니다.)
		 * "제목"(UTF-8) -> ISO-8859-1
		 * 0x43 0xf3 0x35 0x55
		 */
		request.setCharacterEncoding("utf-8");
		String ctxpapth = request.getContextPath();
		String fullPath = request.getRequestURI();
		String uri = fullPath.substring(ctxpapth.length());
		
		ServletContext context = request.getServletContext();
		PostDao postDao = (PostDao) context.getAttribute("postDao");
		UserDao userDao = (UserDao) context.getAttribute("userDao");
		//doGet(request, response);
		if ( "/doWrite".equals(uri) ) {
			/*
			 * 게시글 글쓰기 저장
			 */
			// 실제 글쓴 내용이 옵니다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			//Q&A 2016.05.19 형변환을 해야 하는 이유를 모르겠습니다.
			int seq = Integer.parseInt(request.getParameter("seq"));  
			postDao.insertPost ( title, content, seq );
			/*
			 * 2. db로 새글을 입력합니다.
			 */
			response.sendRedirect(request.getContextPath() + "/list"); // 클라이언트한테 저 url로 다시 가라고 함.
		}else if(uri.equals("/doLogin")){
			/*
			 * 로그인 화면으로 이동합니다.
			 * 톰캣을 클러스터링으로 구성할때가 있습니다.
			 */
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			UserVO loginUser = userDao.login ( id, password );
			System.out.println(loginUser);
			// session에다가 담아둘겁니다.
			if ( loginUser != null ) {
				/*
				 * 1. 로그인이 성공했으면
				 */
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				
				response.sendRedirect(request.getContextPath()+"/list");
			} else {
				response.sendRedirect(request.getContextPath()+"/login");
				
			}
			/*
			 * 2. 실패했으면 ?
			 */
		} else if ( uri.equals("/read")) {
			/*
			 * 게시글 상세화면으로 이동합니다.
			 */
			String pnum = request.getParameter("pnum");
			System.out.println("read_pnum: " + pnum);
			PostVO post = null;
			try {
//				int postSeq = 1;/
//				postSeq = Integer.parseInt(pnum);				
				post = postDao.findBySeq ( Integer.parseInt(pnum) );
			} catch( NumberFormatException e) {
				e.printStackTrace();
			}
			request.setAttribute("p", post);
			request.getRequestDispatcher("WEB-INF/read.jsp").forward(request, response);
		} else if ( "/doEdit".equals(uri) ) {
			/*
			 * 게시글 글수정 저장
			 */
			// 실제 글쓴 내용이 옵니다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("pnum"));  
			postDao.updatePost ( title, content, seq );
			/*
			 * 2. db로 새글을 입력합니다.
			 */
			PostVO post = null;
			try {
				post = postDao.findBySeq (seq);
			} catch( NumberFormatException e) {
				e.printStackTrace();
			}
			request.setAttribute("p", post);
			request.getRequestDispatcher("WEB-INF/read.jsp").forward(request, response);
		}
		
		else {
			response.getWriter().append("Unknown URI: ").append(request.getContextPath());
		}
	}

}
