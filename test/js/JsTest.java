package js;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class JsTest {

    @Test
    public void test() {
        HashMap<Object, Object> user = new HashMap<>();
        user.put("seq", 100);
        user.put("id", "jane");
        user.put("password", "1111");
        
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(100);
        nums.get(0);
        
    }

}
