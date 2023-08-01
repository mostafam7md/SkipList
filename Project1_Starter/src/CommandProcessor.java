/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
    	
    	//Replace multiple white spaces in a line with a single white space
        // s+ is a regular expression that means more than one space
    	 line = line.replaceAll("\\s+", " ");
    	 
    	
    	//Split string line as an array of words 
    	 String [] words = line.split(" ");
    	     	     
    	 //It points to the keyword function what will be excuted in the database class
    	 String keyword = words[0];
    	 
    	//create object of rectangle with the values specified in each line in the file and send it to method insert in database
     	if(keyword.equals("insert")) {
     		Rectangle1 R = new Rectangle1(0, 0, 0, 0);
     		R.x = Integer.parseInt(words[2]);
     		R.y = Integer.parseInt(words[3]);
     		R.w = Integer.parseInt(words[4]);
     		R.h = Integer.parseInt(words[5]);
     		
     	//bind each rectangle object with a key(name) by creating a new object of KVPair
     		Database.insert(new KVPair<String, Rectangle1>(words[1], R));     		
     	}
     	
     	// Remove by dimensions
     	//Check if the length of the array is more than two and if true, which means that we Remove by values not name
     	else if(keyword.equals("remove")&& words.length>2) {
     		Database.remove(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),Integer.parseInt(words[4]));
     	}
     	
     	//Remove by name 
     	else if(keyword.equals("remove")) {Database.remove(words[1]);} 
     	
     	//Redirect line to fun dump in Database class
         else if(keyword.equals("dump")) { Database.dump();}
     	
     	//Redirect line to fun region search in Database class
         else if(keyword.equals("regionsearch")) {
     		Database.regionsearch(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),Integer.parseInt(words[4]));
     	}
     	
     	//Redirect line to fun search in Database class
         else if (keyword.equals("search")) {Database.search(words[1]);}
     	
     	//Redirect line to fun intersections in Database class
         else if (keyword.equals("intersections")) {Database.intersections();}
     	
     	//Print if the input invalid or blank line
         else  { System.out.println("Invalid"); }   	
    }
    	
}
