package pagenation;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageTest {

	@Test
	public void test() {
		int N = 11;
		int T = 4;
		// ABCDEFGH IJK
		int P = N / T + ( N % T > 0 ? 1 : 0);
		assertEquals(3, P);
		
		/*
		 * 1페이지의 범위
		 * ABCD
		 * offset : 0,
		 *  lenth : 4
		 *  
		 * 2페이지의 범위
		 * EFGH
		 * offset : 4
		 * length : 4
		 * 
		 * 3. 페이지
		 * 
		 * offset : 8
		 * length : 4
		 * 
		 * 페이지 번호를 x 라고 하면 ( /list?pnum=3
		 * 
		 * offset : (x-1)  * T
		 * length : T
		 */
		
	}

}
