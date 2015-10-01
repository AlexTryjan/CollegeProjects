import static org.junit.Assert.*;
import org.junit.Test;


public class HashEntryTest {

	@Test
	public void testConstructorAndAccessorMethods() {
		HashEntry he = new HashEntry("key", 5);
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,getKey should returns the key value", "key", he.getKey());
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,getValue should returns the value", 5, he.getValue());
	}
	@Test
	public void testSetValue() {
		HashEntry he = new HashEntry("key", 5);
		he.setValue(1);
		assertEquals("The constructor initializes hash entry with a given key and value" + 
	            " ,setValue will update the value", 1, he.getValue());
		
	}

}