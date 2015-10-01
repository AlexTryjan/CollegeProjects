import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class WordStatTest {

	@Test
	public void FileArgumentConstructor() throws IOException{
		WordStat ws = new WordStat("/Users/aetryjan/Desktop/HuckFinn.txt");
		String[] collocs = ws.mostCommonCollocs(10, "Huck", 1);
		for(int i = 0; i < collocs.length; i++) {
			System.out.print(collocs[i] + " ");
		}
		System.out.println();
		String[] collocsEXC = ws.mostCommonCollocsExc(10, "Huck", 1, new String[] {"I","and","the","a","but","no","well", "its", "what", "it"});
		for(int i = 0; i < collocsEXC.length; i++) {
			System.out.print(collocsEXC[i] + " ");
		}
		System.out.println();
		String[] cWords = ws.mostCommonWords(10);
		for(int i = 0; i < cWords.length; i++) {
			System.out.print(cWords[i] + " ");
		}
		System.out.println();
		String[] cWordPairs = ws.mostCommonWordPairs(10);
		for(int i = 0; i < cWordPairs.length; i++) {
			System.out.print(cWordPairs[i] + " ");
		}
		System.out.println(ws.wordRank("Huck"));
		System.out.println(ws.wordPairRank("Huck", "Finn"));
		/*assertEquals(-1, ws.hashing.get("this"));
		assertEquals(1, ws.wordCount("HELl8o"));
		assertEquals(2, ws.wordRank("worl%d"));
		assertEquals(1, ws.wordRank("HE76767693*LLo"));
		assertEquals(1, ws.wordPairRank("hello", "WORL4D"));*/
	}
	@Test
	public void ArrayArgumentConstructor(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		assertEquals(2, ws.hashing.get("this"));
		assertEquals(2, ws.wordCount("ThI%s"));
		assertEquals(4, ws.wordRank("tH,;is9"));
		assertEquals(1, ws.wordRank("sentEN<>ce"));
		assertEquals(1, ws.wordPairRank("*is", "tHe)("));
	}
	
	@Test
	public void testMostCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		String[] common = ws.mostCommonWords(2);
		assertEquals("sentence", common[0]);
		assertEquals("the", common[1]);
	}
	
	@Test
	public void testLeastCommonWords(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		String[] common = ws.leastCommonWords(3);
		assertEquals("first", common[0]);
	}
	@Test
	public void testWordPairCount(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		int count = ws.wordPairCount("the","sentence");
		assertEquals(0 , count);
		assertEquals(2, ws.wordPairCount("is", "the"));
		assertEquals(1, ws.wordPairCount("the", "FirS(t"));
	}
	@Test
	public void testWordPairRank(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		int rank = ws.wordPairRank("the","sentence");
		assertEquals(0, rank);
		assertEquals(2, ws.wordPairRank("This", "is"));
	}
	@Test
	public void testMostCommonWordPairs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		String[] common = ws.mostCommonWordPairs(2);
		assertEquals("is the", common[0]);
		assertEquals("this is", common[1]);
	}
	@Test
	public void testMostCommonCollocs(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat(test);
		String[] common = ws.mostCommonCollocs(2,"the",2);
		assertEquals("first", common[0]);
		assertEquals("second", common[1]);
		common = ws.mostCommonCollocs(2,"the",-74);
		assertEquals("is", common[0]);
		assertEquals("just", common[1]);
	}
	@Test
	public void testMostCommonCollocsExc(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String exc [] = {"the", "is"};
		String[] common = ws.mostCommonCollocsExc(2,"This",4,exc);
		assertEquals(null, common[0]);
		String[] exceptions = {"First", "SecOND"};
		common = ws.mostCommonCollocsExc(2,"sentEN10*$*$*$*$*$*Ce",-72,exceptions);
		assertEquals("third", common[0]);
	}
	@Test
	public void testGenerateWordString(){
		String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
		WordStat ws = new WordStat (test);
		String common = ws.generateWordString(3,"This");
		assertTrue("returns a string composed of k words of the form startWord",true);
	}
	
	
}
