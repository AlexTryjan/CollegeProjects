import static org.junit.Assert.*;
import org.junit.Test;
public class NumLinkedListTest {

    @Test
    public void testDefaultConstructorAndToString() {
        NumLinkedList list = new NumLinkedList();       
        assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
            "It also can be the problem in method TOSTRING.", 
            "", list.toString());
    }

    @Test
    public void testAddAndToString() {
        NumLinkedList list = new NumLinkedList();

        list.add(1.0);
        
        assertEquals("Add method should add element to the end of list each time. " +
                "It's also can be the problem in method TOSTRING.",
                "1.0", list.toString());
        
        list.add(3.0);
        list.add(2.0);

        assertEquals("Add method should add element to the end of list each time. " +
            "It's also can be the problem in method TOSTRING.",
            "1.0 3.0 2.0", list.toString());
    }

    @Test
    public void testSize() {
        NumLinkedList list = new NumLinkedList();

        assertEquals("Method SIZE should return 0, when list is constructed by default constructor.",
            0, list.size());

        list.add(1.0);
        list.add(2.0);
        list.add(3.0);

        assertEquals("Method SIZE should return the size of the list, " + 
            "i.e. the number of elements, in the sequence.",
            3, list.size());
    }
    
    @Test
    public void testInsertAndToString() {
    	NumLinkedList list = new NumLinkedList();
    	list.insert(2, 3.0);
    	
    	assertEquals("Adds elements to end if index is not available",
                "3.0", 
               	list.toString());
    	
    	list.insert(0, 2.5);
    	list.insert(1, 1.0);
    	list.insert(1, 4.0);
    	list.insert(20, 7.0);
    	list.insert(1, 6.0);
    	
    	assertEquals("Adds elements before desired index.",
                "2.5 6.0 4.0 1.0 3.0 7.0", 
               	list.toString());
    }
    
    @Test
    public void testRemoveAndToString() {
    	NumLinkedList list = new NumLinkedList();
    	list.add(2.0);
    	list.add(3.0);
    	list.add(4.0);
    	
    	list.remove(2);
    	assertEquals("Removes element at desired index.",
                "2.0 3.0", 
               	list.toString());
    	
    	list.remove(17);
    	assertEquals("Removing index that is not present results in no change",
                "2.0 3.0", 
               	list.toString());
    }
    
    @Test
    public void testContains() {
    	NumLinkedList list = new NumLinkedList();
    	assertEquals("Returns false if list is empty.",
                false, 
               	list.contains(5.9));
    	
    	list.add(2.0);
    	list.add(3.0);
    	list.add(4.0);
    	
    	assertEquals("Returns true if value is in list.",
                true, 
               	list.contains(4.0));
    	assertEquals("Returns false if value is NOT in list.",
                false, 
               	list.contains(7.2));
    }
    
	@Test
    public void testLookup() {
		NumLinkedList list = new NumLinkedList();
		list.add(2.0);
    	list.add(3.0);
    	list.add(4.0);
    	
    	assertEquals("Returns value at desired index",
    			4.0, 
               	list.lookup(2), 0.0);
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void testLookupException() {
    	NumLinkedList list = new NumLinkedList();
        //Throws exception if index is not present
    	list.lookup(5);
	}
	
	@Test
    public void testEquals() {
    	NumLinkedList list = new NumLinkedList();
    	list.add(2.0);
    	list.add(3.0);
    	list.add(4.0);
    	
    	NumLinkedList list2 = new NumLinkedList();
    	list2.add(2.0);
    	list2.add(3.0);
    	list2.add(4.0);
    	
    	assertEquals("Checks if equal lists (true if equal).",
                true, 
               	list.equals(list2));
    	
    	NumLinkedList list3 = new NumLinkedList();
    	list3.add(2.0);
    	list3.add(3.0);
    	list3.add(4.0);
    	list3.add(5.0);
    	
    	assertEquals("Checks if equal lists (false if NOT equal).",
                false, 
               	list.equals(list3));
    }
	
	@Test
    public void testRemoveDuplicatesAndToString() {
    	NumLinkedList list = new NumLinkedList();
    	
    	list.removeDuplicates();
    	assertEquals("Empty List is unchanged.",
                "", 
               	list.toString());
    	
    	list.add(2.0);
    	list.add(3.0);
    	list.add(2.0);
    	list.add(6.0);
    	list.add(2.0);
    	list.removeDuplicates();
    	assertEquals("Removes Duplicates in list",
                "2.0 3.0 6.0", 
               	list.toString());
	}
	
	
	
}