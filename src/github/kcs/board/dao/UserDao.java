package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

public class UserDao {

	/**
	 * 모든 게시판 사용자들을 불러옵니다.
	 * @return
	 */
	public List<UserVO> findAll() {
		return null;
	}
	
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
	 * 특정 사용자를 찾습니다.
	 * @param userSeq
	 * @return
	 */
	public UserVO findBySeq ( Integer userSeq) {
		String query = "select seq, id, password, joindate from users where seq = ?";
		
		Connection con = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		UserVO p = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, userSeq);
			rs = stmt.executeQuery();
			if(rs.next()){
//				seq = rs.getInt("seq");
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
		}
	}
}
