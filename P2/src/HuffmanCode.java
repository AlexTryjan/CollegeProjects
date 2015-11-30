import java.util.LinkedList;
import java.util.ListIterator;


public class HuffmanCode {

	LinkedList<HuffmanNode> list;
	public int complete = 0;
	byte[] source;
	byte[] orderDec;
	
	public HuffmanCode(byte[] b) {
		HuffmanList List = new HuffmanList(b);
		this.source = List.source; //These will help us with the Huffman Coder functions
		this.orderDec = List.orderDec; 
		list = List.getList();
		while(list.size() > 1) {//The list.remove() method returns the node removed. Thought that was a neat interaction for below
			HuffmanNode nodeToAdd = new HuffmanNode(list.get(0).count + list.get(1).count, list.remove(), list.remove());
			for(int i = 0; i <= list.size(); i++) {
				if(i == list.size()) {list.add(nodeToAdd); break;}
				if(nodeToAdd.count <= list.get(i).count) {list.add(i,nodeToAdd); break;}
			}
		}
	}
	public HuffmanCode(String inputFile) {
		HuffmanList List = new HuffmanList(inputFile);
		this.source = List.source; //These will help us with HuffmanCoder Functions
		this.orderDec = List.orderDec;
		list = List.getList();
		while(list.size() > 1) {
			HuffmanNode nodeToAdd = new HuffmanNode(list.get(0).count + list.get(1).count, list.remove(), list.remove());
			for(int i = 0; i <= list.size(); i++) {
				if(i == list.size()) {list.add(nodeToAdd); break;}
				if(nodeToAdd.count <= list.get(i).count) {list.add(i,nodeToAdd); break;}
			}
		}
	}
	public HuffmanCode(byte[] b, int[] counts) {
		HuffmanList List = new HuffmanList(b, counts);
		this.orderDec = List.orderDec; //no such thing as source text for this type, so we only need orderDec.
		list = List.getList();
		while(list.size() > 1) {
			HuffmanNode nodeToAdd = new HuffmanNode(list.get(0).count + list.get(1).count, list.remove(), list.remove());
			for(int i = 0; i <= list.size(); i++) {
				if(i == list.size()) {list.add(nodeToAdd); break;}
				if(nodeToAdd.count <= list.get(i).count) {list.add(i,nodeToAdd); break;}
			}
		}
	}
	public boolean[] code(byte b) {
		if(list.get(0).left == null && list.get(0).right == null) //check weird cases
			if(list.get(0).b == b) return new boolean[] {};//desired byte is root
			else throw new IllegalArgumentException("BYTE NOT FOUND");
		else {
			LinkedList<Boolean> code = new LinkedList<Boolean>();
			complete=0;
			traverse(b, list.get(0), code);
			int size = code.size();
			if(size == 0) throw new IllegalArgumentException("BYTE NOT FOUND");
			boolean[] result = new boolean[size];
			ListIterator<Boolean> listIterator = code.listIterator();
			int count = 0;
			while(listIterator.hasNext()) {
				result[count] = listIterator.next();
				count++;
			}
			return result;
		}
	}
	private void traverse(byte b, HuffmanNode loc, LinkedList<Boolean> code) {
		if(loc.left == null && loc.right == null) {
			if(b == loc.b) complete = 1;
			return;
		}
		code.add(false);
		traverse(b,loc.left,code);
		if(complete==1) return; //must remove any true/false that doesn't get us to desired node
		code.removeLast();
		code.add(true);
		traverse(b,loc.right,code);
		if(complete==1) return;
		code.removeLast();
	}
	public String codeString(byte b) {
		boolean[] binary = code(b);
		String result = "";
		for(int i = 0; i < binary.length; i++) {
			if(binary[i] == true) result += "1";
			else result += "0";
		}
		return result;
	}
	public String toString() {
		String result = "";
		int check = 1;
		for(int i = 0; i < orderDec.length; i++) {
			if(check == 0) result += '\n';
			result += orderDec[i] + ": " + codeString(orderDec[i]);
			check = 0;
		}
		return result;
	}
}
