import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class  ByteCounterTest {
	
    @Test
    public void testArrayArgumentConstructorAndToString() {
    	byte test [] = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c', (byte)' ', (byte)'?'};
    	ByteCounter byteCount = new ByteCounter(test);     
        assertEquals("Your constructors should store these bytes with their occurrences values " + 
            "It also can be the problem in method TOSTRING.", 
            (byte)' '+":1 "+ (byte)'?'+":1 "+ (byte)'a'+":3 "+ (byte)'b'+":2 "+(byte)'c'+":1", byteCount.toString());
    }
    
    @Test
    public void testStringArgumentConstructorAndGetCount()throws IOException {
		ByteCounter byteCount = new ByteCounter("/Users/aetryjan/file.txt"); 
    	byte b = (byte)'l';
        assertEquals(3, byteCount.getCount(b));
    }
    
    @Test
    public void testByteArgumentGetCount() {
    	byte test [] = {(byte)'e', (byte)'e', (byte)'c', (byte)'s'};
    	byte b = (byte)'e';
    	ByteCounter byteCount = new ByteCounter(test);     
        assertEquals("getCount method should take a byte value and return the " + 
            " number of occurrences.", 
            2, byteCount.getCount(b));
    }
    
    @Test
    public void testArrayArgumentGetCount() {
    	byte test [] = {(byte)'h',(byte)'e', (byte)'l', (byte)'l',(byte)'o'};
    	byte b [] = {(byte)'h',(byte)'e',(byte)'l',(byte)'o'};
    	ByteCounter byteCount = new ByteCounter(test); 
    	int count [] = byteCount.getCount(b);
        assertArrayEquals("getCount method should take a byte array and return an " + 
            " array of the number of occurrences of each byte.", 
            new int[]{1,1,2,1},count);
    }
    
    @Test
    public void testGetElements() {
    	byte test [] = {(byte)'h',(byte)'e', (byte)'l', (byte)'l',(byte)'o'};
       	ByteCounter byteCount = new ByteCounter(test); 
    	byte elements [] = byteCount.getElements();
        assertArrayEquals("get Elements method should return an array of byte " + 
            " that has a non-zero count", new byte[]{(byte)'e', (byte)'h', (byte)'l',(byte)'o'},elements);
        assertEquals("get Elements method should return an array of byte " + 
            " that has a non-zero count", 4,elements.length);
    }
    
    @Test
	public void testSetOrder() {
    	byte test [] = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c', (byte)' ', (byte)'?'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.setOrder("countDec");
    	assertEquals("SetOrder to countDec", 
                "countDec", byteCount.order);
    	assertTrue("Method setOrder define the order of the current object",
			true);
	}
    
    @Test
	public void testFormatToString() {
    	byte test [] = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c', (byte)' ', (byte)'?'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byteCount.toString("byte");
    	assertEquals("Your constructors should store these bytes with their occurrences values " + 
                "It also can be the problem in method TOSTRING.", 
                " :1 ?:1 a:3 b:2 c:1", byteCount.toString("char"));
    	assertTrue("Method toString returns the bytes and their counts based on the format provided",
			true);
	}
    
    @Test
    public void testgetElements() {
    	byte test [] = {(byte)'h',(byte)'e', (byte)'l', (byte)'l',(byte)'o',(byte)' ',(byte)'?'};
    	byte b [] = {(byte)' ',(byte)'?',(byte)'e',(byte)'h',(byte)'l',(byte)'o'};
    	ByteCounter byteCount = new ByteCounter(test);
    	byte values [] = byteCount.getElements();
        assertArrayEquals("getElements with byte", 
            b,values);
    	b = new byte[] {(byte)' ',(byte)'?',(byte)'e',(byte)'h',(byte)'o',(byte)'l'};
        byteCount.setOrder("countInc");
    	values = byteCount.getElements();
        assertArrayEquals("getElements with countDec", 
            b,values);
        b = new byte[] {(byte)'l',(byte)' ',(byte)'?',(byte)'e',(byte)'h',(byte)'o'};
        byteCount.setOrder("countDec");
    	values = byteCount.getElements();
        assertArrayEquals("getElements with countInc", 
            b,values);
    }
}
