package github.kcs.board.dao;

import java.util.List;

import github.kcs.board.vo.CodeVO;
import github.kcs.board.vo.PostVO;

public interface IPostDao {

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
    List<PostVO> findAll();

    List<PostVO> findByRange(int offset, int length, int codeNum);

    List<PostVO> findByRange(int offset, int length);

    /**
     * 주어진 글번호에 해당하는 post 를 반환합니다. 없으면 null을 반환합니다.
     * @param seq
     * @return
     */
    PostVO findBySeq(Integer seq);

    List<CodeVO> findAllCategory();

    PostVO insertPost(PostVO newPost);

    PostVO insertPostInternal(PostVO post);

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
    void updatePost(String title, String content, int seq, int category);

    void deletePost(int seq);

    List<PostVO> findByCategory(String category, int offset, int lenth);

    void updateViewCount(Integer postSeq, int viewCount);

    int countPage();

    /**
     * 특정 카테고리의 글의 갯수를 반환합니다.
     * @param codeNum 카테고리의 pk 
     * @return
     */
    int countPage(int codeNum);

    List<PostVO> findBySearch(String[] columns, String sw);

    void reply(Integer parentSeq, PostVO reply);

}