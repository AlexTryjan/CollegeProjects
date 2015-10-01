import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class TokenizerTest {

	@Test
	public void FileArgConstructor() throws IOException{
		Tokenizer tz = new Tokenizer ("/Users/aetryjan/Tests/single_word.txt");
		assertEquals("The constructor should read the words from a specified file to tokenize it",
				"hello",tz.wordList().get(0));		
	}
	@Test
	public void ArrayArgConstructor(){
		String test [] = {"hello"};
		Tokenizer tz = new Tokenizer(test);
		assertEquals("The constructor should read the words from an array to tokenize it",
				"hello",tz.wordList().get(0));
	}
	@Test
	public void testFileArgConstructorAndPunctutationAndLowerCase() throws IOException{
		Tokenizer tz = new Tokenizer ("/Users/aetryjan/Tests/test.txt");
		ArrayList<String> output = new ArrayList<String>();
		output.add("hello");
		output.add("world");
		output.add("with");
		output.add("punctutation");
		output.add("only");
		assertArrayEquals("The word list function should return the words from the file after" + 
	            " convert words to lower cases and remove punctutation", 
	            output.toArray(),tz.wordList().toArray());
	}
	@Test
	public void testFileArgConstructorAndSpaces() throws IOException{
		Tokenizer tz = new Tokenizer ("/Users/aetryjan/Tests/test2.txt");
		ArrayList<String> output = new ArrayList<String>();
		output.add("hello");
		output.add("world");
		output.add("with");
		output.add("space");
		output.add("only");
		assertArrayEquals("The word list function should return the words from the file after" + 
	            " removing spaces", 
	            output.toArray(),tz.wordList().toArray());
	}
	@Test
	public void testArrayArgConstructorAndPunctuation(){
		String test [] = {"hello@ world!","with#, punctuation; only&"};
		Tokenizer tz = new Tokenizer (test);
		ArrayList<String> output = new ArrayList<String>();
		output.add("hello");
		output.add("world");
		output.add("with");
		output.add("punctuation");
		output.add("only");
		assertArrayEquals("The word list function should return the words from the file after remove punctuation and", 
	            output.toArray(),tz.wordList().toArray());
	}
	@Test
	public void testArrayArgConstructorAndLowerCase(){
		String test [] = {"HEllO WOrLD","WiTh CApitaL LeTTers"};
		Tokenizer tz = new Tokenizer (test);
		ArrayList<String> output = new ArrayList<String>();
		output.add("hello");
		output.add("world");
		output.add("with");
		output.add("capital");
		output.add("letters");
		assertArrayEquals("The word list function should return the words from the file after remove punctuation and", 
	            output.toArray(),tz.wordList().toArray());
	}
	@Test
	public void testArrayArgConstructorAndSpaces(){
		String test [] = {" hello world   ","   with spaces only "};
		Tokenizer tz = new Tokenizer (test);
		ArrayList<String> output = new ArrayList<String>();
		output.add("hello");
		output.add("world");
		output.add("with");
		output.add("spaces");
		output.add("only");
		assertArrayEquals("The word list function should return the words from the file after remove punctuation and", 
	            output.toArray(),tz.wordList().toArray());
	}
}
	
	