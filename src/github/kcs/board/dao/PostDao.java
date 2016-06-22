package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import github.kcs.board.vo.CodeVO;
import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

/**
 * posts 테이블에서 게시판 글을 조회하거나, 읽거나, 편집 및 삭제를 담당합니다.
 * @author wise-itech
 *
 */
public class PostDao {
    
    private UserDao userDao; // = new UserDao();
    
    private DataSource ds;
    public PostDao ( UserDao userDao ) {
        this.userDao = userDao;
    }
    
    public PostDao (DataSource ds, UserDao userDao ) {
        this.userDao = userDao;
        this.ds = ds;
    }    
    /*
     *  이렇게 커넥션을 얻어오는 코드를 Dao 안에 두지 않습니다.
     *  보통은 외부에서 커넥션을 얻어오는 구현체를 삽입해줍니다.
     */
    /*
    private Connection getConnection () {
        String url = "jdbc:mysql://localhost:3306/boarddb";
        String user = "root";
        String password = "1234";
        
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con ;
        } catch (SQLException e) {
            throw new RuntimeException("fail to create jdbc connection", e);
        }
        
    }
    */
    /**
     * 
     * @return
     */
    public List<PostVO> findAll() {
        String query = "SELECT SEQ                 "
                     + "     , TITLE               "
                     + "     , CONTENT             "
                     + "     , VIEWCOUNT           "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER              "
                     + "     , CATEGORY              "
                     + "  FROM POSTS               "
                     + " ORDER BY CREATIONTIME DESC";
        
        Connection con =  null; //getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            List<PostVO> posts = new ArrayList<>();
            while(rs.next()){
                Integer seq = rs.getInt("seq");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Integer viewcount = rs.getInt("viewcount");
                String creationtime = rs.getString("creationtime");
                int category = rs.getInt("category");
                CodeVO cat = findCategory(con, category);
                
                UserVO writer = userDao.findBySeq(rs.getInt("writer"));
                PostVO p = new PostVO(seq, title, content, viewcount, creationtime, writer, cat);
                posts.add(p);
            }
            
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
        
//        return samples;
    }
    public List<PostVO> findByRange(int offset, int length, int codeNum) {
        String query = "SELECT SEQ                 "
                + "     , TITLE               "
                + "     , CONTENT             "
                + "     , VIEWCOUNT           "
                + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                + "     , WRITER              "
                + "     , CATEGORY              "
                + " FROM POSTS                "
                + " WHERE CATEGORY = ?         "
                + "ORDER BY CREATIONTIME DESC "
                + "LIMIT ?, ?                 ";

   Connection con =  null; //getConnection();
   PreparedStatement stmt = null;
   ResultSet rs  = null;
   try {
       con = ds.getConnection();
       stmt = con.prepareStatement(query);
       stmt.setInt(1, codeNum);
       stmt.setInt(2, offset);
       stmt.setInt(3, length);
       
       rs = stmt.executeQuery();
       List<PostVO> posts = new ArrayList<>();
       while(rs.next()){
           Integer seq = rs.getInt("seq");
           String title = rs.getString("title");
           String content = rs.getString("content");
           Integer viewcount = rs.getInt("viewcount");
           String creationtime = rs.getString("creationtime");
           int category = rs.getInt("category");
           CodeVO cat = findCategory(con, category);

           UserVO writer = userDao.findBySeq(rs.getInt("writer"));
           PostVO p = new PostVO(seq, title, content, viewcount, creationtime, writer, cat);
           posts.add(p);
       }
       
       return posts;
   } catch (SQLException e) {
       throw new RuntimeException("fail to load", e);
   } finally {
       DBUtil.release(con, stmt, rs);
   }        
    }
    
    public List<PostVO> findByRange( int offset, int length ) {
        String query = "SELECT SEQ                 "
                     + "     , TITLE               "
                     + "     , CONTENT             "
                     + "     , VIEWCOUNT           "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER              "
                     + "     , CATEGORY              "
                     + " FROM POSTS                "
                     + "ORDER BY CREATIONTIME DESC "
                     + "LIMIT ?, ?                 ";

        Connection con =  null; //getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, offset);
            stmt.setInt(2, length);
            
            rs = stmt.executeQuery();
            List<PostVO> posts = new ArrayList<>();
            while(rs.next()){
                Integer seq = rs.getInt("seq");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Integer viewcount = rs.getInt("viewcount");
                String creationtime = rs.getString("creationtime");
                int category = rs.getInt("category");
                CodeVO cat = findCategory(con, category);

                UserVO writer = userDao.findBySeq(rs.getInt("writer"));
                PostVO p = new PostVO(seq, title, content, viewcount, creationtime, writer, cat);
                posts.add(p);
            }
            
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }
    /**
     * 주어진 글번호에 해당하는 post 를 반환합니다. 없으면 null을 반환합니다.
     * @param seq
     * @return
     */
    public PostVO findBySeq ( Integer seq) {
        // select * from posts where seq = 1005
        String query = "SELECT SEQ               "
                     + "     , TITLE             "
                     + "     , CONTENT           "
                     + "     , VIEWCOUNT         "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER            "
                     + "     , CATEGORY            "
                     + "  FROM POSTS             "
                     + "  WHERE SEQ = ?          ";
         

        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        PostVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, seq);
            rs = stmt.executeQuery();
            if(rs.next()){
//                seq = rs.getInt("seq");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Integer viewcount = rs.getInt("viewcount");
                String creationtime = rs.getString("creationtime");
                Integer categoryFK = rs.getInt("category");
                
                UserVO writer = this.userDao.findBySeq(rs.getInt("writer"));
                CodeVO category = findCategory (con, categoryFK);
                p = new PostVO(seq, title, content, viewcount, creationtime, writer, category);
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }
    
    private CodeVO findCategory(Connection con, Integer categoryFK) {
        String query = "Select * from codes where cd_dvs_id = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CodeVO p = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, categoryFK);
            rs = stmt.executeQuery();
            if(rs.next()){
//                seq = rs.getInt("seq");
                int cdDvsId = rs.getInt("CD_DVS_ID");
                String cdNm = rs.getString("CD_NM");

                p = new CodeVO(cdDvsId, cdNm);
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(null, stmt, rs);
        }
    }

    public List<CodeVO> findAllCategory() {
        String query = "SELECT CD_DVS_ID "
        		+ "           ,CD_NM"
        		+ "       FROM CODES "
        		+ "      ORDER BY CD_DVS_ID";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CodeVO p = null;
        try {
             con = ds.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            List<CodeVO> categorys = new ArrayList<>();
            while (rs.next()){
//                seq = rs.getInt("seq");
                int cdDvsId = rs.getInt("CD_DVS_ID");
                String cdNm = rs.getString("CD_NM");

                p = new CodeVO(cdDvsId, cdNm);
                categorys.add(p);
            }
            return categorys;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }
    
    public void insertPost ( String title, String content, int seq, int category) {
        String query = "INSERT INTO POSTS (TITLE, CONTENT, WRITER, CATEGORY) "
                     + "VALUES (?,?,?,?)                             "; // inser, update, delete
        
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PostVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, seq);
            stmt.setInt(4, category);
            stmt.executeUpdate();
//            if ( nInserted < 1) {
//                throw new SQLException("쓰기 실패. 글 안들어갔습니다.");
//            }
            
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
        }
    }
    public void updatePost(String title, String content, int seq, int category) {
        String query = "UPDATE POSTS         "
                     + "SET TITLE = ?        "
                     + "  , CONTENT = ?      "
                     + "  , CATEGORY = ?      "
                     + "WHERE SEQ = ?        "; // inser, update, delete
        
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PostVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, category);
            stmt.setInt(4, seq);
            int nInserted = stmt.executeUpdate();
            System.out.println(title +"//"+content+"//"+category+"//"+ seq);
//            if ( nInserted < 1) {
//                throw new SQLException("쓰기 실패. 글 안들어갔습니다.");
//            }
            
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
        }        
    }
    public void deletePost(int seq) {
        String query = "DELETE FROM POSTS "
                     + "WHERE SEQ = ?     "; // inser, update, delete
        
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PostVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, seq);
            stmt.executeUpdate();
//            int nInserted = stmt.executeUpdate();
//            if ( nInserted < 1) {
//                throw new SQLException("쓰기 실패. 글 안들어갔습니다.");
//            }
            
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
        }        
    }

    public List<PostVO> findByCategory ( String category, int offset, int lenth ) {
        return null;
    }

    public void updateViewCount(Integer postSeq, int viewCount) {
        String query = "UPDATE POSTS         "
                     + "   SET VIEWCOUNT = ? "
                     + " WHERE SEQ = ?       "; // inser, update, delete
    
    Connection con = null;   //getConnection();
    PreparedStatement stmt = null;
    PostVO p = null;
    try {
        con = ds.getConnection();
        stmt = con.prepareStatement(query);
        stmt.setInt(2, postSeq);
        stmt.setInt(1, viewCount);
        stmt.executeUpdate();
        
    } catch (SQLException e) {
        throw new RuntimeException("fail to load", e);
    } finally {
        DBUtil.release(con, stmt, null);
    }        
        
    }

    public int countPage() {
        String query = "select count(seq) from posts";

        Connection con =  null; //getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
            
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }
    
    /**
     * 특정 카테고리의 글의 갯수를 반환합니다.
     * @param codeNum 카테고리의 pk 
     * @return
     */
    public int countPage(int codeNum) {
        String query = "select count(seq) from posts ";
        
        if ( codeNum >= 0 ) {
            query +=   " where category = ?" ;
        }

        Connection con =  null; //getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            
            if ( codeNum >= 0 ) {
                stmt.setInt(1, codeNum);                
            }
            
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
            
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }

    


}
