package where;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWhere {

    @Test
    public void test_one_column() {
        String [] columns = new String[]{"title"};
        
        String where = whereQuery(columns);
        assertEquals ( "WHERE TITLE LIKE ?", where.trim().toUpperCase());
        
        columns = new String[]{"content"};
        where = whereQuery(columns);
        assertEquals ( "WHERE CONTENT LIKE ?", where.trim().toUpperCase());
    }
    
    @Test
    public void test_two_columns() {
        String [] columns = new String[]{"title", "content", "category"};
        
        String where = whereQuery(columns);
        assertEquals ( "WHERE TITLE LIKE ? OR CONTENT LIKE ? OR CATEGORY LIKE ?", where.trim().toUpperCase());
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

}
