
public class Annotation {

	int n;
	
	Annotation() {
		n = 0;
	}
	
	Annotation(int n) {
		this.n = n;
	}
	
	public int getn() {
		return n;
	}
	
	public String toString() {
		if(n%3 == 0) 
			if(n%5 == 0)
				return "FizzBuzz";
			else
				return "Fizz";
		else
			if(n%5 == 0)
				return "Buzz";
			else
				return Integer.toString(n);
	}
	
	static String annotateList(int start, int end) {
		String result = "";
		while (start <= end) { //Iterates loop until all numbers have been checked
				if(start % 3 == 0) { 
					if(start % 5 == 0) {
						result += "FizzBuzz "; //divisible by 3 and 5
					}
					else {
						result += "Fizz "; //divisible by 3 but not 5
					}
				}
				else if(start % 5 == 0) {
					result += "Buzz "; //div. by 5 but not 3
				}
				else {
					result += (start + " "); //div. by neither 5 nor 3
				}
			start++; //Move to next number
		}
		result = result.substring(0,result.length()-1); //Removes extra space
		return result; //returns converted numbers as a String
	}
	
}
