import static org.junit.Assert.*;

import org.junit.Test;


public class HuffmanNodeTest {
	
	@Test
	public void testcompareTo() {
		HuffmanNode hn1 = new HuffmanNode((byte)'a', 5);
		HuffmanNode hn2 = new HuffmanNode((byte)'a', 4);
		assertEquals(1, hn1.compareTo(hn2));
		HuffmanNode hn3 = new HuffmanNode((byte)'b', 5);
		assertEquals(-1, hn1.compareTo(hn3));
	}
	
	@Test
    public void testArrayArgumentConstructorAndToString() {
    	HuffmanNode hn = new HuffmanNode((byte)'a', 5);
    	assertEquals("Your constructors should initialize the byte and count members " + 
            "based on the passed arguments", 
            (byte)'a', hn.b);
    	assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments", 
                5, hn.count);
    	assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments. The left branch should be Null", 
                null, hn.left);
                assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments. The right branch should be Null", 
                null, hn.right);
    }
	
	@Test
	public void testPublicMember() {
		HuffmanNode hn = new HuffmanNode((byte)'a', 5);
		System.out.println(hn.b + hn.count + "" + ((hn.code == null) ? (hn.left==hn.right) : 1));
	}

}