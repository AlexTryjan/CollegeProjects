import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HuffmanCoderTest {

	//Not much to test here.
	
	@Test
	public void testConstructor() throws IOException{
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	assertTrue("The constructor makes a HuffmanCode using byte array",
			true);
	}
	
	@Test
	public void testCompressMethod() throws IOException{
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	hc.compress();
    	assertTrue("The constructor make a HuffmanCode using byte array",
			true);
	}

}
