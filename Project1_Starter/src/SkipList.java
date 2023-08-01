import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random 
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     */
    public ArrayList<KVPair<K, V>> Search(K key) {
        ArrayList<KVPair<K, V>> result = new ArrayList<>(); //create an array list to hold the result
        SkipNode x = head; // start with head of skip list.
        
        //looping throw skip list level start with the head
        for (int i = head.level; i >= 0; i--) { 
        	
        	//traverse over skip list until we found the node with the specified key
            while (x.forward[i] != null && x.forward[i].pair.getKey().compareTo(key) <= 0) {
            	
                if (x.forward[i].pair.getKey().compareTo(key) == 0) { //check if we found the node
                    result.add(x.forward[i].pair);
                }
                x = x.forward[i]; //if we didn't fount move to the next nod at this level
            }
        }
        
        if (!result.isEmpty()) { // check if we found rectangle
            System.out.println("Rectangles found: ");
            Iterator<KVPair<K, V>> i = result.iterator(); //create an iterator to loop throw kvpair object
            
          //loop throw kvpair object
            while (i.hasNext()) {
                KVPair<K, V> pair = i.next(); //get the next kvpair object
                Rectangle1 rect = (Rectangle1) pair.getValue(); //get the value of kvpair object
                System.out.println("("+pair.getKey()+", " + rect.x + ", " + rect.y + ", " + rect.w + ", " + rect.h+ ")");                       
            }         
        } 
        
        //if we didn't find any rectangle
        else {
            System.out.println("Rectangle not found: "+key);                
        }        
        return null;   
    }
    
    
    
    public ArrayList<KVPair<K,V>> searchByRegion (int x , int y , int w , int h){
    	ArrayList<KVPair<K,V>> result = new ArrayList<>(); //this array list used to store the key-value pairs that intersect with the search region
    	SkipNode check = head;  // initialize a skip node object to the head of the skip list
    	
    	//to check with positive height and width
    	if(w > 0 && h>0) {
    	
    		//a loop that iterates the skip list until the end (check=null)
    		for(int i=0 ; i<size;i++) {			
    		
    			//to ensure that the node we use to iterate not equal and not consult to null
    			while(check!=null && check.forward[0]!=null) {  
    				
    				/*For each key-value pair, the method retrieves the Rectangle1 object associated with the value of the pair
        			 * then check if the rectangle intersects with the search region by checking if the rectangle's top-left x coordinate is less than the search region's x coordinate plus its width ,
        		     *the bottom-right x coordinate is greater than the search region's x coordinate, the top-left y coordinate is less than the search region's y coordinate plus its height,
        		     *and the bottom-right y coordinate is greater than the search region's y coordinate.
        		     *checks if the rectangle is not equal to the search region to exclude the case where the rectangle is the same as the search region
        		     */
    				Rectangle1 rect =(Rectangle1) check.forward[0].pair.getValue(); 			
    				if(rect.x < x + w &&rect.x + rect.w > x &&rect.y < y + h &&rect.y + rect.h > y && !(rect.x == x && rect.y == y && rect.w == w && rect.h == h)) {
    				
    					result.add(check.forward[0].pair);
    			
    				}    				
    			check=check.forward[0];    		
    			}   	   		
    		} 		
    	}
    	
    	if(result.size() != 0) {
    		System.out.printf("Rectangles intersecting region (%d, %d, %d, %d):", x, y, w, h);
    		Rectangle1 temp = new Rectangle1(0,0,0,0);
    		for(int i =0; i<result.size();i++) {
    			temp = (Rectangle1)result.get(i).getValue();
    			System.out.println();
    			System.out.printf("(%s, %d, %d, %d, %d)",result.get(i).getKey(),temp.x,temp.y,temp.w,temp.h);
    		}
    	}
    	else {
    		System.out.print("Rectangle rejected: " + "(" + x + "," + y + "," + w + "," + h + ")");
    	}
    	System.out.println();
    	return null ;
    
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
   public void insert(KVPair<K, V> it) {
   	//generates a new level for the new node 
   int newlevel = randomLevel();
		  if(newlevel > head.level) {
			  adjustHead(newlevel);
		  }
		  //an update array to keep track of the levels before the new node
		  SkipNode [] update = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,  head.level + 1);
	    	//head node to start iteration from the beginning of the SkipList
		  SkipNode x = head;
	    	//Starts from the highest level to keep track of the nodes before the new inserted one
		   for(int i = head.level; i >= 0; i--) {
	
			   //checks if the key is greater than the new key or not
			   while((x.forward[i] != null) && ( x.forward[i].pair.getKey().compareTo(it.getKey()) < 0)) {
				   //moves forward a step
				   x = x.forward[i];
			   }
			   //when we find where the node should be in this level we add the node before it to the update list
			  update[i] = x;
		   }
		   
		  
		   x = new SkipNode(it, newlevel);
		   //to insert a node we must adjust which node points to which, we do this through the following for loop
		   for(int i = 0; i <= newlevel; i++) {
			   x.forward[i] = update[i].forward[i];
			   update[i].forward[i] = x; 
			  
			   }
		  size++;
		  Rectangle1 tempRect = (Rectangle1) it.getValue();
   	
   }


   /**
    * Increases the number of levels in head so that no element has more
    * indices than the head.
    * 
    * @param newLevel
    *            the number of levels to be added to head
    */
   @SuppressWarnings("unchecked")
   private void adjustHead(int newLevel) {
   	//adjusts the head to the largest skipnode level 
	SkipNode temphead = head;
	    head = new SkipNode(new KVPair<K, V>(null,null),newLevel);
	    for(int i =0;i<=temphead.level;i++) {
	    	head.forward[i] = temphead.forward[i];
	    }
   }



    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
	
 @SuppressWarnings("unchecked")	
 public KVPair<K, V> remove (K key){ 
 	int newlevel = randomLevel();
 	Rectangle1 rectangleObject ;
 			  if(newlevel>head.level) {
 				  adjustHead(newlevel);
 			  }
	              //an update array to keep track of the levels before the new node
 			  SkipNode [] update = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,  head.level + 1);    
	              //head node to start iteration from the beginning of the SkipList
 			  SkipNode x = head;
 			
 			   for(int i = head.level;i>=0;i--) {
				   //checks if the key is greater than the new key or not
 				   while((x.forward[i] != null) && ( x.forward[i].pair.getKey().compareTo(key)) < 0) {
 					
					    //moves forward a step
 					   x = x.forward[i];
 				   }
				     //when we find where the node should be in this level we add the node before it to the update list
 				  update[i] = x;
 			   }
 			   x=x.forward[0];
               //checks if the key is equal to the  key we want to rmove  or not
 			   
 			   if( x!=null && x.pair.getKey().compareTo(key) == 0){
     			   rectangleObject=(Rectangle1) x.pair.getValue();
     			   
				      //loop from the node we standing on to the lenght of the array which contain the lavels 
 				   for(int i =0;i<x.forward.length;i++) {
 					   update[i].forward[i] =x.forward[i]; 
 					   x.forward[i] = null;
 					   }
 					// when we found the rectangle we want to remove 
 				   System.out.printf("Rectangle removed: (%s, %d, %d, %d, %d)\n",key ,rectangleObject.x,rectangleObject.y,rectangleObject.w,rectangleObject.h);
 				   size--;
 			   }
 			   else {
     			 //if the rectangle is not there
     			   System.out.printf("Rectangle not removed:  ("+key+")\n" );
     			   }
 			   return null ;
 	    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
 public KVPair<K, V> removeByValue(V val) {
		
		SkipNode x = head;
		boolean flag = false;
		Rectangle1 v = (Rectangle1) val; 
		
		//LOOP ON NODES LIKE A NORMAL LINKED LIST TO FIND THE NODE I WANNA DELETE
		for(int i =0;i<size;i++) {
			
			if(x.forward[0] != null) {
				Rectangle1 tempRect = (Rectangle1) x.forward[0].pair.getValue(); //dimensions i want to remove
				
				//compare to see if the dimensions given do exist or not		
				if((tempRect.x == v.x && tempRect.y == v.y && tempRect.w == v.w && tempRect.h==v.h) ){					
					K it= x.forward[0].pair.getKey(); //if there is rectangle, get its key and remove it by calling remove by name function
					remove(it) ;		
					flag = true;		
				}		
				x = x.forward[0]; 						
			}		
		}
		
		//if the rectangle is not there
		if(flag == false)			
			System.out.printf("Rectangle rejected: (%d, %d, %d, %d)\n", v.x,v.y,v.w,v.h);
		
		return null;	
 }



    /**
     * Prints out the SkipList in a human readable format to the console.
     */

public ArrayList<String> dump() {
	
		//This is the starting node that moves through the code
    	SkipNode tempNode = head;
    	//The arrayList used in testing, everything printed shall be added to this list
    	ArrayList<String> dumpp = new ArrayList<String>();
    	//This prints the start of the dump
    	System.out.println("SkipList dump:");    
    	dumpp.add("SkipList dump:");
    	System.out.printf("Node has depth %d, Value (null)%n",tempNode.forward.length);
    	dumpp.add(String.format("Node has depth %d, Value (null)",tempNode.forward.length));
    	//The node starts moving to the next node in the list using the level 0
    	tempNode = tempNode.forward[0];
    	
    	//this for loop keeps moving forward till the end of the list to display each node, its depth and its value
    	for(int i = 0; i < size; i++) {
    		// this is the rectangle that shall holds the values of the rectangle coordinates, width and height of the node
    		Rectangle1 tempRect = (Rectangle1) tempNode.pair.getValue();
    		System.out.printf("Node has depth %d, Value (%s, %d, %d, %d, %d)%n",tempNode.forward.length,tempNode.pair.getKey(),
    		tempRect.x, tempRect.y, tempRect.w, tempRect.h);
    		dumpp.add( String.format("Node has depth %d, Value (%s, %d, %d, %d, %d)",tempNode.forward.length,tempNode.pair.getKey(),
    	    		tempRect.x, tempRect.y, tempRect.w, tempRect.h));
    		//moves a step forward
    		tempNode = tempNode.forward[0];
    	}
    	
    	//the size is displayed in the end
    	System.out.printf("SkipList size is: %d%n",size);
    	dumpp.add(String.format("SkipList size is: %d",size));
    	//returns the arrayList for testing purposes
    	return dumpp;
    }




    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode [] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;  
        }


        public boolean hasNext() {
        	    return current.forward[0] != null;   // check if there is node after the current node and return boolean value	
        }

        /* get the next node in the SkipList and returns the KVPair stored in the SkipList  
        */
        @Override
        public KVPair<K, V> next() {
            
        	SkipNode temp = current; //current point to the head node we assign  temp variable points to the curerrnt
            temp = temp.forward[0]; //get the next node 
            current =temp; //update current to point to the next node
            return current.element(); // return KVpair
        	}

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}