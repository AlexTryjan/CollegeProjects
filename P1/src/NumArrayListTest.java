import static org.junit.Assert.*;

import org.junit.Test;

public class NumArrayListTest {

    /* 
     * JUnit tests of fundamental functionality.
     *   
     * Use these JUnit tests to ensure that your code compiles
     * and correctly implements the fundamental functionality.
     * 
     * Code that does not pass these tests will not be graded.
     * Your draft submission needs to pass these tests in order
     * to receive a non-zero grade on the assignment.
     */

    @Test
    public void testDefaultConstructorAndToString() {
        NumArrayList list = new NumArrayList();     
        assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
            "It also can be the problem in method TOSTRING.", 
            "", list.toString());
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
}