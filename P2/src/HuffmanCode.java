//import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanCode {
	
	private LinkedList<HuffmanNode> tree;
	public HuffmanList listORIGINAL;
	private LinkedList<Boolean> code = new LinkedList<Boolean>();
	private boolean equal;
	
	public HuffmanCode(byte[] input) {
		HuffmanList list = new HuffmanList(input);
		listORIGINAL = list;
		tree = list.getList();
		System.out.println("base");
		
		while(tree.size() > 1) {
			HuffmanNode nodeToAdd = new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.remove(), tree.remove());
			for(int i = 0; i < tree.size() + 1 ; i++) {
				if(i == tree.size()) {tree.add(i,nodeToAdd); break;}
				if(nodeToAdd.count <= tree.get(i).count) {tree.add(i,nodeToAdd); break;}
			}
		}
	}
	
	public HuffmanCode(String input) {
		HuffmanList list = new HuffmanList(input);
		listORIGINAL = list;
		tree = list.getList();
		
		
		
		while(tree.size() > 1) {
			HuffmanNode nodeToAdd = new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.remove(), tree.remove());
			for(int i = 0; i < tree.size() + 1 ; i++) {
				if(i == tree.size()) {tree.add(i,nodeToAdd); break;}
				if(nodeToAdd.count <= tree.get(i).count) {tree.add(i,nodeToAdd); break;}
			}
		}
		

		
		
		
		/*while(tree.size() > 1) {
			if(tree.size() == 2) 
				tree.add(new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.get(0), tree.get(1)));
			else 
				for(int i = 2; i < tree.size() + 1; i++) {
					if(i == tree.size()) {tree.add(new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.get(0), tree.get(1))); break;}
					else if(tree.get(i).count >= tree.get(0).count + tree.get(1).count) {
						tree.add(i-1, new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.get(0), tree.get(1)));
						break;
					}
				}
			tree.remove();
			tree.remove();
		}*/
	}
	
	public HuffmanCode(byte[] input, int[] counts) {
		HuffmanList list = new HuffmanList(input, counts);
		listORIGINAL = list;
		tree = list.getList();
		
		while(tree.size() > 1) {
			HuffmanNode nodeToAdd = new HuffmanNode(tree.get(0).count + tree.get(1).count, tree.remove(), tree.remove());
			for(int i = 0; i < tree.size() + 1 ; i++) {
				if(i == tree.size()) {tree.add(i,nodeToAdd); break;}
				if(nodeToAdd.count <= tree.get(i).count) {tree.add(i,nodeToAdd); break;}
			}
		}
		System.out.println();
	}
	
	public boolean[] code(byte b) {//Gets boolean array for location of byte
		if(tree.get(0).left == null && tree.get(0).right == null) //check weird cases
			if(tree.get(0).b == b) return new boolean[] {};//desired byte is root
			else throw new IllegalArgumentException("BYTE NOT FOUND");//not in tree w/ single leaf=root
		equal = false;
		treeProgress(b, tree.get(0));
		if(code == null) throw new IllegalArgumentException("BYTE NOT FOUND");//not in tree
		boolean[] result = new boolean[code.size()];
		for(int i = 0; i < code.size(); i++) {//convert LL to boolean[]
			result[i] = code.get(i);
		}
		code = new LinkedList<Boolean>();//reset boolean LL
		return result;
	}
	
	private void treeProgress(byte b, HuffmanNode node) { //Uses recursion to find location binary codes
		equal = false;
		if(node.left == null && node.right == null && node.b == b){ equal = true; return; }
		if(node.left != null) {
			code.add(false);
			treeProgress(b, node.left);
			if(equal) return;//if we found value, don't remove last boolean added
			code.removeLast();//removes added boolean b/c leaf was not our value
		}
		if(node.right != null) {
			code.add(true);
			treeProgress(b, node.right);
			if(equal) return;//if we found value, don't remove last boolean added
			code.removeLast();//removes added boolean b/c leaf was not our value
		}
	}
	
	public String codeString(byte b) { //returns 0's and 1's for location of byte b
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
		int check = 1; //Used to limit when \n is used (i.e. not have extra at end)
		for(byte b : listORIGINAL.orderDec) {
			if(check == 0) {result += '\n'; check = 1;}
			result += b + ": " + codeString(b);
			check = 0;
		}
		return result;
	}
}
