//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ByteCounter {
	
	public class Tracker implements Comparable<Tracker> {
		int count;
		byte value;
		public Tracker(int count, byte value) {
			this.count = count;
			this.value = value;
		}
		public void set(Tracker t) {
			count = t.count;
			value = t.value;
		}
		public int compareTo(Tracker t) {
			if(count > t.count) return 1;
			else if(count == t.count)
				if(value > t.value) return 1;
				else return -1;
			else return -1; //when "(count < t.count) is true"
			
			
		}
	}

	byte[] read;
	String order = "byte";
	boolean format = false;
	Tracker marker;
	
	public ByteCounter(byte[] read) {
		this.read = read;
		
	}
	
	public ByteCounter(String read) {
		try{
			Path path = Paths.get(read);
			this.read = Files.readAllBytes(path);
		} catch (Exception InvalidPathException) {
			throw new IllegalArgumentException("PATH UNAVAILABLE");
		}
	}
	
	public ByteCounter(byte[] read, int[] counts) {
		this.read = read;
		
	}
	
	public int getCount(byte b) {
		int count = 0;
		for(int i = 0; i < read.length; i++) {
			if(read[i] == b) count++; 
		}
		return count;
	}
	
	public int[] getCount(byte[] b) {
		if(b == null) return null;
		int count;
		int[] byteCount = new int[b.length];
		for(int i = 0; i < b.length; i++) {
			count = 0;
			for(int j = 0; j < read.length; j++) {
				if(b[i] == read[j]) count++;
			}
			byteCount[i] = count;
		}
		return byteCount;
	}
	
	public byte[] getElements() {
		int count;
		ArrayList<Byte> tempCount = new ArrayList<Byte>();
		ArrayList<Tracker> tempCountT = new ArrayList<Tracker>();
		byte[] values = new byte[256];
		
		for (int i=0; i<256; i++){
			  values[i] = (byte) (char) i;
		}
		
		//byte value order (increasing)
		if(order.equals("bytes")) { 
			for(int i = 0; i < values.length; i++) {
				count = 0;
				for(int j = 0; j < read.length; j++) {
					if(values[i] == read[j]) count++;
				}
				if(count != 0) tempCount.add(values[i]);
			}
			byte[] finCount = new byte[tempCount.size()];
			for(int i = 0; i < tempCount.size(); i++)
				finCount[i] = tempCount.get(i);
			return finCount;
		}
		//Increasing count order
		if(order.equals("countInc")) {
			for(int i = 0; i < values.length; i++) {
				count = 0;
				for(int j = 0; j < read.length; j++) {
					if(values[i] == read[j]) count++;
				}
				if(count != 0) tempCountT.add(new Tracker(count, values[i]));
			}
			/*for (int i = 0; i < (tempCountT.size()-1); i++) //Sorts list based on frequencies
			   {
			      int min = i;
			      for (int j = i+1; j < tempCountT.size(); j++){
			    	  System.out.println(tempCountT.get(j).count + " vs " + tempCountT.get(min).count);
			          if (tempCountT.get(j).count < tempCountT.get(min).count) min = j;
			          }
			      Tracker temp = new Tracker(tempCountT.get(i).count, tempCountT.get(i).value);
			      tempCountT.get(i).set(tempCountT.get(min));
			      tempCountT.get(min).set(temp);
			}
			for(int i = 0; i < tempCountT.size(); i++) {
				if(tempCountT.get(i).count == tempCountT.get(i + 1).count) {
					
				}
			}*/
			Collections.sort(tempCountT);
			byte[] finCount = new byte[tempCountT.size()];
			for(int i = 0; i < tempCountT.size(); i++)
				finCount[i] = tempCountT.get(i).value;
			return finCount;
		}
		//Decreasing count order
		else if(order.equals("countDec")) {
			for(int i = 0; i < values.length; i++) {
				count = 0;
				for(int j = 0; j < read.length; j++) {
					if(values[i] == read[j]) count++;
				}
				if(count != 0) tempCountT.add(new Tracker(count, values[i]));
			}
			/*for (int i = 0; i < (tempCountT.size()-1); i++)
			   {
			      int max = i;
			      for (int j = i+1; j < tempCountT.size(); j++){
			    	  System.out.println(tempCountT.get(j).count + " vs " + tempCountT.get(max).count);
			          if (tempCountT.get(j).count > tempCountT.get(max).count) max = j;}
			      Tracker temp = new Tracker(tempCountT.get(i).count, tempCountT.get(i).value);
			      tempCountT.get(i).set(tempCountT.get(max));
			      tempCountT.get(max).set(temp);
			}*/
				
			Collections.sort(tempCountT, new Comparator<ByteCounter.Tracker>() {

				@Override
				public int compare(Tracker o1, Tracker o2) {
					if(o1.count < o2.count) return 1;
					else if(o1.count == o2.count)
						if(o1.value > o2.value) return 1;
						else return -1;
					else return -1;
				}}
			);
			
			byte[] finCount = new byte[tempCountT.size()];
			for(int i = 0; i < tempCountT.size(); i++)
				finCount[i] = tempCountT.get(i).value;
			return finCount;
		}
		//default is order.equals("byte")
		else{
			for(int i = 0; i < values.length; i++) {
				count = 0;
				for(int j = 0; j < read.length; j++) {
					if(values[i] == read[j]) count++;
				}
				if(count != 0) tempCount.add(values[i]);
			}
			byte[] finCount = new byte[tempCount.size()];
			for(int i = 0; i < tempCount.size(); i++)
				finCount[i] = tempCount.get(i);
			return finCount;
		}
	}
	
	public void setOrder(String order) {
		if(order.equals("byte") || order.equals("countInc") || order.equals("countDec"))
		this.order = order;
		else throw new IllegalArgumentException("SPECIFIED ORDER IS UNAVAILABLE");
	}
	
	public String toString() {
		String result = "";
		byte[] lmnts = getElements();
		if(lmnts.length == 0) return "";
		int[] counts = getCount(lmnts);//read
		if(!format) {
			for(int i = 0; i < lmnts.length; i++) {
				result += lmnts[i];
				result += ":";
				result += counts[i];
				if(i != (lmnts.length - 1)) result += " ";
			}
		}
		else {
			for(int i = 0; i < lmnts.length; i++) {
				result += (char) lmnts[i];
				result += ":";
				result += counts[i];
				if(i != (lmnts.length - 1)) result += " ";
			}
			}
		return result;
	}
	
	public String toString(String format) {
		if(format.equals("char")) this.format = true;
		String result = toString();
		this.format = false;
		return result;
	}
}
