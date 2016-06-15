package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import github.kcs.board.vo.CodeVO;

/**
 * 코드 정보 조회,수정,삭제를 담당합니다.
 * @author kcs
 *
 */
public class CodeDao {
    private CodeDao codeDao;
    
    private DataSource ds;
    public CodeDao (CodeDao codeDao) {
        this.codeDao = codeDao;
    }
    
    public CodeDao (DataSource ds, CodeDao codeDao) {
        this.codeDao = codeDao;
        this.ds = ds;
    }

    
    public CodeVO findByCode (String codeDvsId) {
        
        String query = " SELECT CD_DVS_ID      "
                   + "      , CD_ID           "
                   + "      , CD_NM          "
                   + "  FROM CODES          "
                   + "  WHERE CD_DVS_ID = ?  ";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CodeVO vo = null;
        
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, codeDvsId);
            rs = stmt.executeQuery();
            if(rs.next()){
                String cdDvsId = rs.getString("CD_DVS_ID");
                String cdId = rs.getString("CD_ID");
                String cdNm = rs.getString("CD_NM");
                
                vo = new CodeVO(cdDvsId, cdId, cdNm);
            }
            return vo;
            
        } catch (SQLException e){
            throw new RuntimeException("fail to load", e);
        } finally {
            DBUtil.release(con, stmt, rs);
        }
                 
    }
}
