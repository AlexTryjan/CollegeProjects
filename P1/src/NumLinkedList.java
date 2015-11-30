
public class NumLinkedList implements NumList{
	/*START NODE CLASS*/
	public class Node {
		private Node next;
		private double dbl;
		public Node() {
			next = null;
		}
		public Node(double dbl) {
			this.dbl = dbl;
			next = null;
		}
		public Node(double dbl, Node next) {
			this.dbl = dbl;
			this.next = next;
		}
		public double getDbl() {
			return dbl;
		}
		public void setDbl(double dbl) {
			this.dbl = dbl;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) { 
			this.next = next;
		}
	}
	/*END NODE CLASS*/
	private Node head;
	private int length;
	public NumLinkedList() {
		head = new Node(); 					
		length = 0; 						
	}
	public int size() {
		return length;
	}
	
	//puts new node with requested value at end of list
	public void add(double value) {	
		Node current = head;				
		while(current.getNext() != null) { 	
			current = current.getNext();
		}
		current.setNext(new Node(value));
		length++;
	}					
	
	//puts new node with requested value before the 'i'th node in the list
	public void insert(int i, double value){
		if(i >= length) add(value);	
		else {
			Node current = head;			
			for(int j = 0; j < i; j++) {
				current = current.getNext();
			}
			Node temp = new Node(value, current.getNext());
			current.setNext(temp);
			length++;
		}
	}
	
	//removes node at 'i'th position from list
	public void remove(int i) {
		if(i >= length) return;
		Node current = head;				
		for(int j = 0; j < i; j++) {
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		length--;
	}
	
	//Returns true if list contains a node with the requested value, false otherwise
	public boolean contains(double value) {
		Node current = head;
		for(int i = 0; i < length; i++) {
			current = current.getNext();
			if(value == current.getDbl()) return true;
		}
		return false;
	}
	
	//returns value of node at position i in list, throws exception if the position i is non-existant
	public double lookup(int i) {
		if(length < i+1) throw new IllegalArgumentException("Index out of bounds");
		Node current = head;			
		for(int j = 0; j <= i; j++) {
			current = current.getNext();
		}
		return current.getDbl();
	}
	
	public boolean equals(NumList otherList) {
		if(length != otherList.size()) return false;
		Node current = head;
		for(int i = 0; i < otherList.size(); i++) {
			current = current.getNext();
			if(otherList.lookup(i) != current.getDbl()) return false;
		}
		return true;	
	}
	
	//Removes any duplicate values from the list so that all values are unique
	public void removeDuplicates() {
		if(length == 0) return;
		Node base = head;
		Node comp = head;
		 Node prevcomp;
		while(base != null) {
			prevcomp = comp;
			comp = base.getNext();
			while(comp != null) {
				if(base.getDbl() == comp.getDbl()) {
					prevcomp.setNext(comp.getNext());
					comp = comp.getNext();
					length--;
				}
				else {
					prevcomp = comp;
					comp = comp.getNext();
				}
			}
			base = base.getNext();
		}
	}
	
	public String toString() {
		String result = "";
		Node current = head;
		if(current.getNext() != null) {
			while(current.getNext().getNext() != null) {
				current = current.getNext();
				result += current.getDbl() + " ";
			}
			result+= current.getNext().getDbl();
		}
		return result;
	}
}
