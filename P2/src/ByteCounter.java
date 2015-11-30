import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ByteCounter {
	
	byte[] inputList;
	LinkedList<HuffmanNode>  inputLL = new LinkedList<HuffmanNode>();
	String order;
	int size;
	public ByteCounter(byte[] inputList) {
		this.inputList = inputList;
		order = "byte";
		int length = this.inputList.length;
		int check;
		size=1;
		inputLL.add(new HuffmanNode(inputList[0],1));
		for(int i = 1; i < length; i++) {
			check = 0;
			for(int j = 0; j < inputLL.size(); j++) {
				if(check == 0) {
					HuffmanNode temp = inputLL.get(j);
					if(temp.b == inputList[i]) {temp.incCount(); check = 1;}
					if(check == 0 && j == inputLL.size()-1) {inputLL.add(new HuffmanNode(inputList[i],1)); size++; check = 1;}
				}
			}
		}
	}
	public ByteCounter(String inputFileName) {
		try{
			Path filePath = Paths.get(inputFileName);
			inputList = Files.readAllBytes(filePath);
			//Code from first constructor
			order = "byte";
			int length = inputList.length;
			int check;
			size=1;
			inputLL.add(new HuffmanNode(inputList[0],1));
			for(int i = 1; i < length; i++) {
				check = 0;
				for(int j = 0; j < inputLL.size(); j++) {
					if(check == 0) {
						HuffmanNode temp = inputLL.get(j);
						if(temp.b == inputList[i]) {temp.incCount(); check = 1;}
						if(check == 0 && j == inputLL.size()-1) {inputLL.add(new HuffmanNode(inputList[i],1)); size++; check = 1;}
					}
				}
			}
			
		} catch (Exception InvalidPathException) {
			throw new IllegalArgumentException("PATH UNAVAILABLE");
		}
	}
	int getCount(byte b) { 
		//Iterator is more efficient than loop because calling get on linked list has runtime of O(N)
		//Has to step through from head of list.
		ListIterator<HuffmanNode> listIterator = inputLL.listIterator();
        HuffmanNode current;
		while (listIterator.hasNext()) {
            current = listIterator.next();
            if(current.b == b)
            	return current.count;
        }
        return 0;
		
	}
	int[] getCount(byte[] b) {
		if(b == null) return null;
		int[] countList = new int[size];
		for(int i = 0; i < size; i++) {
			countList[i] = getCount(b[i]);
		}
		return countList;
	}
	public void setOrder(String order) {
		if(order.equals("byte") || order.equals("countInc") || order.equals("countDec"))
		this.order = order;
		else throw new IllegalArgumentException("Illegal Order");
	}
	byte[] getElements() {
		inputLL.sort(new Comparator<HuffmanNode>() {
	         public int compare(HuffmanNode o1, HuffmanNode o2) {
	        	 //byte
	             if(order == "byte")
	            	 if(o1.b > o2.b) 
	            		 return 1; 
	            	 else
	            		 return -1;
	             //countDec
	             if(order == "countDec")
	            	 if(o1.count < o2.count) 
	            		 return 1; 
	            	 else if (o1.count == o2.count)
	            		 if(o1.b > o2.b) 
		            		 return 1; 
		            	 else
		            		 return -1;
	            	 else
	            		 return -1;
	             //order must be countInc
	             if(o1.count > o2.count) 
            		 return 1; 
            	 else if (o1.count == o2.count)
            		 if(o1.b > o2.b) 
	            		 return 1; 
	            	 else
	            		 return -1;
            	 else
            		 return -1;
	         }
	     });
		ListIterator<HuffmanNode> listIterator = inputLL.listIterator();
		byte[] ordered = new byte[size];
		for(int i = 0; i < size; i++) {
			ordered[i] = listIterator.next().b;
        }
		return ordered;
	}
	public String toString() {
		getElements();//Needed to add this in case someone called setOrder and then toString b/c setOrder doesn't change list, only order
		ListIterator<HuffmanNode> listIterator = inputLL.listIterator();
		HuffmanNode current = listIterator.next();
		String output = current.b + ":" + current.count;
		while(listIterator.hasNext()) {
			current = listIterator.next();
			output += " " + current.b + ":" + current.count;
        }
		return output;
	}
	public String toString(String format) {
		if(format != "char") return "ILLEGAL INPUT";
		getElements();//See above comment
		ListIterator<HuffmanNode> listIterator = inputLL.listIterator();
		HuffmanNode current = listIterator.next();
		String output = (char) current.b + ":" + current.count;
		while(listIterator.hasNext()) {
			current = listIterator.next();
			output += " " + (char) current.b + ":" + current.count;
        }
		return output;
	}
	
}
