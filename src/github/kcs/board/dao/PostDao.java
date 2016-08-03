package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import github.kcs.board.vo.CodeVO;
import github.kcs.board.vo.FileVO;
import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

/**
 * posts 테이블에서 게시판 글을 조회하거나, 읽거나, 편집 및 삭제를 담당합니다.
 * @author wise-itech
 *
 */
public class PostDao implements IPostDao {
    
    private IUserDao userDao; // = new UserDao();
    
    private DataSource ds;

    private FileDao fileDao;
    public PostDao ( IUserDao userDao ) {
        this.userDao = userDao;
    }
    
    public PostDao (DataSource ds, IUserDao userDao, FileDao fileDao ) {
        this.userDao = userDao;
        this.fileDao = fileDao;
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
    @Override
    public List<PostVO> findAll() {
        String query = "SELECT SEQ                 "
                     + "     , TITLE               "
                     + "     , CONTENT             "
                     + "     , VIEWCOUNT           "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER              "
                     + "     , CATEGORY              "
                     + "     , GNUM                "
                     + "     , PARENT              "
                     + "     , ODRNUM              "
                     + "     , INDENT              "
                     + "  FROM POSTS               "
                     + " ORDER BY GNUM DESC, ODRNUM ASC ";
        
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
    @Override
    public List<PostVO> findByRange(int offset, int length, int codeNum) {
        String query = "SELECT SEQ                 "
                + "     , TITLE               "
                + "     , CONTENT             "
                + "     , VIEWCOUNT           "
                + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                + "     , WRITER              "
                + "     , CATEGORY              "
                + "     , GNUM                "
                + "     , PARENT              "
                + "     , ODRNUM              "
                + "     , INDENT              "
                + " FROM POSTS                "
                + " WHERE CATEGORY = ?         "
                + "ORDER BY GNUM DESC, ODRNUM ASC "
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
           p.setGroupNum( rs.getInt("gnum"));
           p.setParentNum(rs.getInt("parent"));
           p.setOrderNum( rs.getInt("odrnum"));
           p.setIndentation( rs.getInt("indent"));
           posts.add(p);
       }
       
       return posts;
   } catch (SQLException e) {
       throw new RuntimeException("fail to load", e);
   } finally {
       DBUtil.release(con, stmt, rs);
   }        
    }
    
    @Override
    public List<PostVO> findByRange( int offset, int length ) {
        String query = "SELECT SEQ                 "
                     + "     , TITLE               "
                     + "     , CONTENT             "
                     + "     , VIEWCOUNT           "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER              "
                     + "     , CATEGORY              "
                     + "     , GNUM                "
                     + "     , PARENT              "
                     + "     , ODRNUM              "
                     + "     , INDENT              "
                     + " FROM POSTS                "
                     + " ORDER BY GNUM DESC, ODRNUM ASC  "
                     + " LIMIT ?, ?                 ";

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
                p.setGroupNum( rs.getInt("gnum"));
                p.setParentNum(rs.getInt("parent"));
                p.setOrderNum( rs.getInt("odrnum"));
                p.setIndentation( rs.getInt("indent"));
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
    @Override
    public PostVO findBySeq ( Integer seq) {
        // select * from posts where seq = 1005
        String query = "SELECT SEQ               "
                     + "     , TITLE             "
                     + "     , CONTENT           "
                     + "     , VIEWCOUNT         "
                     + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                     + "     , WRITER            "
                     + "     , CATEGORY            "
                     + "     , GNUM                "
                     + "     , PARENT              "
                     + "     , ODRNUM              "
                     + "     , INDENT              "
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
                p.setGroupNum( rs.getInt("gnum"));
                p.setParentNum(rs.getInt("parent"));
                p.setOrderNum( rs.getInt("odrnum"));
                p.setIndentation( rs.getInt("indent"));
                
                FileVO attached = fileDao.findByPost( seq ) ;
                p.setAttachedFile(attached); 
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

    @Override
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
    @Override
    public PostVO insertPost ( PostVO newPost) {
        
//        newPost = insertPost(newPost.getTitle(),newPost.getContent(),newPost.getWriter().getSeq(),newPost.getCategory().getCdDvsId());
        newPost = insertPostInternal(newPost);
        FileVO f = newPost.getAttachedFile();
        fileDao.insertFile ( f, newPost.getSeq() );
        return newPost;
    }
    @Override
    public PostVO insertPostInternal (  PostVO post ) {
        String query = "     INSERT INTO POSTS (TITLE, CONTENT, WRITER, CATEGORY, gnum, parent, odrnum, indent) "
                     +"VALUES (?, ?, ?, ?, ?, ?, ?, ?)                              "; // inser, update, delete
        
        String updateQuery = "update posts set gnum = ?, parent = ? where seq = ? ";
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        
//        PostVO p = new PostVO(null, title, content, 0, null, null, null);
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getWriter().getSeq());
            stmt.setInt(4, post.getCategory().getCdDvsId());
            
            Integer gnum = post.getGroupNum() ;
            Integer parent = post.getParentNum();
            
            stmt.setInt(5, gnum == null ? 0 : gnum );
            stmt.setInt(6, parent == null ? 0 : parent);
            stmt.setInt(7, post.getOrderNum());
            stmt.setInt(8, post.getIndentation());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            Integer pk = rs.getInt(1); 
            post.setSeq(pk);

            if ( gnum == null ) {
                /*
                 * 이제 여기서 DB가 생성한 PK값을 얻어내서 설정해줘야 합니다.
                 */
                
                stmt2 = con.prepareStatement(updateQuery);
                stmt2.setInt(1, pk);
                stmt2.setInt(2, pk);
                stmt2.setInt(3, pk);
                stmt2.executeUpdate();
                
                
            }
            return post;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
            DBUtil.release(null, stmt2, null);
        }
    }
    /*
    public PostVO insertPost ( String title, String content, int seq, int category) {
        String query = "     INSERT INTO POSTS (TITLE, CONTENT, WRITER, CATEGORY, gnum, parent, odrnum, indent) "
                     +"VALUES (?, ?, ?, ?, 0, 0, 0, 0)                              "; // inser, update, delete
        
        String updateQuery = "update posts set gnum = ?, parent = ? where seq = ? ";
        Connection con = null;   //getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        
        PostVO p = new PostVO(null, title, content, 0, null, null, null);
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS );
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, seq);
            stmt.setInt(4, category);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            Integer pk = rs.getInt(1); 
            p.setSeq(pk);
            
            System.out.println("PK : " + pk);
            stmt2 = con.prepareStatement(updateQuery);
            stmt2.setInt(1, pk);
            stmt2.setInt(2, pk);
            stmt2.setInt(3, pk);
            stmt2.executeUpdate();
            
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, null);
            DBUtil.release(null, stmt2, null);
        }
    }
    */
    @Override
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
    @Override
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

    @Override
    public List<PostVO> findByCategory ( String category, int offset, int lenth ) {
        return null;
    }

    @Override
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

    @Override
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
    @Override
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

    @Override
    public List<PostVO> findBySearch(String [] columns, String sw) {
        String baseQuery = "SELECT SEQ            "
                + "     , TITLE               "
                + "     , CONTENT             "
                + "     , VIEWCOUNT           "
                + "     , DATE_FORMAT(CREATIONTIME, '%Y년%m월%d일%H시%i분%S초') CREATIONTIME"
                + "     , WRITER              "
                + "     , CATEGORY              "
                + "  FROM POSTS               "
                + "  {where} "
                + " ORDER BY CREATIONTIME DESC";
       String [] words = parseWords ( sw );
       String query = baseQuery.replace("{where}", whereMatchQuery( columns, words ));
       
       System.out.println("Q: " + query );
       
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
    }

    private String[] parseWords(String sw) {
        String word[] = sw.trim().split("\\s+"); 
        return word;
    }

    private String whereQuery ( String [] columns ) {
        String where = "where ";
        for(int i=0; i< columns.length; i++){
            if(i==0){
                where += columns[i] + " LIKE ? ";                
            }else{
                where += "OR "+ columns[i] + " LIKE ? ";                
            }
        }
        return where;
    }
    
    private String whereMatchQuery ( String [] columns, String[] words ) {
        // words = new String[]{"감자", 고구마}
        // where match(title, content) against('감자* 고구마*' in boolean mode)
        String where = "where match({c}) against('{s}' in boolean mode)";
        
        String cpart = "";
        for(int i=0; i < columns.length; i++){
            cpart += columns[i]+",";
        }
        //title,content,
        int end = cpart.lastIndexOf(',');
        cpart = cpart.substring(0, end);
        where = where.replace("{c}", cpart);

        String dpart = "";
        for(int i=0; i < words.length; i++){
            dpart += words[i]+"*";
        }
        where = where.replace("{s}", dpart);
        return where;
    }

    @Override
    public void reply ( Integer parentSeq, PostVO reply ) {
        PostVO parent = findBySeq(parentSeq);
        
        reply.setGroupNum(parent.getGroupNum() );
        reply.setParentNum(parent.getSeq());
        reply.setOrderNum(parent.getOrderNum() + 1);
        reply.setIndentation(parent.getIndentation() + 1);
        reply.setCategory(parent.getCategory());
        
        /*
         *  order number 를 재배치 해줍니다.
         *  
         *  현재 부모글의 order number보다 큰 애들의 order 값을 1씩 증가시켜줍니다.
         *  update 
         */
        
        String orderQuery = "update posts set odrnum = odrnum + 1 where gnum = ? and odrnum > ?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con =ds.getConnection();
            stmt = con.prepareStatement(orderQuery);
            stmt.setInt(1, parent.getGroupNum());
            stmt.setInt(2, parent.getOrderNum());
            stmt.executeUpdate();
            
            insertPost(reply);
        } catch (SQLException  e ) {
            throw new RuntimeException("fail to reply", e);
        } finally {
            DBUtil.release(con, stmt, null);
        }
        
        
    }


}
