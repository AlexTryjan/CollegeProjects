import static org.junit.Assert.*;

import java.io.IOException;
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
	
	@Test
	public void testStringArgumentConstructor()throws IOException {
		char[] charList = new char[]{' ', 'd', 'e', 'h', 'r', 'w', 'o', 'l'};
		int[] countList = new int[]{1, 1, 1, 1, 1, 1, 2, 3};
		
		HuffmanList list = new HuffmanList("/Users/aetryjan/file.txt");
		
		HuffmanNode hNode;
		Iterator<HuffmanNode> iter = list.iterator();
		for (int i = 0; i < 8; i++) {
			assertTrue("HuffmanList constructed from file 'file.txt' should create a list length 8. " +
					"However, your list only has " + i + " elements", iter.hasNext());
			hNode = iter.next();
			assertEquals("The #" + (i+1) + " element in the HuffmanList, constructed from file 'file.txt', " 
					+ "should be character '" + charList[i] + "'.", (byte)charList[i], hNode.b);
			assertEquals("The count of #" + (i+1) + " element, '" + (byte)charList[i] + "'," 
					+ " in the HuffmanList, which constructed from file 'file.txt', should be " 
					+ countList[i] + ".", countList[i], hNode.count);
		}
		assertFalse("HuffmanList constructed from file 'file.txt' should create a list length 8. " +
				"However, your list has the 9-th element.", iter.hasNext());
	}
	
	@Test
	public void testIterator() throws IOException {
		HuffmanList list = new HuffmanList("/Users/aetryjan/file.txt"); 
		Iterator<HuffmanNode> it = list.iterator();
		assertEquals("this method provides an iterator of the list. "+
				"check that the iterator has values", true,it.hasNext());
		HuffmanNode hn = it.next();
		assertEquals("this method provides an iterator of the list. "+
				"check the byte value of the first node in the list ", (byte)' ',hn.b);
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		hn = it.next();
		assertEquals("this method provides an iterator of the list. "+
				"check the count value of the last node in the list ", 3,hn.count);
		assertEquals("this method provides an iterator of the list. "+
				"check that the hasNext is false as we finished all the elements in iterator ", 
				false,it.hasNext());
	}
	
	@Test
	public void testByteAndIntegerArrayConstructor() {
		HuffmanList list = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'c',(byte)'d',(byte)'e'},
				new int[]{7,4,12,5,207});
		assertEquals("arranges bytes in order of increasing count", 
				(byte)'b',list.getList().get(0).b);
		assertEquals("arranges bytes in order of increasing count", 
				(byte)'d',list.getList().get(1).b);
		assertEquals("arranges bytes in order of increasing count", 
				(byte)'a',list.getList().get(2).b);
		assertEquals("arranges bytes in order of increasing count", 
				(byte)'c',list.getList().get(3).b);
		assertEquals("arranges bytes in order of increasing count", 
				(byte)'e',list.getList().get(4).b);
	}
}