import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Tokenizer {

	private ArrayList<String> words = new ArrayList<String>();
	
	public Tokenizer(String input) throws FileNotFoundException {
		Scanner scan = new Scanner(new FileReader(input));
		String[] split;
		String word;
		while(scan.hasNextLine()) {//Adds tokens to 'words' until no tokens remain
			split = scan.nextLine().split(" "); //Removes whitespace
			for(int i = 0; i< split.length; i++) {
				word = split[i].replaceAll("[^a-zA-Z]", "").toLowerCase(); //Removes punc. & toLowerCase
				word = word.replaceAll(" ", "");
				if(word.length() != 0) //removes blank indexes due to multiple spaces
					words.add(word);
			}
		}
		scan.close(); //Close input stream
	}
	
	public Tokenizer(String[] input) {
		String[] split;
		String word;
		for(int i  = 0; i < input.length; i++) {
		split = input[i].split(" "); //Removes whitespace
			for(int j = 0; j< split.length; j++) {
				word = split[j].replaceAll("[^a-zA-Z]", "").toLowerCase(); //Removes punc. & toLowerCase
				word = word.replaceAll(" ", "");
				if(word.length() != 0) //removes blank indexes due to multiple spaces
					words.add(word);
			}
		}
	}
	
	public ArrayList<String> wordList() {
		return words;
	}
	
}
