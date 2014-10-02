
public class NumArrayList implements NumList {
	private double[] order; //The array we will be referencing throughout methods
	public NumArrayList() {
		order = new double[0];//sets array to size 0
	}
	
	public NumArrayList(int size) {
		order = new double[size];//sets array to desired size
	}
	
	public NumArrayList(double[] array) {
		order = array;
	}
	
	public double[] get() {
		return order;
	}
	
	public int size() {
		if(capacity() == 0) return 0;
		int count = 0;
		for(double index : order) {
			if(index != 0.0)//if index is null, will show as '0.0'... not sure what is wanted
				count++;
		}
		return count;
	}
	
	public int capacity() { //returns the number of elements the array can currently hold
		if(order == null) return 0;
		return order.length;
	}
	
	public void add(double value) { //adds value at the end of the array
		double[] temp = new double[capacity() + 1];
		int count = 0;
		if(order != null)
		for(double index : order) {
			temp[count] = index;
			count++;
		}
		temp[count] = value;
		order = temp;
	}
	
	public void insert(int i, double value) { //adds value before desired index
		if(order == null || i >= order.length) add(value);
		else {
			double[] temp = new double[capacity() + 1];
			for(int j = 0, k = 0; j < order.length + 1; j++, k++) { //copies over entire array minus new spot
				if(j == i) j++;
				temp[j] = order[k];
			}
			temp[i] = value;	// adds new spot into new array
			order = temp;
		}
	}
	
	public void remove(int i) {	//takes out value from desired index without resizing array
		if(i >= order.length) return;
			double[] temp = new double[capacity() - 1];
			int count = -1;
			for(double index : order) {
				count++;
				if(count != i) //Skips value to be removed
				temp[count] = index;
				else count --; //Prevents gap in temp array
			}
			order = temp;
	}
	public boolean contains(double value) {
		for(int i = 0; i < size(); i++) {
			if(order[i] == value) return true;
		}
		return false;
	}
	public double lookup(int i) { //returns value at desired index
		if(i + 1 > size()) throw new IllegalArgumentException("Index out of bounds"); //checks for out of bounds errors
		return order[i];
	}
	
	public boolean equals(NumList otherList) { //checks to see if two lists are equal (returns boolean)
		if(capacity() != otherList.size()) return false;
		for(int i = 0; i < order.length; i++) {
			if(otherList.lookup(i) != order[i]) return false; //if an index is not similar, returns false
		}
		return true;	
	}
	
	public void removeDuplicates() {//removes multiple of same values from array
		for(int i = 0; i < order.length; i++) {
			for(int j = i+1; j < order.length; j++) {
				if(order[i] == order[j]) remove(j);	
			}
		}
	}
	
	public String toString() {	//returns String of arrayList without space at the end
		if(order == null) return "";
		String result = "";
		int i = 0;
		for( ; i < order.length - 1; i++) {	//adds all values except for last
			result += order[i] + " ";
		}
		if(order.length != 0)
		result += order[i];		//adds last value if the list is not empty to avoid awkward last space
		return result;
	}
}
