package where;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;

import org.apache.catalina.tribes.util.Arrays;
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
    
    @Test
    public void test_match_query() {
        String [] columns = new String[]{"title"};
        String [] words = new String []{"감자"};
        String query = whereMatchQuery(columns, words);
        
        assertEquals ( "where match(title) against('감자*' in boolean mode)", query );
        
        columns = new String[]{"title", "content"};
        words = new String[]{"감자", "고구마"};
        query = whereMatchQuery(columns, words);
        assertEquals ( "where match(title,content) against('감자*고구마*' in boolean mode)", query );
        
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
    
    @Test
    public void test_parseWord() {
        String input = " \t감자 \t \n고구마   \n";
        String [] words = parseWords(input);
        System.out.println(Arrays.toString(words));
        assertArrayEquals (new String[]{"감자", "고구마"},words );
    }
    
    private String[] parseWords(String sw) {
        String word[] = sw.trim().split("\\s+"); 
        return word;
    }

}
