import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {
	
    // Variables MAX_WIDTH and MAX_HEIGHT specifies the area of the world box which is 1024*1024
    static final int MAX_WIDTH = 1024;
    static final int MAX_HEIGHT = 1024;

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private static SkipList<String, Rectangle1> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle1>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
public static String insert(KVPair<String, Rectangle1> inputPair) {
        
    	Rectangle1 rectangleObjectToBeInserted = inputPair.getValue(); //Get the rectangle object and through pair.getValue() and pass it to rectanglToInsert
    	String rectangleNameToBeInserted = inputPair.getKey(); //Get the rectangle name and through pair.getKet() and pass it to rectangleName	    
    	Iterator<KVPair<String, Rectangle1>> iter1 = list.iterator(); //creating object iter1 of Iterator class
    	  	
    	/*
	 *Loop on the list of rectangles to know if the rectangle name and coordinates exactly exists in the list
    	 *if The rectangle exists with the same name and coordinates, then ignore the insertion operation
	 */
      	while (iter1.hasNext()) {
              KVPair <String, Rectangle1> pairToBeCompared = iter1.next(); //creating object iter1 of Iterator class
              Rectangle1 rectangleToBeCompared = pairToBeCompared.getValue();
              String rectangleToBeComparedName = pairToBeCompared.getKey();
		
		//if the inserted rectangle exists before, return and ignore the insertion operation
              if(rectangleToBeComparedName.equals(rectangleNameToBeInserted) && rectangleToBeCompared.x == rectangleObjectToBeInserted.x && rectangleToBeCompared.y == rectangleObjectToBeInserted.y && rectangleToBeCompared.w == rectangleObjectToBeInserted.w && rectangleToBeCompared.h == rectangleObjectToBeInserted.h) {
            	
            	  return null;
              }
      	}
    	    	
    	//Check if the first letter sides between 'A' and 'Z or between 'a' and 'z'
    	if ((int)rectangleNameToBeInserted.charAt(0)>=65 && (int)rectangleNameToBeInserted.charAt(0)<=95 || (int)rectangleNameToBeInserted.charAt(0)>=97 && (int)rectangleNameToBeInserted.charAt(0)<=122) {
    		
            int x2 = rectangleObjectToBeInserted.x + rectangleObjectToBeInserted.w; //x2 is the x axis value of the bottom right corner right corner by summing the values of x and the rectangle width    
            int y2 = rectangleObjectToBeInserted.y + rectangleObjectToBeInserted.h; //y2 is the y axis value of the bottom right corner right corner by summing the values of y and the rectangle height
		
            //Checking if the rectangle sides within the world box or not
            if (rectangleObjectToBeInserted.x >= 0 && rectangleObjectToBeInserted.y >= 0 && x2 <= MAX_WIDTH && y2 <= MAX_HEIGHT && rectangleObjectToBeInserted.w > 0 && rectangleObjectToBeInserted.h > 0) {
	            list.insert(inputPair);            	
                System.out.println("Rectangle inserted: " + "(" +rectangleNameToBeInserted + ", " + rectangleObjectToBeInserted.x + ", " + rectangleObjectToBeInserted.y + ", " +  rectangleObjectToBeInserted.w + ", " +  rectangleObjectToBeInserted.h + ")" );
                String s = "Rectangle inserted: " + "(" +rectangleNameToBeInserted + ", " + rectangleObjectToBeInserted.x + ", " + rectangleObjectToBeInserted.y + ", " +  rectangleObjectToBeInserted.w + ", " +  rectangleObjectToBeInserted.h + ")" ;
                return s;
            }
            
            else {
            	System.out.println("Rectangle rejected: " + "(" + rectangleNameToBeInserted + ", " + rectangleObjectToBeInserted.x + ", " + rectangleObjectToBeInserted.y + ", " +  rectangleObjectToBeInserted.w +", " +  rectangleObjectToBeInserted.h + ")" );
            	return "Rectangle rejected: " + "(" + rectangleNameToBeInserted + ", " + rectangleObjectToBeInserted.x + ", " + rectangleObjectToBeInserted.y + ", " +  rectangleObjectToBeInserted.w +", " +  rectangleObjectToBeInserted.h + ")" ;
            }
        }
     	return "Rectangle rejected: " + "(" + rectangleNameToBeInserted + ", " + rectangleObjectToBeInserted.x + ", " + rectangleObjectToBeInserted.y + ", " +  rectangleObjectToBeInserted.w +", " +  rectangleObjectToBeInserted.h + ")" ;
        
       }


        
       


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public static void remove(String name) {
        list.remove(name);

  }


  /**
   * Removes a rectangle with the specified coordinates if available. If not
   * an error message is printed to the console.
   * 
   * @param x
   *            x-coordinate of the rectangle to be removed
   * @param y
   *            x-coordinate of the rectangle to be removed
   * @param w
   *            width of the rectangle to be removed
   * @param h
   *            height of the rectangle to be removed
   */
    public static void remove(int x, int y, int w, int h) {
        //Dimensions of rectangle i want to remove
    	Rectangle1 rec = new Rectangle1(x,y,w,h);
    	rec.x = x;
    	rec.y = y;
    	rec.w = w;
    	rec.h = h;


            	list.removeByValue(rec);
            
    	}

    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList Iterator for this 
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
  public static void regionsearch(int x, int y, int w, int h) {
      list.searchByRegion(x, y, w, h);
  }



    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList Iterators for this
     */
    public static void intersections() {
	    
    	Iterator<KVPair<String, Rectangle1>> iter1 = list.iterator(); //creating object iter1 of Iterator class
        System.out.print("Intersections pairs:");

	      //First iteration on the rectangle to be compared to other rectangles
    	  while (iter1.hasNext()) { //iter1.hasNext() checks if the the node next to the current node in the list is not null
    		  
              KVPair<String, Rectangle1> pair1 = iter1.next(); //storing the KVPair<String, Rectangle1> returned from the iter1.next() method and storing it in variable pair1
              Rectangle1 rect1 = pair1.getValue();
              String rect1_Name = pair1.getKey();
              Iterator<KVPair<String, Rectangle1>> iter2 =  list.iterator(); //creating object iter2 of Iterator class
              
              //Second iteration on the rest of the rectangles to get the intersections with the first rectangle
              while (iter2.hasNext()) {
            	  
                  KVPair<String, Rectangle1> pair2 = iter2.next();  //storing the KVPair<String, Rectangle1> returned from the iter2.next() method and storing it in variable pair2
                  Rectangle1 rect2 = pair2.getValue();
                  String rect2_Name = pair2.getKey();
                     
		  /*
		   * Check if the  first rectangle and second rectangle intersect
		   * Check if the  first rectangle and second rectangle edges does not abut one another and do not overlap,
		   * if the first two conditions are true, excute the block 
		   * If it happens that two rectangles intersect, they are printed to standart output file in the shape of pair
		  */
                  if (rect1.x < rect2.x + rect2.w && rect1.x + rect1.w > rect2.x && rect1.y < rect2.y + rect2.h &&rect1.y + rect1.h > rect2.y &&
		      !(rect1.x == rect2.x && rect1.y == rect2.y && rect1.w == rect2.w && rect1.h == rect2.h)) {
              	    System.out.printf( 
              	    		"\n(%s, %d, %d, %d, %d | %s, %d, %d, %d, %d)",
              	    		rect1_Name, rect1.x, rect1.y, rect1.w, rect1.h,
              	    		rect2_Name, rect2.x, rect2.y, rect2.w, rect2.h);
              	}
                  
              }
          }
    	  System.out.println();
      }

    
    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public static void search(String rectangleName) {
    	list.Search(rectangleName);
       
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */

    public static ArrayList<String> dump() {
    	return list.dump();
        
    }

}
