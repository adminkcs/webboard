package github.kcs.board.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import github.kcs.board.dao.PostDao;

/**
 * Application Lifecycle Listener implementation class BoardInitListener
 *
 */
@WebListener
public class BoardInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BoardInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * 웹애플리케이션을 내린 후에 호출해 줍니다.
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("board 웹 애플리케이션이 종료되었습니다.");
    }

	/**
     * 웹애플리션이 준비된 후에 톰캣에서 한번 호출해줍니다.
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("board 웹 애플리케이션이 시작되었습니다.");
    	
    	ServletContext sc = sce.getServletContext();
    	/* dao 초기화 로직 */
    	initDao ( sc );
    	
    	
    }

	private void initDao(ServletContext sc) {
		PostDao postDao = new PostDao();
		sc.setAttribute("postDao", postDao);
		
	}
	
}
