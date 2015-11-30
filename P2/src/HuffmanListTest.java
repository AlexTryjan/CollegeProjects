import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class HuffmanListTest {

	@Test
	public void testBothArrayAndByteConstructorExceptions() {
		boolean caughtIt = false;
		try {//NEGATIVE COUNTS
		HuffmanList list = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'c',(byte)'d',(byte)'e'},
				new int[]{7,4,12,5,-1});
		} catch(Exception IllegalArgumentException) {caughtIt = true;}
		assertTrue(caughtIt);
		caughtIt = false;
		try {//DUPLICATES
			HuffmanList list = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'c',(byte)'b',(byte)'e'},
					new int[]{7,4,12,5,1});
			} catch(Exception IllegalArgumentException) {caughtIt = true;}
			assertTrue(caughtIt);
			caughtIt = false;
			try {//VARYING SIZES
				HuffmanList list = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'c',(byte)'d',(byte)'e'},
						new int[]{7,4,12,5,1,9});
				} catch(Exception IllegalArgumentException) {caughtIt = true;}
				assertTrue(caughtIt);
	}
	
	@Test
	public void testGetList() {
		byte[] LIST = new byte[]{(byte)'a',(byte)'b',(byte)'c',(byte)'d'};
		HuffmanList list = new HuffmanList(LIST);
		assertTrue(list.getList().get(3).b == (byte)'d');
	}
	
	@Test
	public void testByteArrayArgumentConstructor() {
		HuffmanList hList = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'a', (byte)'c'});
		Iterator<HuffmanNode> iter = hList.iterator();

		assertTrue("Should not be empty", iter.hasNext());
		HuffmanNode hNode = iter.next();
		assertEquals((byte)'b',hNode.b);
		assertEquals(1,hNode.count); //Check first Node

		assertTrue("2 elements???",iter.hasNext());
		hNode = iter.next();
		assertEquals((byte)'c',hNode.b);
		assertEquals(1,hNode.count);//Check second Node

		assertTrue("3 elements?!?",iter.hasNext());
		hNode = iter.next();
		assertEquals((byte)'a',hNode.b);
		assertEquals(2,hNode.count);//Check third Node
		
		assertFalse("3 elements total?!",iter.hasNext());
	}

}
