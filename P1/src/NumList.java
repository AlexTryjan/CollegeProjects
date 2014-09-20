
public interface NumList {

	int size() ;
	void add(double value) ;
	void insert(int i, double value) ;
	void remove(int i) ;
	boolean contains(double value) ;
	double lookup(int i) ;
	boolean equals(NumList otherList) ;
	void removeDuplicates() ;
	String toString() ;
}
