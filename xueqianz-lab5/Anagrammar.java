//name£ºXueqian Zhang and Andrew ID: xueqianz
package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrammar {
	String[] words;		//stores all words from the dictionary	
	boolean isInDictionary; //true if the clue word exists in dictionary
	boolean hasAnagrams;	//true if the clue word has anagrams
	String[] anagramArray;	//stores all anagrams of clue-word, if found
	

	
	
	//DO NOT change loadWords method
	/**loadWords method reads the file and loads all words 
	 * into the words array */
	void loadWords() {
		Scanner fileInput = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			fileInput = new Scanner (new File("dictionary.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileInput.hasNextLine()) fileContent.append(fileInput.nextLine() + "\n");
		words = fileContent.toString().split("\n");
	}
	
	
	/** findAnagrams method traverses the words array and looks 
	 * for anagrams of the clue. While doing so, if the clue-word itself is found in  
	 * words array, it sets isInDictionary to true.
	 * If it finds any anagram of the clue, it sets hasAnagram to true. 
	 * It loads the anagrams into anagramArray */
	void findAnagrams(String clue) {
		//write your code here
		char[] cluearray = clue.toCharArray();
		Arrays.sort(cluearray);
		char[] dicarray = null;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(clue)) {
				isInDictionary = true;
			}

			
			dicarray = words[i].toCharArray();
			Arrays.sort(dicarray);
			
			if((!words[i].equals(clue) && Arrays.equals(cluearray, dicarray))) {
				hasAnagrams = true;
				sb.append(words[i] + ",");
			}
			anagramArray = sb.toString().split(",");
			
		}
	
	}
}
