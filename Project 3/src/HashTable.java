import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class HashTable {

	LinkedList<HashEntry>[] table;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		table = new LinkedList[10];
		for(int i = 0; i < 10; i++) {
			table[i] = new LinkedList<HashEntry>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		table = new LinkedList[size];
		for(int i = 0; i < size; i++) {
			table[i] = new LinkedList<HashEntry>();
		}
	}
	
	void put(String key, int value) {
		int code = Math.abs(key.hashCode());
		code = code % table.length;
		int check = getPlace(key, code);
		if(check != -1) {//if it is already in table
			HashEntry hashCurr = table[code].get(check);
			int count = hashCurr.getValue();//increase count
			hashCurr.setValue(count + value);
		}
		else
			table[code].add(new HashEntry(key, value));//else add it to table
	}
	
	void put(String key, int value, int hashCode) {
		int code = Math.abs(hashCode) % table.length;
		int check = getPlace(key, code);
		if(check != -1) {//if it is already in table
			int count = table[code].get(check).getValue();//increase count
			table[code].get(check).setValue(count + value);
		}
		else
			table[code].add(new HashEntry(key, value));//else add it to table
	}
	
	void putPair(String key, String key2, int value) {
		int check = -1;
		int loc = 0;
		if(table.length != 0) {
		for(int i = 0; i < table.length; i++)
			if(table[i].get(0).getKey().equals(key)) {//vertical search
				check = 0;
				loc = i;
			}
		if(check != -1) {//if it is already in table
			for(int i = 1; i < table[loc].size(); i++)//horizontal search
			if(table[loc].get(i).getKey().equals(key2)) {
				table[loc].get(i).setValue(table[loc].get(i).getValue() + 1);
				return;
			}
			table[loc].add(new HashEntry(key2, key, value));
		}
		else {
				rehash();
				table[table.length - 1].add(new HashEntry(key, 0));//else add it to table
				table[table.length - 1].add(new HashEntry(key2, key, value));
			}
		}
		else {
				rehash();
				table[table.length - 1].add(new HashEntry(key, 0));//else add it to table
				table[table.length - 1].add(new HashEntry(key2, key, value));
		}
	}
	
	int getPair(String w1, String w2) {
		for(int i = 0; i < table.length; i++)
			if(table[i].get(0).getKey().equals(w1)) {//vertical search
				for(int j = 1; j < table[i].size(); j++) {
					if(table[i].get(j).getKey().equals(w2))//Horizontal Search
						return table[i].get(j).getValue();
				}
				return -1;
			}
		return -1; //If pair is not in table
	}
	
	void update(String key, int value) {
		int code = Math.abs(key.hashCode());
		code = code % table.length;
		int check = getPlace(key, code);
		if(check != -1) {//if it is already in table
			HashEntry hashCurr = table[code].get(check);
			hashCurr.setValue(value);
		}
		else
			table[code].add(new HashEntry(key, value));//else add it to table
	}
	
	int getPlace(String key, int code) {
		for(int i = 0; i < table[code].size(); i++) {
			if(table[code].get(i).getKey().equals(key)) 
				return i;
		}
		return -1;
	}
	
	/*int getPlace(String key, int hashCode) {
		int code = Math.abs(hashCode) % table.length;
		for(int i = 0; i < table[code].size(); i++) {
			if(table[code].get(i).getKey().equals(key)) 
				return i;
		}
		return -1;
	}*/
	
	int get(String key) {
		int code = Math.abs(key.hashCode()) % table.length;
		for(int i = 0; i < table[code].size(); i++) {
			if(table[code].get(i).getKey().equals(key)) 
				return table[code].get(i).getValue();
		}
		return -1;
	}
	
	int get(String key, int hashCode) {
		int code = Math.abs(hashCode) % table.length;
		for(int i = 0; i < table[code].size(); i++) {
			if(table[code].get(i).getKey().equals(key)) 
				return table[code].get(i).getValue();
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	void rehash() {
		LinkedList<HashEntry>[] temp = new LinkedList[table.length + 1];
		for(int i = 0; i < table.length; i++)
			temp[i] = table[i];
		temp[table.length] = new LinkedList<HashEntry>();
		table = temp;
	}
	
	ArrayList<HashEntry> generateWordRank() {
		ArrayList<HashEntry> WRank = new ArrayList<HashEntry>();
		for(int i = 0; i < table.length; i++) 
			for(int j = 0; j < table[i].size(); j++)
				WRank.add(table[i].get(j));
		Collections.sort(WRank);
		return WRank;
	}
	
	ArrayList<HashEntry> generatePairRank() {
		ArrayList<HashEntry> PRank = new ArrayList<HashEntry>();
		for(int i = 0; i < table.length; i++) 
			for(int j = 1; j < table[i].size(); j++)
				PRank.add(table[i].get(j));
		Collections.sort(PRank);
		return PRank;
	}
	
	ArrayList<HashEntry> generateReverseRank() {
		ArrayList<HashEntry> WRank = new ArrayList<HashEntry>();
		for(int i = 0; i < table.length; i++) 
			for(int j = 0; j < table[i].size(); j++) {
				WRank.add(table[i].get(j));
				WRank.get(WRank.size() - 1).setSH();
			}
		Collections.sort(WRank);
		return WRank;
	}
	
}
