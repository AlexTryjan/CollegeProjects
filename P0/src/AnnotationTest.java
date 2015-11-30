import static org.junit.Assert.*;

import org.junit.Test;


public class AnnotationTest {

	
	@Test
	public void testAnnotation() {
		Annotation n = new Annotation();
        assertEquals("A constructor with no parameters should assume n is 0.",
        0, n.getn());
	}

	@Test
	public void testAnnotationInt() {
		Annotation n = new Annotation(4);
        assertEquals("A constructor with one integer parameter should store the value of that number.",
        4, n.getn());
	}

	@Test
	public void testToString() {
		Annotation a = new Annotation(99);
		assertEquals("Method should convert int to 'Fizz', 'Buzz', or original number based on properties", 
				"Fizz" , a.toString());
		
		Annotation b = new Annotation(25);
		assertEquals("Buzz" , b.toString());
		
		Annotation c = new Annotation(150);
		assertEquals("FizzBuzz" , c.toString());
		
		Annotation d = new Annotation(73);
		assertEquals("73" , d.toString());
	}

	@Test
	public void testAnnotateList() {
		assertEquals("Method should return String with 'Buzz' if # div. by 5 and 'Fizz' if # div. by 3. 'FizzBuzz' if both.",
		        "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16", Annotation.annotateList(1,16));
	}

}
