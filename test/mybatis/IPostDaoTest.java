package mybatis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import github.kcs.board.dao.IPostDao;
import github.kcs.board.vo.PostVO;

public class IPostDaoTest {

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
        
    }
    
    
    @Test
    public void test_list_all_post() {
        SqlSession session = factory.openSession(false);
        
        IPostDao postDao = session.getMapper(IPostDao.class); 
        List<PostVO> all = postDao.findAll();
        for ( PostVO p : all) {
            System.out.println(p);
        }
        
        session.commit();
        session.close();
    }

}
