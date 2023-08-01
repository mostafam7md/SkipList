import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DumpTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testDumpEmpty() {
		SkipList skip = new SkipList();
		ArrayList<String> testDumpList = new ArrayList<String>();
		ArrayList<String> Result = skip.dump();
		testDumpList.add("SkipList dump:");
		testDumpList.add("Node has depth 1, Value (null)");
		testDumpList.add("SkipList size is: 0");
		assertEquals(testDumpList, Result);
	}

}
