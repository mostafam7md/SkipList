import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegoinSearchTest {

	@Test
	public void test() {
		Database list = new Database();
	
		list.insert(new KVPair<>("r5", new Rectangle1(20, 12, 3, 3)) );
		
		
		
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));

	        // Call the Intersection method
	      
	        list.regionsearch(22, 14, 2, 2);;

	        // Get the console output as a string
	        String output = outputStream.toString();

	        // Define the expected output
	        
	        String expectedOutput = "Rectangles intersecting region (22, 14, 2, 2):\r\n"
	        		+ "(r5, 20, 12, 3, 3)\r\n";
	        // Assert that the output matches the expected output
	        assertEquals(expectedOutput,output);
	        

}
}
