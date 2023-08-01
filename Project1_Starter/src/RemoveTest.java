import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoveTest {


	@Test
	public void test() {
		
		Database list = new Database();

		list.insert(new KVPair<>("r2", new Rectangle1(10, 10, 15, 15)) );

		 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	       
		 System.setOut(new PrintStream(outputStream));

	        list.remove("r2");
	     
	        String output = outputStream.toString();
	        
	        String expectedOutput = "Rectangle removed: (r2, 10, 10, 15, 15)\n";
	        
	        assertEquals(expectedOutput,output);

		
	}
}
