package github.kcs.board.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import github.kcs.board.BoardContext;
import github.kcs.board.action.EditAction;
import github.kcs.board.action.FileDownloadAction;
import github.kcs.board.action.DeleteAction;
import github.kcs.board.action.IAction;
import github.kcs.board.action.JoinAction;
import github.kcs.board.action.LoginAction;
import github.kcs.board.action.PageEditAction;
import github.kcs.board.action.PageListAction;
import github.kcs.board.action.PageReadAction;
import github.kcs.board.action.PageWriteAction;
import github.kcs.board.action.WriteAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet( urlPatterns = {    "/login"          //로그인   (로그인화면으로 이동)
                              , "/doLogin"    //로그인   (로그인 기능 /로그인 후 리스트로 이동)
                              , "/logout"      //로그아웃 (로그아웃 기능 /로그아웃 후 리스트로 이동)
                              , "/list"         //리스트   (리스트 화면으로 이동)
                              , "/read"        //상세보기 (게시글 상세화면으로 이동) 
                              , "/write"        //글쓰기   (게시글 글쓰기 화면으로 이동)
                              , "/doWrite"      //글쓰기     (게시글 저장/저장후 리스트로 이동)    
                              , "/edit"        //글수정   (게시글 글수정 화면으로 이동)
                              , "/doEdit"       //글수정     (게시글 수정기능 / 수정 후 상세보기 화면으로 이동) 저장 후 상세 화면으로 이동하지 않음
                              , "/delete"       //글삭제   (게시글 글삭제 /삭제 후 리스트로 이동)   
                              , "/join"        //회원가입 (회원가입화면으로 이동)
                              , "/doJoin"      //회원가입 (회원가입 가입 후 웰컴화면으로 이동)
                              , "/f/*"    
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
        BoardContext btx = (BoardContext) context.getAttribute("BOARD_CTX");
        
        if ( uri.equals("/list")) {
            /*
             * 게시판 리스트를 보여줍니다.
             */
            IAction listAction = new PageListAction();
            String nextUrl = listAction.proccess(btx, request, response);
            moveNext(request, response, nextUrl);
        } else if ( uri.equals("/write")) {
            /*
             * 글쓰기 화면으로 이동
             */
            IAction writeAction = new PageWriteAction();
            String nextUrl = writeAction.proccess(btx, request, response);
            moveNext(request, response, nextUrl);
        } else if(uri.equals("/login")){
            /*
             * 로그인 화면으로 이동
             * FIXME 만일 현재 사용자가 이미 로그인을 했다면 아래와 같이 로그인 페이지로 포워등을 하면 안됩니다. 
             */
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } else if(uri.equals("/logout")){
            HttpSession session = request.getSession();
            session.invalidate(); // 세션을 날려버립니다. 세션은 진짜 필요한 정보만 담아야 합니다.
//            request.getRequestDispatcher("WEB-INF/views/list.jsp").forward(request, response);
            response.sendRedirect("list");
        } else if ( uri.equals("/edit")) {
            /*
             * 게시판 수정 화면으로 이동합니다.
             */
            PageEditAction edit = new PageEditAction();
            String nextUrl = edit.proccess(btx, request, response);
            moveNext (request, response, nextUrl );
            
        }  else if ( uri.equals("/join")) {
            /*
             * 글쓰기 화면으로 이동
             */
            request.getRequestDispatcher("WEB-INF/join.jsp").forward(request, response);                
            
        }  else if ( uri.startsWith("/f/")) {
            System.out.println("여기는 오나?");
            // /f/322232
            String nextUrl = new FileDownloadAction().proccess(btx, request, response);
            moveNext(request, response, nextUrl);
            
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
        BoardContext btx = (BoardContext) context.getAttribute("BOARD_CTX");

        //doGet(request, response);
        if ( "/doWrite".equals(uri) ) {
            /*
             * 게시글 글쓰기 저장
             */
            IAction action = new WriteAction();
            String nextUrl = action.proccess(btx, request, response);
            moveNext(request, response, nextUrl);
            
        }else if(uri.equals("/doLogin")){
            /*
             * 로그인을 합니다.
             * 톰캣을 클러스터링으로 구성할때가 있습니다.
             */

            IAction action = new LoginAction();
            String nextUrl = action .proccess(btx, request, response);
            moveNext(request, response, nextUrl);
    
        } else if ( uri.equals("/read")) {
            /*
             * 게시글 상세화면으로 이동합니다.
             */
            PageReadAction read = new PageReadAction();
            String nextUrl = read.proccess(btx, request, response);
            moveNext (request, response, nextUrl );
            
        } else if ( uri.equals("/doJoin")) {
            /*
             * 회원가입(등록)
             */
            JoinAction action = new JoinAction();
            String nextUrl = action.proccess(null, request, response);
            moveNext (request, response, nextUrl );
            
        } else if ( "/doEdit".equals(uri) ) {
            /*
             * 게시글 수정
             */
            IAction action = new EditAction();
            String nextUrl = action.proccess(btx, request, response);
            moveNext(request, response, nextUrl);
            
        } else if ( "/delete".equals(uri) ) {
            /*
             * 게시글 글삭제 
             */
            IAction action = new DeleteAction();
            String nextUrl = action.proccess(btx, request, response);
            moveNext(request, response, nextUrl);
        }
        
        else {
            response.getWriter().append("Unknown URI: ").append(request.getContextPath());
        }
    }

    private void moveNext(HttpServletRequest request, HttpServletResponse response, String nextUrl) throws ServletException, IOException {
        if( nextUrl == null) {
            return ;
        }
        if ( nextUrl.startsWith("forward:")){
            request.getRequestDispatcher( nextUrl.substring("forward:".length()).trim() ).forward(request, response);                
        } else if ( nextUrl.startsWith("redirect:")) {
            response.sendRedirect(nextUrl.substring("redirect:".length()).trim());
        } else {
            System.out.println("what is this?: " + nextUrl);
        }
        
    }

}
