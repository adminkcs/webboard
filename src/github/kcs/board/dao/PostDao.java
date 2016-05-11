package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import github.kcs.board.vo.PostVO;

/**
 * posts 테이블에서 게시판 글을 조회하거나, 읽거나, 편집 및 삭제를 담당합니다.
 * @author wise-itech
 *
 */
public class PostDao {
	
	static {
		try {
//			Class.forName("org.mariadb.jdbc.Driver");
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<PostVO> samples = new ArrayList<>();
	{
		samples.add(new PostVO(1005, "가나다","본문", 3323, "2016-04-22", "James"));
		samples.add(new PostVO(1002, "가나다","본문", 3323, "2016-04-22", "James"));
		samples.add(new PostVO(1001, "가나다22","본문", 3323, "2016-04-22", "James"));
		samples.add(new PostVO(1000, "가나다323","본문", 3323, "2016-04-22", "James"));
	}
	
	/*
	 *  이렇게 커넥션을 얻어오는 코드를 Dao 안에 두지 않습니다.
	 *  보통은 외부에서 커넥션을 얻어오는 구현체를 삽입해줍니다.
	 */
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
	/**
	 * 
	 * @return
	 */
	public List<PostVO> findAll() {
		// FIXME 가짜로 넣어두고 시작합니다.
		String query = "select seq, title, content, viewcount, creationtime from posts order by creationtime desc";
		
		Connection con = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			List<PostVO> posts = new ArrayList<>();
			while(rs.next()){
				Integer seq = rs.getInt("seq");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Integer viewcount = rs.getInt("viewcount");
				String creationtime = rs.getString("creationtime");
				
				PostVO p = new PostVO(seq, title, content, viewcount, creationtime, "James");
				posts.add(p);
			}
			
			return posts;
		} catch (SQLException e) {
			throw new RuntimeException("fail to load", e);
		} finally {
			DBUtil.release(con, stmt, rs);
		}
		
//		return samples;
	}
	/**
	 * 주어진 글번호에 해당하는 post 를 반환합니다. 없으면 null을 반환합니다.
	 * @param seq
	 * @return
	 */
	public PostVO findBySeq ( Integer seq) {
		// select * from posts where seq = 1005
		String query = "select seq, title, content, viewcount, creationtime from posts where seq = ?";
		
		Connection con = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		PostVO p = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()){
//				seq = rs.getInt("seq");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Integer viewcount = rs.getInt("viewcount");
				String creationtime = rs.getString("creationtime");
				
				p = new PostVO(seq, title, content, viewcount, creationtime, "James");
				
			}
			
			return p;
		} catch (SQLException e) {
			throw new RuntimeException("fail to load", e);
		} finally {
			DBUtil.release(con, stmt, rs);
		}
	}
	
	public List<PostVO> findByCategory ( String category, int offset, int lenth ) {
		return null;
	}
}