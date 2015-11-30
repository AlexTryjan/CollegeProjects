
public class NumArrayList implements NumList{
	private double[] list;
	int size;
	int capacity;
	
	public NumArrayList(double[] array) { //EXTRA METHOD TO MAKE TESTING EASIER
		list = new double[0];
		size = 0;
		capacity = 0;
		for(double i : array) {
			add(i);
		}
	}
	
	NumArrayList() {
		list = new double[0];
		size = 0;
		capacity = 0;
	}
	
	NumArrayList(int size) {
		list = new double[size];
		this.size = 0;
		capacity = size;
	}
	
	public double[] get() { //Helper Method for NUMSET
		return list;
	}
	
	public int size() {
		removeDuplicates();
		return size;
	}
	
	int capacity() {
		return capacity;
	}
	
	//puts element at end of array
	public void add(double value) { 
		if(size == capacity) { //increase capacity
			double[] templist;
			templist = new double[size + 1];
			for(int i = 0; i < size; i++) {
				templist[i] = list[i];
			}
			templist[size] = value;
			list = templist;
			capacity++;
		}
		else //size < capacity, so leave capacity same
			list[size] = value;
		size++;
	}
	
	//insert element into array before index 'i'
	public void insert(int i, double value) {
		if(capacity <= i) {add(value); return;}
		int check = 0;
		if(capacity==size) {//Resize array
			capacity++;
			double[] templist = new double[capacity];
			for(int j = 0; j < capacity; j++) {
				if(j == i) {
					templist[j] = value;
					check = 1;
				}
				else if(check == 0)
					templist[j] = list[j];
				else
					templist[j] = list[j-1];
			}
			list = templist;
			size++;
		}
		else {//Leave capacity or array same
			double[] templist = new double[capacity];
			for(int j = 0; j < capacity; j++) {
				if(j == i) {
					templist[j] = value;
					check = 1;
				}
				else if(check == 0)
					templist[j] = list[j];
				else
					templist[j] = list[j-1];
			}
			list = templist;
			size++;
		}
	}
	
	//remove an element before index 'i'
	public void remove(int i) {
		if(capacity < i) return; //Not a valid index, so do nothing
		size--;
		int check = 0;
		double[] templist = new double[capacity];
		for(int j = 0; j < capacity - 1; j++) {
			if(j==(i)) check = 1;
			if(check == 1) {
				templist[j] = list[j+1];
				continue;
			}
			templist[j] = list[j];
		}
		list = templist;
		//NOTE: Capacity remains same
	}
	
	//returns true if array contains element, false otherwise
	public boolean contains(double value) {
		if(capacity == 0) return false;
		for(int i = 0; i < capacity; i++) {
			if(list[i] == value) return true;
		}
		return false;
	}
	
	//returns value of the index requested, throws exception if invalid index
	public double lookup(int i) {
		if(size() < i+1) throw new IllegalArgumentException("Index out of bounds");
		return list[i];
	}
	
	public boolean equals(NumList otherList) {
		if(capacity() != otherList.size()) return false;
		for(int i = 0; i < list.length; i++) {
			if(otherList.lookup(i) != list[i]) return false;
		}
		return true;	
	}
	
	
	//Removes duplicate elements in the array, NOTE: capacity does not change... similar to remove method
	public void removeDuplicates() {
		for(int i = 0; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				if(list[i] == list[j]) {
					remove(j);
					j--;
				}	
			}
		}
	}

	public String toString() {
		if(list == null) return "";
		if(size == 0) return "";
		String listString = "";
		int i = 0;
		for( ; i < size - 1; i++) {
			listString += list[i] + " ";
		}
		if(size != 0)
		listString += list[i];
		return listString;
	}
	
}
