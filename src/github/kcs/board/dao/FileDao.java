package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import github.kcs.board.vo.CodeVO;
import github.kcs.board.vo.FileVO;
import github.kcs.board.vo.PostVO;

public class FileDao {
    
    private DataSource ds;
    
    public FileDao (DataSource ds) {
        this.ds = ds;
    }

    public void insertFile(FileVO f, Integer postPK) {
        String query = "INSERT INTO FILES ( originFileName , uniqueFileName , fileSize , posting ) VALUES  (?,?,?,?) "; 
   
       Connection con = null;   //getConnection();
       PreparedStatement stmt = null;
       
       try {
           con = ds.getConnection();
           stmt = con.prepareStatement(query);
           stmt.setString(1, f.getOriginFileName());
           stmt.setString(2, f.getUniqueFileName());
           stmt.setLong(3, f.getFileSize());
           stmt.setInt(4, postPK);
           stmt.executeUpdate();

       } catch (SQLException e) {
           throw new RuntimeException("fail to load", e);
       } finally {
           DBUtil.release(con, stmt, null);
       }       
        
    }

    public FileVO findByPost(Integer postPK) {
        String query = "SELECT seq, originFileName, uniqueFileName, fileSize, posting FROM FILES WHERE posting = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FileVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, postPK);
            rs = stmt.executeQuery();
            if(rs.next()){
                int seq = rs.getInt("SEQ");
                String originFileName = rs.getString("ORIGINFILENAME");
                String uniqueFileName = rs.getString("uniqueFileName");
                long fileSize = rs.getInt("fileSize");

                p = new FileVO(seq, originFileName, uniqueFileName, fileSize);
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }

    public FileVO findBySeq(Integer pk) {
        String query = "SELECT seq, originFileName, uniqueFileName, fileSize, posting FROM FILES WHERE seq = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FileVO p = null;
        try {
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, pk);
            rs = stmt.executeQuery();
            if(rs.next()){
                int seq = rs.getInt("SEQ");
                String originFileName = rs.getString("ORIGINFILENAME");
                String uniqueFileName = rs.getString("uniqueFileName");
                long fileSize = rs.getInt("fileSize");

                p = new FileVO(seq, originFileName, uniqueFileName, fileSize);
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
    }

}
