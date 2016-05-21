package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

public class UserDao {

	private DataSource ds;
	
	public UserDao ( DataSource ds) {
		this.ds = ds;
	}
	/**
	 * 모든 게시판 사용자들을 불러옵니다.
	 * @return
	 */
	public List<UserVO> findAll() {
		return null;
	}
	
	/**
	 * 주어진 id와 password 에 일치하는 사용자 정보를 읽어서 반환합니다.
	 * @param id
	 * @param password
	 * @return
	 */
	public UserVO login ( String id, String password) {
		
		String query = "SELECT SEQ           "
				     + "     , ID            "
				     + "     , PASSWORD      "
				     + "     , JOINDATE      "
				     + "  FROM USERS         "
				     + " WHERE ID = ?        "
				     + "   AND PASSWORD = ?  ";
		
		Connection con = null ; 
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		UserVO p = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next()){
//				seq = rs.getInt("seq");
				Integer seq = rs.getInt("seq");
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
	/**
	 * 특정 사용자를 찾습니다.
	 * @param userSeq
	 * @return
	 */
	public UserVO findBySeq ( Integer userSeq) {
		String query = "SELECT SEQ         "
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
