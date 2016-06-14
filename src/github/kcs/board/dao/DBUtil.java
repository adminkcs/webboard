package github.kcs.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DBUtil {

    public static void release ( Connection con, PreparedStatement stmt, ResultSet rs){
        try {
            if ( rs != null ) 
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if ( stmt != null )
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if ( con != null ) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
