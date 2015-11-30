import java.util.LinkedList;
import java.util.ListIterator;


public class HuffmanList{

	private LinkedList<HuffmanNode> list;
	byte[] source;
	byte[] orderDec;
	public HuffmanList(byte[] input) {
		ByteCounter ordersInput = new ByteCounter(input);
		source = ordersInput.inputList;
		ordersInput.setOrder("countDec");
		orderDec = ordersInput.getElements(); //helps us with HuffmanCoder functions (probably a better way to do so)
		ordersInput.setOrder("countInc");
		ordersInput.getElements();
		list = ordersInput.inputLL;
	}
	public HuffmanList(String fileInput) {
		ByteCounter ordersInput = new ByteCounter(fileInput);
		source = ordersInput.inputList;
		ordersInput.setOrder("countDec");//helps us with HuffmanCoder functions (probably a better way to do so)
		orderDec = ordersInput.getElements();
		ordersInput.setOrder("countInc");
		ordersInput.getElements();
		list = ordersInput.inputLL;
	}
	public HuffmanList(byte[] b, int[] counts) {
		int totalByteCount = 0;
		int currentPos = 0;
		for(int i = 0; i < counts.length; i++)
			totalByteCount += counts[i];
		byte[] allBytes = new byte[totalByteCount];
		for(int i = 0; i < b.length; i++){
			if(counts[i] < 0) throw new IllegalArgumentException("NEGATIVES DETECTED");
			for (int j = 0; j < counts[i]; j++) {
				allBytes[currentPos] = b[i];
				currentPos++;
			}
		}
		ByteCounter ordersInput = new ByteCounter(allBytes);
		ordersInput.setOrder("countDec");
		orderDec = ordersInput.getElements();//Don't need source for this constructor as it doesn't exist
		ordersInput.setOrder("countInc");
		ordersInput.getElements();
		list = ordersInput.inputLL;
		if(list.size() != b.length) throw new IllegalArgumentException("DUPLICATES DETECTED");
	}
	public ListIterator<HuffmanNode> iterator() {
		ListIterator<HuffmanNode> itr = list.listIterator(0);
        return itr; 
	}
	public LinkedList<HuffmanNode> getList() {
		return list; //Helper method to get list for other code
	}
}
