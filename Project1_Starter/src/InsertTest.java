import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InsertTest {

	Database list = new Database();

	
	@Test
	public void testInsertInvalidName() {
		String s = list.insert(new KVPair<String,Rectangle1>("1r_r", new Rectangle1(6,2,1,4)));
		assertEquals(s,"Rectangle rejected: (1r_r, 6, 2, 1, 4)");
	}
	@Test
	public void testInsertInvalidWidth() {
		String s = list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(6,2,1026,4)));
		assertEquals(s,"Rectangle rejected: (a, 6, 2, 1026, 4)");
	}
	@Test
	public void testInsertInvalidHeight() {
		String s = list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(50,100,200,2000)));
		assertEquals(s,"Rectangle rejected: (a, 50, 100, 200, 2000)");
	}
	@Test
	public void testInsertInvalidCoordinates() {
		String s = list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(-1,100,200,200)));
		assertEquals(s,"Rectangle rejected: (a, -1, 100, 200, 200)");
	}
	@Test
	public void testInsertInvalidCoordinatesb() {
		String s = list.insert(new KVPair<String,Rectangle1>("b", new Rectangle1(-1,-50,200,200)));
		assertEquals(s,"Rectangle rejected: (b, -1, -50, 200, 200)");
	}
	@Test
	public void testInsertValid() {
		String s = list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(1,50,200,200)));
		assertEquals(s,"Rectangle inserted: (a, 1, 50, 200, 200)");
	}
	@Test
	public void testInsertValidButRepeated() {
		list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(1,50,200,200)));
		String s = list.insert(new KVPair<String,Rectangle1>("a", new Rectangle1(1,50,200,200)));
		assertEquals(s,null);
	}
}
