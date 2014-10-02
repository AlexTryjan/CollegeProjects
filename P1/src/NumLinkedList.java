
public class NumLinkedList implements NumList {
	//Node "Class-ception"
	public class Node {
		private Node next;
		private double dbl;
		public Node() {						//Creates node without value
			next = null;					//use: head for List
		}
		public Node(double dbl) {			//Creates Node with value & null pointer
			this.dbl = dbl;					//use: adding
			next = null;
		}
		public Node(double dbl, Node next) {//Creates Node with value and pointer
			this.dbl = dbl;					//use: inserting
			this.next = next;
		}
		public double getDbl() {			//Returns value of Node
			return dbl;
		}
		public void setDbl(double dbl) {	//Sets the value for Node
			this.dbl = dbl;
		}
		public Node getNext() {				//Gets the next item in List
			return next;
		}
		public void setNext(Node next) { 	//Sets 'next'(pointer) for a Node 
			this.next = next;				//use: adding & inserting
		}
	}
	//End of Node Class
	//"Start" of NumLinkedList
	private Node head;
	private int length;
	public NumLinkedList() { 				//Constructor for NumLinkedList
		head = new Node(); 					
		length = 0; 						
	}
	public int size() {						//Returns no. of items in list
		return length;
	}
	public void add(double value) {			//Adds an item (Node) to end of list	
		Node current = head;				
		while(current.getNext() != null) { 	
			current = current.getNext();	//Goes to end of list
		}
		current.setNext(new Node(value));//Sets next for last value
		length++;
	}									
	public void insert(int i, double value){//Inserts Node into List
		if(i >= length) add(value); //If list is too small, add at end	
		else {
			Node current = head;			
			for(int j = 0; j < i; j++) {		//Same as add method, but stops at location, not end
				current = current.getNext();
			}
			Node temp = new Node(value, current.getNext()); //Inserts node with desired value & pointer
			current.setNext(temp);
			length++;
		}
	}
	public void remove(int i) {				//Removes Node from List
		if(i >= length) return;				//Checks if Node to be removed is there
		Node current = head;				
		for(int j = 0; j < i; j++) {		//Moves to one before Node we want to remove
			current = current.getNext();
		}									//Changes pointer of "one before node"
		current.setNext(current.getNext().getNext());//to "one after node"
		length--;							//Changes length of List
	}
	public double lookup(int i) {			//returns value at specific index
		if(i + 1 > length) throw new IllegalArgumentException("Index out of bounds");
		Node current = head;			
		for(int j = 0; j <= i; j++) {
			current = current.getNext();
		}
		return current.getDbl();
	}
	public boolean contains(double value) { //returns true if list contains specific value
		Node current = head;				//false if it does not contain value
		for(int i = 0; i < length; i++) {
			current = current.getNext();
			if(value == current.getDbl()) return true;
		}
		return false;
	}
	public boolean equals(NumList otherList) {//true/false if lists are same/NOTsame respectively
		if(length != otherList.size()) return false; //Different size means NOT the same
		Node current = head;
		for(int i = 0; i < otherList.size(); i++) {
			current = current.getNext();
			System.out.println(current.getDbl() + " vs " + otherList.lookup(i));
			if(otherList.lookup(i) != current.getDbl()) return false;
		}
		return true;	
	}
	public void removeDuplicates() { //Removes duplicate values from list
		if(length == 0) return; //if list is size zero, there is nothing to remove
		Node base = head;
		Node comp = head;
		 Node prevcomp;
		while(base != null) {
			prevcomp = comp;
			comp = base.getNext(); // resets comp and prevcomp every outer loop
			while(comp != null) {
				if(base.getDbl() == comp.getDbl()) {
					prevcomp.setNext(comp.getNext());
					comp = comp.getNext();
					length--;//adjust length of list b/c we removed
				}
				else {
					prevcomp = comp; //moves to next node
					comp = comp.getNext();
				}
			}
			base = base.getNext();
		}
	}
	public String toString() { //returns String version of list
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