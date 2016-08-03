package mybatis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import github.kcs.board.dao.IUserDao;
import github.kcs.board.vo.UserVO;

public class MybatisTest {

    private static SqlSessionFactory factory;

    @BeforeClass
    public static void setup() throws IOException {
        /*
         * 1. 설정 파일에서 세션 팩토리 준비하기
         *    session factory - 진입점 ?  
         */
        String mybatisXml = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(mybatisXml);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(reader);
        
        /*
         * 2. insert, update, delete, select
         *    SQLSession - mybatis-config.xml에 설정된 디비 접속정보를 통해서 트랙잭션을 열고 connection을 얻어옴.
         */
        /*
        SqlSession session = factory.openSession(false);
        
        IUserDao userDao = session.getMapper(IUserDao.class); 
        
        // @Param ("uid") String id, @Param("pw") String password
        // Map < String, Object> map = new HashMap();
        // mp.put ( "uid", "james");
        // map.put( "pw", "1111");
        UserVO user = userDao.login("james", "1111");
        assertNotNull(user);
        assertEquals ( "james", user.getId() );
        assertEquals ( "1111", user.getPassword());
        
        session.commit();
        session.close();
        
        */
        
    }
    
    @Test
    public void login() {
        SqlSession session = factory.openSession(false);
        
        IUserDao userDao = session.getMapper(IUserDao.class); 
        
        // @Param ("uid") String id, @Param("pw") String password
        // Map < String, Object> map = new HashMap();
        // mp.put ( "uid", "james");
        // map.put( "pw", "1111");
        UserVO user = userDao.login("james", "1111");
        assertNotNull(user);
        assertEquals ( "james", user.getId() );
        assertEquals ( "1111", user.getPassword());
        
        session.commit();
        session.close();
    }
    
    @Test
    public void findBySeq() {
        Integer userseq = 2;
        
        SqlSession session = factory.openSession(false);
        IUserDao dao = session.getMapper(IUserDao.class);
        UserVO haha = dao.findBySeq(userseq);
        assertEquals ( "hahaha", haha.getId() );
        session.close();
    }
    
    @Test
    public void test_insert_new_user() {
        UserVO newUser = new UserVO("mybatis", "2222");
        
        SqlSession session = factory.openSession(false);
        IUserDao dao = session.getMapper(IUserDao.class);
       
        dao.insertUser(newUser.getId(), newUser.getPassword());
        session.commit();
        
        session.close();
        
    }

}
