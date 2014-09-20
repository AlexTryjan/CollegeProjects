
public class Annotation {

	int n;
	
	//default constructor
	public Annotation() {}
	
	//user-initialized constructor
	public Annotation(int n) {this.n = n;}
	
	//accessor for n
	public int getn() {return n;}
	
	//converts n to String type based on factors 
	//(specifically 3 & 5)
	public String toString() {	
		if(n % 3 == 0) {
			if(n % 5 == 0) {
				return "FizzBuzz";
			}
			else {
				return "Fizz";
			}
		}
		else if(n % 5 == 0) {
			return "Buzz";
		}
		else {
			return String.valueOf(n);
		}
	}
	//same as toString, but converts list of numbers
	static String annotateList(int start, int end) {
		String result = "";
		while (start <= end) {
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
			start++;
		}
		result = result.substring(0,result.length()-1);
		return result; //returns converted array as a String
	}
}