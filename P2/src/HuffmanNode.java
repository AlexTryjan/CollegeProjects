



public class HuffmanNode implements Comparable<HuffmanNode>{

	byte b;
	int count;
	boolean[] code;
	HuffmanNode next;
	HuffmanNode left;
	HuffmanNode right;
	
	public HuffmanNode(int count, byte b) {
		this.count = count;
		this.b = b;
		left = null;
		right = null;
	}
	
	public HuffmanNode(byte b, int count) {
		this.count = count;
		this.b = b;
		left = null;
		right = null;
	}
	
	public HuffmanNode(int count, HuffmanNode left, HuffmanNode right) {
		this.count = count;
		this.left = left;
		this.right = right;
	}
	
	public int compareTo(HuffmanNode node) {
		//if(node.count > count) return -1;
		//else if(node.count < count) return 1;
		//else if(node.b > b) return -1;
		//else return 1;
		if(node.count < count) return -1;
		else if(node.count == count)
			if(node.b > b) return -1;
			else return 1;
		else return 1;
	}
	
}
