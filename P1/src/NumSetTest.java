import static org.junit.Assert.*;

import org.junit.Test;


public class NumSetTest {

	@Test
	public void testConstructorAndToString() {
		double[] var = {2.0, 3.0, 4.0, 5.7};
		NumSet test = new NumSet(var);
		assertEquals("Normal array test",
					"2.0 3.0 4.0 5.7",
					test.toString());
		var = null;
		test = new NumSet(var);
		assertEquals("Null array test",
					"",
					test.toString());
	}
	
	@Test
	public void testSize() {
		double[] var = {2.0, 3.0, 4.0, 5.7};
		NumSet test = new NumSet(var);
		assertEquals("Normal array test",
					4,
					test.size());
		var = null;
		test = new NumSet(var);
		assertEquals("Null array test",
					0,
					test.size());
	}
	
	@Test
	public void testContains() {
		double[] var = {2.0, 3.0, 4.0, 5.7};
		NumSet test = new NumSet(var);
		assertEquals("Normal array test - true",
					true,
					test.contains(4.0));
		assertEquals("Normal array test - false",
					false,
					test.contains(4.01));
		test = new NumSet(null);
		assertEquals("Null array test",
					false,
					test.contains(4.0));
	}
	
	@Test
	public void testIntersectAndToString() {
		double[] var1 = {2.0, 3.0, 4.0, 5.7};
		NumSet test1 = new NumSet(var1);
		double[] var2 = {7.0, 2.0, 43.0, 5.7};
		NumSet test2 = new NumSet(var2);
		assertEquals("Normal array test",
					"2.0 5.7",
					NumSet.intersect(test1, test2).toString());
		test2 = new NumSet(null);
		assertEquals("One null array test",
					"",
					NumSet.intersect(test1, test2).toString());
		test1 = test2;
		assertEquals("Both null array test",
					"",
					NumSet.intersect(test1, test2).toString());
		test2 = new NumSet(var2);
		assertEquals("Other one null array test",
					"",
					NumSet.intersect(test1, test2).toString());
	}
	
	@Test
	public void testUnionAndToString() {
		double[] var1 = {2.0, 3.0, 4.0, 5.7};
		NumSet test1 = new NumSet(var1);
		double[] var2 = {7.0, 2.0, 43.0, 5.7};
		NumSet test2 = new NumSet(var2);
		NumSet test3 = new NumSet(null);
		NumSet test4 = new NumSet(null);
		assertEquals("Normal array test",
					"2.0 3.0 4.0 5.7 7.0 43.0",
					NumSet.union(test1, test2).toString());
		assertEquals("One null array test",
					"2.0 3.0 4.0 5.7",
					NumSet.union(test1, test3).toString());
		assertEquals("Both null array test",
					"",
					NumSet.union(test4, test3).toString());
		assertEquals("Other one null array test",
					"7.0 2.0 43.0 5.7",
					NumSet.union(test4, test2).toString());
	}
	
	@Test
	public void testEquivalence() {
		double[] var = {2.0, 3.0, 4.0, 5.7};
		NumSet test1 = new NumSet(var);
		NumSet test2 = new NumSet(var);
		assertEquals("Normal array test.",
					true,
					NumSet.equivalence(test1, test2));
		
		test2 = new NumSet(null);
		assertEquals("One null array test.",
					false,
					NumSet.equivalence(test1, test2));
		
		test1 = test2;
		assertEquals("Both null array test.",
					true,
					NumSet.equivalence(test1, test2));
		
		test2 = new NumSet(var);
		assertEquals("Other one null array test.",
					false,
					NumSet.equivalence(test1, test2));
		test1 = new NumSet(var);
		double[] extraVar = {2.0, 3.0, 4.0, 5.7, 6.3};
		test2 = new NumSet(extraVar);
		assertEquals("Test other sizes.",
					false,
					NumSet.equivalence(test1, test2));
	}
}
