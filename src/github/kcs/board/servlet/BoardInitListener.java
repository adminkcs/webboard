package github.kcs.board.servlet;

import java.io.IOException;
import java.io.Reader;
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

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.FileDao;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.dao.IUserDao;
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
        BoardContext btx = new BoardContext();
        
        /* dao 초기화 로직 */
        
        DataSource ds = initDataSource();
        initMybatisConfig ( btx, ds );
        initDao ( sc, btx, ds );
        
        /* pagenation: 한페이지에 10개씩 */
        btx.setDefaultPageSize(10);
    }

    private void initMybatisConfig(BoardContext btx, DataSource ds) {
//        SqlSessionFactoryBuilder builder = null;
        String mybatisXml = "mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(mybatisXml);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(reader);
            
            btx.setMybatisFactory ( factory );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
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

    private void initDao(ServletContext sc, BoardContext btx, DataSource ds) {
        SqlSessionFactory fac = btx.getMybatisFactory();
        
        FileDao fileDao = new FileDao(ds);
        btx.setFileDao (fileDao);
        /* 사용자 dao 호출 */
        
        IUserDao userDao = new UserDao(ds, fac);
        btx.setUserDao ( userDao );
        
        sc.setAttribute("userDao",  userDao);
        
        /* 게시판 dao 호출 */
        IPostDao postDao = new PostDao(ds, userDao, fileDao);
        btx.setPostDao( postDao );
        
        sc.setAttribute("postDao", postDao);
        
//        NoteDao noteDao = new NoteDao ( userDao );
        
        sc.setAttribute("BOARD_CTX", btx);
        
        System.out.println("DAO 설정 성공");
    }
    
}
