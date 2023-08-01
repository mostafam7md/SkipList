import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntersectionTestTest {


	@Test
	public void test() {
		Database list = new Database();
	
		list.insert(new KVPair<>("r2", new Rectangle1(10, 10, 15, 15)) );
		
		list.insert(new KVPair<>("R2", new Rectangle1(11, 11, 5, 5)) );
		
		list.insert(new KVPair<>("r3", new Rectangle1(0, 0, 1000, 10)) );
		
		list.insert(new KVPair<>("r4", new Rectangle1(0, 0, 10, 1000)) );
		
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));

	        // Call the Intersection method
	      
	        list.intersections();

	        // Get the console output as a string
	        String output = outputStream.toString();

	        // Define the expected output
	        
	        String expectedOutput = "Intersections pairs:\n"
	        		+ "(R2, 11, 11, 5, 5 | r2, 10, 10, 15, 15)\n"
	        		+ "(r2, 10, 10, 15, 15 | R2, 11, 11, 5, 5)\n"
	        		+ "(r3, 0, 0, 1000, 10 | r4, 0, 0, 10, 1000)\n"
	        		+ "(r4, 0, 0, 10, 1000 | r3, 0, 0, 1000, 10)\r\n";

	        // Assert that the output matches the expected output
	        assertEquals(expectedOutput,output);
	        
	}
}
