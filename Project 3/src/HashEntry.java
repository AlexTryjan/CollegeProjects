
public class HashEntry implements Comparable<HashEntry>{

	String key;
	String prev = "";
	int value;
	int sortHelper = 0;
	
	public HashEntry(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public HashEntry(String key, String prev, int value) {
		this.key = key;
		this.value = value;
		this.prev = prev;
	}
	
	String getPrev() {
		return prev;
	}
	
	String getKey() {
		return key;
	}
	
	int getValue() {
		return value;
	}
	
	void setValue(int value) {
		this.value = value;
	}
	
	void setSH() {//helps to distinuguish types of sorting
		sortHelper = 1;
	}

	public int compareTo(HashEntry o) {//SOrts from greatest to Least
		if(sortHelper == 1) return compareToReverse(o);
		if(prev != "") return compareToPair(o);//Check for pairs
		if(value < o.getValue()) return 1;
		else if(value > o.getValue()) return -1;
		else return key.compareTo(o.getKey());
	}
	
	public int compareToPair(HashEntry o) { //compare Pairs using prev (i.e. first word)
		if(value < o.getValue()) return 1;
		else if(value > o.getValue()) return -1;
		else return prev.compareTo(o.getPrev());
	}
	
	public int compareToReverse(HashEntry o) { //compare Pairs using prev (i.e. first word)
		if(value < o.getValue()) return -1;
		else if(value > o.getValue()) return 1;
		else return key.compareTo(o.getKey());
	}

}
