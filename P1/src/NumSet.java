
public class NumSet {
	private NumArrayList set;
	public NumSet(double[] array) {
		set = new NumArrayList(array);
	}
	public double get(int i) {
		return set.lookup(i); //gets value at certain index of the array from NumArrayList
	}
	public int size() {
		return set.size();
	}
	public boolean contains(double value) {
		for(int i = 0; i < size(); i++) {
			if(get(i) == value) return true;
		}
		return false;
	}
	static NumSet intersect(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return new NumSet(null);//test for null cases
		if(S1.set == null && S2.set != null) return new NumSet(null);
		if(S1.set != null && S2.set == null) return new NumSet(null);
		NumArrayList temp = new NumArrayList(); 	
		double value1;							
		for(int i = 0; i < S1.size(); i++) {
			for(int j = 0; j < S2.size(); j++) {
				value1 = S1.get(i);
				if(value1 == S2.get(j)) {
					temp.add(value1);
					break; //Final NumSet will not have duplicates, so we can go to next value
				}
			}
		}
		return new NumSet(temp.get());
	}
	static NumSet union(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return new NumSet(null);//Checking for null cases
		if(S1.set == null && S2.set != null) return S2;
		if(S1.set != null && S2.set == null) return S1;
		NumSet temp = new NumSet(null);
		for(int i = 0; i < S1.size(); i++) {
			temp.set.add(S1.get(i)); //creates new NumSet 'identical' to S1
		}								
		for(int i = 0; i < S2.size(); i++) {
			if(!S1.contains(S2.get(i)))//Makes sure to not create duplicate
			temp.set.add(S2.get(i)); //adds S2 onto the end of S1
		}
		return temp;
	}
	public String toString() {
		return set.toString();
	}
	static boolean equivalence(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return true;//Checking for null cases
		if(S1.set == null && S2.set != null) return false;
		if(S1.set != null && S2.set == null) return false;
		if(S1.size() != S2.size()) return false; //b/c no duplicates, size must be same
		int check = 1; //used to check for similarities
		for(int i = 0; i < S1.size(); i++) {
			if(check != 1) return false;
			check = 0;
			for(int j = 0; j < S2.size(); j++) {
				if(S1.get(i) == S2.get(j)) {
					check = 1;
					break;
				}
			}
		}
		return true;
	}
	
}
