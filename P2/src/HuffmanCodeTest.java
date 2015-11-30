import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HuffmanCodeTest {

	@Test
	public void testHuffmanConstructor() {
		byte[] input = {'z','e','l','l','o'};
		HuffmanCode test = new HuffmanCode(input);
		boolean[] location = test.code((byte)'e');
		System.out.println(test.toString());
		System.out.println("done");
	}
	
	@Test
	public void testHuffmanStringConstructorAndCodeToString() {
		HuffmanCode test = new HuffmanCode("file.txt");
		System.out.println(test.toString());
		System.out.println("done");
	}
	
	//Used Eclipse's Debug function to step through code at this above part to ensure that the trees
	//are in the right order. The System.out.printlns where locations for breakpoints in my code.
	//Below are the generic tests
	
	@Test
	public void testToStringMethod() {
		System.out.println("E");
		assertTrue((byte)'a' < (byte)'b');
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b', (byte)'c', (byte)' ', (byte)'?'}, new int [] {3, 2, 1, 1, 1});
    	String s = hc.toString();
    	assertEquals("This method returns a string containing the table of the binary encodings of each byte in the Huffman tree",
    			"97: 0\n98: 10\n32: 1110\n63: 1111\n99: 110",s);
	}
	
	@Test
	public void testByteArrayArgumentConstructorAndToString() {
		System.out.println("D");
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'});
    	assertEquals("97: 0"+'\n'+"98: 1", hc.toString());
	}
	
	@Test
	public void testStringArgumentConstructorAndToString() throws IOException {
		System.out.println("B");
    	HuffmanCode hc = new HuffmanCode("file.txt");
    	assertEquals("97: 0\n98: 11\n99: 101\n10: 100",hc.toString());
	}
	
	@Test
	public void testByteAndCountArraysConstructorAndToString() {
		System.out.println("C");
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b', (byte)'c', (byte)' ', (byte)'?'}, new int [] {3, 2, 1, 1, 1});
    	assertEquals("The constructor makes a HuffmanCode using byte and count arrays",
    				"10",hc.codeString((byte)'b'));
    	assertEquals("110",hc.codeString((byte)'c'));
    	assertEquals("1110",hc.codeString((byte)' '));
    	assertEquals("1111",hc.codeString((byte)'?'));
    	assertEquals("0",hc.codeString((byte)'a'));
    	assertEquals("97: 0\n98: 10\n32: 1110\n63: 1111\n99: 110", hc.toString());
	}
	
	@Test
	public void testCodeMethod()throws IOException {
		System.out.println("A");
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b', (byte)'c'}, new int [] {3, 2, 1});
    	boolean[] code = hc.code((byte)'a');
    	boolean[] binary = new boolean[] {true};
    	assertEquals(binary[0],code[0]);
	}
	
}
