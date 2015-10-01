import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordStat {

	HashTable hashing;
	Tokenizer tokening;
	HashTable pairs;
	ArrayList<HashEntry> WRank;
	ArrayList<HashEntry> PRank;
	ArrayList<HashEntry> ReverseWRank;
	
	public WordStat(String input) throws FileNotFoundException {
		tokening = new Tokenizer(input);//creates statistics/data necessary for stat computing
		hashing = new HashTable();
		pairs = new HashTable(0);
		for(int i = 0; i < tokening.wordList().size(); i++) 
			hashing.put(tokening.wordList().get(i), 1);
		for(int i = 0; i < tokening.wordList().size() - 1; i++)
			pairs.putPair(tokening.wordList().get(i), tokening.wordList().get(i + 1), 1);
		WRank = hashing.generateWordRank();
		PRank = pairs.generatePairRank();
		ReverseWRank = hashing.generateReverseRank();
	}
	
	public WordStat(String[] input) {
		tokening = new Tokenizer(input);
		hashing = new HashTable();
		pairs = new HashTable(0);
		for(int i = 0; i < tokening.wordList().size(); i++) 
			hashing.put(tokening.wordList().get(i), 1);
		for(int i = 0; i < tokening.wordList().size() - 1; i++)
			pairs.putPair(tokening.wordList().get(i), tokening.wordList().get(i+1), 1);
		WRank = hashing.generateWordRank();
		PRank = pairs.generatePairRank();
		ReverseWRank = hashing.generateReverseRank();
	}
	
	int wordCount(String word) {
		int count =  hashing.get(word.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase());
		if(count == -1) return 0;
		else return count;
	}
	
	int wordPairCount(String w1, String w2) {
		int count = pairs.getPair(w1.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase(), w2.replaceAll(" ", "").replaceAll("\\p{P}", "").toLowerCase());
		if(count == -1) return 0;
		else return count;
	}
	
	int wordRank(String word) {
		for(int i = 0; i < WRank.size(); i ++)
			if(WRank.get(i).getKey().equals(word.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase())) return i + 1;
		return 0;
	}
	
	int wordPairRank(String w1, String w2) {
		for(int i = 0; i < PRank.size(); i ++)
			if(PRank.get(i).getKey().equals(w2.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase()) && PRank.get(i).getPrev().equals(w1.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase())) return i + 1;
		return 0;
	}
	
	String [] mostCommonWords(int k) {
		if(k > WRank.size()) k = WRank.size();
		String[] topX = new String[k];
		for(int i = 0; i < k; i++) 
			topX[i] = WRank.get(i).getKey();
		return topX;
	}
	
	String [] leastCommonWords(int k) {
		if(k > ReverseWRank.size()) k = ReverseWRank.size();
		String[] topX = new String[k];
		for(int i = 0; i < k; i++) 
			topX[i] = ReverseWRank.get(i).getKey();
		return topX;
		/*
		if(k > WRank.size()) k = WRank.size();
		String[] botX = new String[k];
		for(int i = 0; i < WRank.size() - 1; i++) 
			botX[i] = WRank.get((WRank.size() - 1) - i).getKey();
		return botX;*/
	}
	
	String [] mostCommonWordPairs(int k) {
		if(k > PRank.size()) k = PRank.size();
		String[] topX = new String[k];
		for(int i = 0; i < k; i++) 
			topX[i] = PRank.get(i).getPrev() + " " +  PRank.get(i).getKey();
		return topX;
	}
	
	String [] mostCommonCollocs(int k, String baseWord, int i) {//goes to different methods for right/left collocs
		if(i > 0) 
			return RightCollocs(k, baseWord.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase());
		else if(i < 0) 
			return LeftCollocs(k, baseWord.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase());
		else return null;
	}
	
	String[] RightCollocs(int k, String baseWord) {
		String[] collocs = new String[k];
		int j = 0;
		for(int i = 0; i < PRank.size(); i++) {
			if(PRank.get(i).getPrev().equals(baseWord)) {
				collocs[j] = PRank.get(i).getKey();
				j++;
				if(j == k) break;
			}
		}
		return collocs;
	}
	
	String[] LeftCollocs(int k, String baseWord) {
		String[] collocs = new String[k];
		int j = 0;
		for(int i = 0; i < PRank.size(); i++) {
			if(PRank.get(i).getKey().equals(baseWord)) {
				collocs[j] = PRank.get(i).getPrev();
				j++;
				if(j == k) break;
			}
		}
		return collocs;
	}
	
	String [] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) {//same layout as normal collocs (2 sep method)
		if(i > 0) 
			return RightCollocsExc(k, baseWord.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase(), exclusions);
		else if(i < 0) 
			return LeftCollocsExc(k, baseWord.replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase(), exclusions);
		else return null;
	}
	
	String[] RightCollocsExc(int k, String baseWord, String[] exclusions) {
		String[] collocs = new String[k];
		int j = 0;
		int check = 0;
		for(int i = 0; i < PRank.size(); i++) {
			check = 0;
			if(PRank.get(i).getPrev().equals(baseWord)) {
				for(int l = 0; l < exclusions.length; l++) {
					if(PRank.get(i).getKey().equals(exclusions[l].replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase())) {
						check = -1;
						break;
					}
				}
				if(check != -1) {
					collocs[j] = PRank.get(i).getKey();
					j++;
					if(j == k) break;
				}
			}
		}
		return collocs;
	}
	
	String[] LeftCollocsExc(int k, String baseWord, String[] exclusions) {
		String[] collocs = new String[k];
		int j = 0;
		int check = 0;
		for(int i = 0; i < PRank.size(); i++) {
			check = 0;
			if(PRank.get(i).getKey().equals(baseWord)) {
				for(int l = 0; l < exclusions.length; l++) {
					if(PRank.get(i).getPrev().equals(exclusions[l].replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase())) {
						check = -1;
						break;
					}
				}
				if(check != -1) {
					collocs[j] = PRank.get(i).getPrev();
					j++;
					if(j == k) break;
				}
			}
		}
		return collocs;
		/*//older version of collocs left before testing
		String[] collocs = new String[k];
		int j = 0;
		for(int i = 0; i < PRank.size(); i++) {
			if(PRank.get(i).getKey().equals(baseWord)) {
				for(int l = 0; l < exclusions.length; l++)
					if(PRank.get(i).getPrev().equals(exclusions[l].replaceAll(" ", "").replaceAll("[^a-zA-Z]", "").toLowerCase()))
						continue;
				collocs[j] = PRank.get(i).getPrev();
				j++;
				if(j == k) break;
			}
		}
		return collocs;*/
	}
	
	String generateWordString(int k, String startWord) {
		/*for(int i = 0; i < )
			
		if(k == 0) return ;
		return  + " " + generateWordString(k - 1, );*/
		return "Did not implement for extra credit.";
	}
	
}
