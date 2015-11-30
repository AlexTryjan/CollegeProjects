import org.junit.Test;


public class ByteCounterTest {

	//Last Project I used asserts to test my code. This time I found it easier to simply
	//print out the data and compare it to what I expected to find.
	
	@Test
	public void testConstructorA() {
		System.out.println(); System.out.println("testConstructorA");
		System.out.println("hello");
		byte[] input = {'h','e','l','l','o'};
		ByteCounter test = new ByteCounter(input);
		for(int i = 0; i < test.inputLL.size(); i++) {
			System.out.print(test.inputLL.get(i).b);
			System.out.println("  " + test.inputLL.get(i).count);
		}
	}
	
	public void testCounstructorB() {
		System.out.println(); System.out.println("testConstructorB");
		ByteCounter test = new ByteCounter("test.txt");
		for(int i = 0; i < test.inputLL.size(); i++) {
			System.out.print(test.inputLL.get(i).b);
			System.out.println("  " + test.inputLL.get(i).count);
		}
	}

	@Test
	public void testGetElementsByte() {
		System.out.println(); System.out.println("testGetElementsByte");
		byte[] input = {'z','e','l','l','o'};
		ByteCounter test = new ByteCounter(input);
		byte[] output = test.getElements();
		for(int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
	}
	
	@Test
	public void testGetElementsCount() {
		System.out.println("");
		byte[] in = {'a','a','a','b','c','c'};
		ByteCounter check = new ByteCounter(in);
		check.setOrder("countInc");
		System.out.println(check.toString("char"));
		
		System.out.println(); System.out.println("testGetElementsCount");
		byte[] input = {'z','e','l','l','o'};
		ByteCounter test = new ByteCounter(input);
		test.setOrder("countInc");
		System.out.println("countInc");
		byte[] output = test.getElements();
		for(int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
		test.setOrder("countDec");
		System.out.println("countDec");
		output = test.getElements();
		for(int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
	}
	
	@Test
	public void testToStrings() {
		System.out.println(); System.out.println("testToStrings");
		byte[] input = {'z','e','l','l','o'};
		ByteCounter test = new ByteCounter(input);
		System.out.println(test.toString());
		//Expecting 122:1 101:1 108:2 111:1
		System.out.println(test.toString("char"));
		//Expecting z:1 e:1 l:2 o:1
	}
}
