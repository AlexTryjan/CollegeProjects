
public class NumSet {
	
	//NOTE: NumSet uses the MunArrayList class to implement many of its methods
	private NumArrayList set;
	public NumSet(double[] array) {
		set = new NumArrayList();
		if(array == null) return;
		for(double i : array) {
			set.add(i);
		}
	}
	public double get(int i) {
		return set.lookup(i);
	}
	public int size() {
		return set.size();
	}
	
	//returns true if set contains the value, false otherwise
	public boolean contains(double value) {
		for(int i = 0; i < size(); i++) {
			if(get(i) == value) return true;
		}
		return false;
	}
	
	//Returns the intersection of two sets (i.e. what values they share)
	static NumSet intersect(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return new NumSet(null);
		if(S1.set == null && S2.set != null) return new NumSet(null);
		if(S1.set != null && S2.set == null) return new NumSet(null);
		NumArrayList temp = new NumArrayList(); 	
		double valueA;							
		for(int i = 0; i < S1.size(); i++) {
			for(int j = 0; j < S2.size(); j++) {
				valueA = S1.get(i);
				if(valueA == S2.get(j)) {
					temp.add(valueA);
					break;
				}
			}
		}
		return new NumSet(temp.get());
	}
	
	//returns combination of two sets. (elements are still unique after this union)
	static NumSet union(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return new NumSet(null);
		if(S1.set == null && S2.set != null) return S2;
		if(S1.set != null && S2.set == null) return S1;
		double[] d = new double[0];
		NumSet temp = new NumSet(d);
		for(int i = 0; i < S1.size(); i++) {
			temp.set.add(S1.get(i));
		}								
		for(int i = 0; i < S2.size(); i++) {
			if(!S1.contains(S2.get(i)))
			temp.set.add(S2.get(i));
		}
		return temp;
	}
	
	public String toString() {
		return set.toString(); //uses NumArrayList toString method
	}
	
	//returns true if the two sets contain the same values NOTE: order does not matter for sets
	static boolean equivalence(NumSet S1, NumSet S2) {
		if(S1.set == null && S2.set == null) return true;
		if(S1.set == null && S2.set != null) return false;
		if(S1.set != null && S2.set == null) return false;
		if(S1.size() != S2.size()) return false;
		int check;
		for(int i = 0; i < S1.size(); i++) {
			check = 0;
			for(int j = 0; j < S2.size(); j++) {
				if(S1.get(i) == S2.get(j)) {
					check = 1;
					break;
				}
			}
			if(check != 1) return false;
		}
		return true;
	}
	
}