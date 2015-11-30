import static org.junit.Assert.*;

import org.junit.Test;


public class HuffmanNodeTest {

	@Test
	public void testConstructorsAndIncCount() {
		HuffmanNode newNode = new HuffmanNode((byte) 'a',2); //Checks if constructor is functioning without error
		assertEquals(newNode.count,2);
		newNode.incCount();
		assertEquals(newNode.count,3);
		assertEquals(newNode.b,(byte) 'a');
		HuffmanNode newerNode = new HuffmanNode(2, newNode, null);
		assertEquals(newerNode.left, newNode);
		assertEquals(newerNode.right,null);
	}

}
