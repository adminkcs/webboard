package pagenation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ListIteration {

    @Test
    public void test() {
        List<String> list = Arrays.asList("Ab", "CC", "Bx");
        Iterator<String> itr  = list.iterator();
        while ( itr.hasNext()) {
            String s = itr.next();
            System.out.println( s );
        }
        
        for ( String s : list) {
            System.out.println(s);
        }
    }

}
