import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class HuffmanList {

	private LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
	public byte[] orderInc;
	public byte[] orderDec;
	public byte[] order;
	
	public HuffmanList(byte[] input) {
		ByteCounter counter = new ByteCounter(input);
		order = counter.read;
		counter.setOrder("countDec");
		orderDec = counter.getElements();
		counter.setOrder("countInc");
		orderInc = counter.getElements();
		for(byte b: orderInc)
			list.add(new HuffmanNode(counter.getCount(b),b));
	}
	
	public HuffmanList(String input) {
		ByteCounter counter = new ByteCounter(input);
		order = counter.read;
		counter.setOrder("countDec");
		orderDec = counter.getElements();
		counter.setOrder("countInc");
		orderInc = counter.getElements();
		for(byte b: orderInc)
			list.add(new HuffmanNode(counter.getCount(b),b));
	}
	
	public HuffmanList(byte[] input, int[] counts) {
		for(int i = 0; i < counts.length; i++) {
			if(counts[i] < 0) throw new IllegalArgumentException("NEGATIVE COUNTS");
		}
		ByteCounter counter = new ByteCounter(input);
		order = counter.read;
		if(input.length != counts.length) throw new IllegalArgumentException("SIZE MISMATCH");
		orderInc = counter.getElements();
		orderDec = counter.getElements();
		if(orderInc.length != input.length) throw new IllegalArgumentException("DUPLICATES DETECTED");
		for(int i = 0; i < input.length; i++)
			list.add(new HuffmanNode(counts[i],input[i]));
		
		Collections.sort(list, new Comparator<HuffmanNode>() {
				//SORTS FROM BIGGEST TO SMALLEST COUNTS
			@Override
			public int compare(HuffmanNode o1, HuffmanNode o2) {
				if(o2.count < o1.count) return -1;
				else if(o2.count == o1.count)
					if(o2.b > o1.b) return -1;
					else return 1;
				else return 1;
			}}
		);
		for(int i = 0; i < list.size(); i++)//CHANGES BIG TO SMALL COUNTS TO ARRAY
			orderDec[i] = list.get(i).b;
		Collections.sort(list);//SORTS FROM SMALLEST TO BIGGEST COUNTS
		for(int i = 0; i < list.size(); i++)//CHANGES BIG TO SMALL COUNTS TO ARRAY
			orderInc[i] = list.get(i).b;
		Collections.sort(list, new Comparator<HuffmanNode>() {
			//SORTS FROM BIGGEST TO SMALLEST COUNTS
		@Override
		public int compare(HuffmanNode o1, HuffmanNode o2) {
			if(o2.count < o1.count) return 1;
			else if(o2.count == o1.count)
				if(o2.b > o1.b) return -1;
				else return 1;
			else return -1;
		}}
	);
	}
	
	public ListIterator<HuffmanNode> iterator() {
		ListIterator<HuffmanNode> itr = list.listIterator(0);
        return itr;
	}
	
	public LinkedList<HuffmanNode> getList() {return list;}//ACCESSOR FOR list
}