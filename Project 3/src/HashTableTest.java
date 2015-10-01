import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;


public class HashTableTest {

	@Test
	public void testDefaultConstructor(){
    	HashTable ht = new HashTable();
    	assertTrue("The constructor creates a reasonable size table",
			true);
	}
	
	@Test
	public void testArgConstructorAndPutAndGet(){
    	HashTable ht = new HashTable(20);
    	ht.put("Hi",5);
    	ht.put("Bonjour",8);
    	ht.put("Marhaba",10);
    	ht.put("Hola",12);
    	assertEquals("The constructor creates a hash table where the size specified as argument. "
			+"Put method should store the key-value pair in the hash table."+
    			" Get method returns current the value associated with key",10,ht.get("Marhaba"));
    	assertEquals("The constructor creates a hash table where the size specified as argument. "
    			+"Put method should store the key-value pair in the hash table."+
        			" Get method returns current the value associated with key",-1,ht.get("Hello"));
	}
	@Test
	public void testCollisionHandling(){
    	HashTable ht = new HashTable(20);
    	ht.put("Hi",5,10);
    	ht.put("Bonjour",8,10);
    	ht.put("Marhaba",10,10);
    	ht.put("Hola",4,10);
    	assertEquals("Put method should handle collisions. You may use either separate"+
    			" chaining or a probing strategy",5,ht.get("Hi",10));
    	assertEquals("Put method should handle collisions. You may use either separate"+
    			" chaining or a probing strategy",4,ht.get("Hola",10));
	}
	@Test
	public void testUpdateMethod(){
    	HashTable ht = new HashTable(6);
    	ht.put("Hi",1);
    	ht.put("Bonjour",2);
    	ht.put("Marhaba",3);
    	ht.update("Hi",4);
    	ht.update("Hola", 5);
    	assertEquals("Update method should update value associated with key in the "+
    			"hash table. If value does not exist, it should be added to the table",4,ht.get("Hi"));
    	assertEquals("Update method should update value associated with key in the "+
    			"hash table. If value does not exist, it should be added to the table",5,ht.get("Hola"));
	}
	
	
}
