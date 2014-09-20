
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
		return order.length;
	}
	
	public void add(double value) { //adds value at the end of the array
		double[] temp = new double[capacity() + 1];
		int count = 0;
		for(double index : order) {
			temp[count] = index;
			count++;
		}
		temp[count] = value;
		order = temp;
	}
	
	public void insert(int i, double value) { //adds value before desired index
		if(i >= order.length) add(value);
		else {
			double[] temp = new double[capacity()];
			for(int j = 0, k = 0; j < order.length; j++, k++) { //copies over entire array minus new spot
				if(j == i) j++;
				temp[j] = order[k];
			}
			temp[i] = value;					// adds new spot into new array
			order = temp;
		}
	}
	
	public void remove(int i) {					//takes out value from desired index without resizing array
		if(i <= order.length) {
			double[] temp = new double[capacity()];
			int count = 0;
			for(double index : order) {
				if(count == i) {count++; continue;} //Skips value to be removed
				temp[count] = index;
				count++;
			}
			order = temp;
		}
	}
	public boolean contains(double value) { //array is said to be already sorted according to directions
		int start = 0, end = 0, mid = 0;
		while(true) {
			mid = (start + end) / 2;
			if(order[mid] == value) return true;
			if(order[mid] > value) end = mid - 1;
			if(order[mid] > value) start = mid - 1;
			if(start > end) return false;
		}
	}
	
	public double lookup(int i) { //returns value at desired index
		if(i + 1 < size()) throw new IllegalArgumentException("Index out of bounds"); //checks for out of bounds errors
		return order[i];
	}
	
	public boolean equals(NumList otherList) { //checks to see if two lists are equal (returns boolean)
		for(int i = 0; i < order.length; ) {
			if(otherList.lookup(i) != order[i]) return false; //if an index is not similar, returns false
		}
		return true;	
	}
	
	public void removeDuplicates() {			//removes multiple of same values from array
		for(int i = 0; i < order.length; i++) {
			for(int j = i+1; j < order.length; j++) {
				if(order[i] == order[j]) remove(j);	
			}
		}
	}
	
	public String toString() {	//returns String of arrayList without space at the end
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
