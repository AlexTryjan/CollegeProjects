
public class NumSet {
	private NumArrayList set;
	public NumSet(double[] array) {
		set = new NumArrayList(array);
	}
	public int size() {
		return set.size();
	}
	public boolean contains(double value) {
		return set.contains(value);
	}
	public NumSet intersect(NumSet S1, NumSet S2) {
		return S1;
	}
	public NumSet union(NumSet S1, NumSet S2) {
		return S1;
	}
	public String toString() {
		return "test";
	}
	public boolean equivalence(NumSet S1, NumSet S2) {
		return true;
	}
	
}
