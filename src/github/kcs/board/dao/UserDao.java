package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

public class UserDao implements IUserDao {

    private DataSource ds;
    private SqlSessionFactory factory;
    
    public UserDao ( DataSource ds) {
        this.ds = ds;
    }
    public UserDao(DataSource ds, SqlSessionFactory fac) {
        this.ds = ds;
        this.factory = fac;
    }
    /**
     * 모든 게시판 사용자들을 불러옵니다.
     * @return
     */
    @Override
    public List<UserVO> findAll() {
        return null;
    }
    
    /**
     * 주어진 id와 password 에 일치하는 사용자 정보를 읽어서 반환합니다.
     * @param id
     * @param password
     * @return
     */
    @Override
    public UserVO login ( String id, String password) {
        SqlSession session = factory.openSession();
        try {
            IUserDao userDao = session.getMapper(IUserDao.class);
            UserVO user = userDao.login(id, password);
            return user;
        } finally {
            session.close();
        }
    }
    
    /**
     * 특정 사용자를 찾습니다.
     * @param userSeq
     * @return
     */
    @Override
    public UserVO findBySeq ( Integer userSeq) {
        SqlSession session = factory.openSession();
        try {
            IUserDao userDao = session.getMapper(IUserDao.class);
            UserVO user = userDao.findBySeq(userSeq);
            return user;
        } finally {
            session.close();
        }
        
       /* String query = "SELECT SEQ         "
                     + "     , ID          "
                     + "     , PASSWORD    "
                     + "     , JOINDATE    "
                     + "  FROM USERS       "
                     + " WHERE SEQ = ?     ";
        
        Connection con = null ; 
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        UserVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, userSeq);
            rs = stmt.executeQuery();
            if(rs.next()){
//                seq = rs.getInt("seq");
                Integer seq = rs.getInt("seq");
                String  id = rs.getString("id");
                String password = rs.getString("password");
                String joindate = rs.getString("joindate");

                p = new UserVO(seq, id, password, joindate );
                
            }
            
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }*/
    }
    
    @Override
    public void insertUser ( String name, String password) {
        SqlSession session = factory.openSession();
        try {
            IUserDao userDao = session.getMapper(IUserDao.class);
            userDao.insertUser(name, password);
            session.commit();
        } finally {
            session.close();
        }
        /*
        String query = "insert into users (id,password) values(?,?) "; // inser, update, delete
        
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PostVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
        }
        */
    }
}
