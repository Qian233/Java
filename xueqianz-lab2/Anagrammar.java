package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrammar {
	String[] words;		//stores all words from the dictionary
	String filename, clue;	
	boolean isInDictionary; //true if the clue word exists in dictionary
	boolean hasAnagrams;	//true if the clue word has anagrams
	String[] anagramArray;	//stores all anagrams of clue-word, if found
	
	//DO NOT change main method
	public static void main(String[] args) {
		Anagrammar ag = new Anagrammar();
		ag.getInputs();
		ag.loadWords();
		ag.findAnagrams();
		ag.printResult();
	}
	
	
	//DO NOT change loadWords method
	/**loadWords method reads the file and loads all words 
	 * into the words array */
	void loadWords() {
		Scanner fileInput = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			fileInput = new Scanner (new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileInput.hasNextLine()) fileContent.append(fileInput.nextLine() + "\n");
		words = fileContent.toString().split("\n");
	}
	
	/** getInputs method takes two inputs from the user to 
	 * initialize the member variables filename and clue */
	void getInputs() {
		//write your code here
		System.out.println("Enter file name");
		Scanner input = new Scanner(System.in);
		filename = input.nextLine();
		System.out.println("Enter the word");
		Scanner input2 = new Scanner(System.in);
		clue = input.nextLine();
	}
	
	
	/** findAnagrams method traverses the words array and looks 
	 * for anagrams of the clue. While doing so, if the clue-word itself is found in  
	 * words array, it sets isInDictionary to true.
	 * If it finds any anagram of the clue, it sets hasAnagram to true. 
	 * It loads the anagrams into anagramArray */
	void findAnagrams() {
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
	
	/** printResult method prints the outputs 
	 * as shown in the lab-handout */
	void printResult() {
		//write your code here
		if(isInDictionary && hasAnagrams) {
			System.out.println(anagramArray.length + " anagram(s) found");
			for (int i = 1; i <= anagramArray.length; i++) {
				System.out.println(i + ". " + anagramArray[i-1]);
			}
		}
		if (!isInDictionary && !hasAnagrams) {
			System.out.println(clue + " not found in the dictionary");
			System.out.println("Sorry! No anagram found!");
		}
		if(!isInDictionary && hasAnagrams) {
			System.out.println(clue + " not found in the dictionary");
			System.out.println(anagramArray.length + " anagram(s) found");
			for (int i = 1; i <= anagramArray.length; i++) {
				System.out.println(i + ". " + anagramArray[i-1]);
		}
		}
		if (isInDictionary && !hasAnagrams) {
			System.out.println("Sorry! No anagram found!");
		}
}
}
