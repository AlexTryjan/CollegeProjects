import static org.junit.Assert.*;

import org.junit.Test;

public class NumArrayListTest {

    @Test
    public void testDefaultConstructorAndToString() {
        NumArrayList list = new NumArrayList();     
        assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
            "It also can be the problem in method TOSTRING.", 
            "", list.toString());
    }
    
    @Test
    public void testUserInitializedConstructorAndToString() {
        NumArrayList list = new NumArrayList(14);     
        assertEquals("With an 'int' parameters, constructor should initialize a list size 'int'. " + 
        		"It also can be the problem in method TOSTRING.", 
        		"0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0", list.toString());
        NumArrayList empty = new NumArrayList(0);
        assertEquals("With an 'int' parameters, constructor should initialize a list size 'int'. " + 
        		"It also can be the problem in method TOSTRING.", 
        		"", empty.toString());
    }

    @Test
    public void testAddAndToString() {
        NumArrayList list = new NumArrayList();

        list.add(1.0);
        list.add(3.0);
        list.add(2.0);

        assertEquals("Add method should add element to the end of list each time. " +
        		"It's also can be the problem in method TOSTRING.",
            	"1.0 3.0 2.0", list.toString());
    }

    @Test
    public void testSize() {
        NumArrayList list = new NumArrayList();

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
    public void testCapacity() {
    	NumArrayList list = new NumArrayList(5);
    	assertEquals("Method CAPACITY should return the size of the list, " + 
    			"i.e. number of indexes",
                5, 
               	list.capacity());
    	NumArrayList list2 = new NumArrayList();
    	assertEquals("CAPCACITY should return the size of the list (null = 0), " + 
    			"i.e. number of indexes",
                0, 
               	list2.capacity());
    }
    
    @Test
    public void testInsertAndToString() {
    	NumArrayList list = new NumArrayList(0);
    	list.insert(2, 3.0);
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
    	double[] var = {2.0, 3.0, 5.0, 7.9};
    	NumArrayList list = new NumArrayList(var);
    	list.remove(3);
    	assertEquals("Removes element at desired index.",
                "2.0 3.0 5.0", 
               	list.toString());
    	list.remove(17);
    	assertEquals("Removing index that is not present results in no change",
                "2.0 3.0 5.0", 
               	list.toString());
    }
    
    @Test
    public void testContains() {
    	double[] var = {2.0, 3.0, 5.0, 7.9};
    	NumArrayList list = new NumArrayList(var);
    	assertEquals("Returns true if value is in list.",
                true, 
               	list.contains(7.9));
    	assertEquals("Returns false if value is NOT in list.",
                false, 
               	list.contains(4.0));
    	list = new NumArrayList(0);
    	assertEquals("Returns false if list is empty.",
                false, 
               	list.contains(4.0));
    }
    
	@Test
    public void testLookup() {
    	double[] var = {2.0, 3.0, 5.0, 7.9};
    	NumArrayList list = new NumArrayList(var);
    	assertEquals("Returns value at desired index",
                7.9, 
               	list.lookup(3), 0.0);
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void testLookupException() {
    	double[] var = {2.0, 3.0, 5.0, 7.9};
    	NumArrayList list = new NumArrayList(var);
        //Throws exception if index is not present
    	list.lookup(5);
	}
	
	@Test
    public void testEquals() {
    	double[] var = {2.0, 3.0, 5.0, 7.9};
    	NumArrayList list = new NumArrayList(var);
    	NumArrayList list2 = new NumArrayList(var);
    	
    	assertEquals("Checks if equal lists (true if equal).",
                true, 
               	list.equals(list2));
    	
    	double[] var2 = {2.0, 3.0, 5.0, 7.9, 4.4};
    	NumArrayList list3 = new NumArrayList(var2);
    	
    	assertEquals("Checks if equal lists (false if NOT equal).",
                false, 
               	list.equals(list3));
    }
	
	@Test
    public void testRemoveDuplicatesAndToString() {
    	double[] var = {2.0, 3.0, 5.0, 3.0};
    	NumArrayList list = new NumArrayList(var);
    	list.removeDuplicates();
    	assertEquals("Removes Duplicates in list",
                "2.0 3.0 5.0", 
               	list.toString());
	}
}