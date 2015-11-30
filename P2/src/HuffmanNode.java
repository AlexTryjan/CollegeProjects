
public class HuffmanNode {

	public byte b;
	public int count;
	public boolean[] code;
	//public HuffmanNode next;//unnecessary for LL
	public HuffmanNode left;
	public HuffmanNode right;
	public HuffmanNode(byte b, int c) {
		this.b = b;
		count = c;
		left = null;
		right = null;
	}
	public HuffmanNode(int count, HuffmanNode left, HuffmanNode right) { //Allows us to make new nodes for tree construction
		this.count = count;
		this.left = left;
		this.right = right;
	}
	public void incCount() {
		count++;
	}
}
