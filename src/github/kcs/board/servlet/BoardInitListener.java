package github.kcs.board.servlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import github.kcs.board.dao.PostDao;
import github.kcs.board.dao.UserDao;

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
    	
    	DataSource ds = initDataSource();
    	initDao ( sc, ds );
    	
    	
    }

	private DataSource initDataSource() {
		DataSource ds = null;
		Context rootCtx;
		System.out.println("trying to get jdbc connection");
		try {
			rootCtx = new InitialContext();
			Context env     = (Context) rootCtx.lookup("java:/comp/env");
			ds   = (DataSource) env.lookup("jdbc/boardDS");
			
			Connection con = ds.getConnection();
			System.out.println("실제 클래스: " + con.getClass().getName());
			con.close();
			
			System.out.println("Datasource config OK!");
			return ds;
		} catch (NamingException | SQLException e) {
			throw new RuntimeException("DataSource 설정 실패", e);
		}
	}

	private void initDao(ServletContext sc, DataSource ds) {
		/* 사용자 dao 호출 */
		UserDao userDao = new UserDao(ds);
		sc.setAttribute("userDao",  userDao);
		
		/* 게시판 dao 호출 */
		PostDao postDao = new PostDao(ds, userDao);
		sc.setAttribute("postDao", postDao);
		
//		NoteDao noteDao = new NoteDao ( userDao );
		System.out.println("DAO 설정 성공");
	}
	
}
