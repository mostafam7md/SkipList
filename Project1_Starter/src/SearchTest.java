import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchTest {


	@Test
	public void test()
	 
	{
	  Database List = new Database();
	  List.insert(new KVPair<>("r1", new Rectangle1(20, 20, 35, 35)) );

	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	  
	  System.setOut(new PrintStream(outputStream));
	
	  List.search("r1");

	  String output = outputStream.toString();
	  
	  assertEquals("Rectangles found: \r\n(r1, 20, 20, 35, 35)\r\n",output);

	    
	}

}
