//Xueqian Zhang id: xueqianz
package lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
	public final static String DICTIONARY = "NewDictionary.txt";
	List<Word> wordList = new ArrayList<>();
	Map<String, Word> singleMap = new HashMap<>();
	Map<String, List<Word>> multiMap = new HashMap<>();

	//DO NOT CHANGE THIS METHOD
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.loadWordList();
		d.loadSingleMap();
		d.loadMultiMap();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter search word");
		String searchWord = input.nextLine();
		
		System.out.println("------------WordList Search-----------");
		d.searchWordList(searchWord);
		System.out.println("*******************************");
		System.out.println("------------SingleMap Search-----------");
		d.searchSingleMap(searchWord);
		System.out.println("*******************************");
		System.out.println("------------MultiMap Search-----------");
		d.searchMultiMap(searchWord);
		System.out.println("*******************************");
		input.close();
	}
	
	//DO NOT CHANGE THIS METHOD
	/**loadWordList() reads the txt file. For each line, it invokes 
	 * getWord() method that returns a Word object. This object is then
	 * added to the arrayList wordList
	 */
	void loadWordList() {
		String wordString = null;
		try {
			Scanner input = new Scanner(new File(DICTIONARY));
			while (input.hasNextLine()) {
				wordString = input.nextLine();
				if (wordString.length() == 0) continue; //blank line
				wordList.add(getWord(wordString));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	//DO NOT CHANGE THIS METHOD
	/** getWord() takes a wordString and splits it on "(". The first
	 * element after split is the word, and rest are elements of its meaning. 
	 * So it uses first element to initialize 'word' of Word, and rest to 
	 * initialize 'meaning' of Word. As '(' may occur anywhere in the 
	 * 'meaning', the split string is put back together by putting
	 * '(' in front of each piece.  
	 * @param wordString
	 * @return
	 */
	Word getWord(String wordString) {
		String[] splits = wordString.split("\\(");  //split on (
		String word = null;
		StringBuilder wordMeaningString = new StringBuilder();
		if (splits[0].length() >0) 
			word = splits[0].trim();  //get the first string as it is the word
		for (int i = 1; i < splits.length; i++) {
			wordMeaningString.append("(" + splits[i]); //put back rest of the string together
		}
		return new Word(word, wordMeaningString.toString());
	}

	/** loadSingleMap() takes each word from
	 * wordList and loads it into singleMap with key being
	 * the Word's word in lowercase, and its value being the whole 
	 * Word object.
	 */
	void loadSingleMap() {
		//write your code here
 
		for(Word w: wordList){
			singleMap.put(w.word.toLowerCase(), w);
		}
	}

	/**loadMultiMap() takes each word from wordList and loads it 
	 * into multiMap with key being the Word's word in lowercase, and 
	 * its value being a list of all its meaning found in the 
	 * dictionary
	 */
	void loadMultiMap() {
		//write your code here
		for(Word w:wordList) {
			List<Word> list = new ArrayList<>();
			if(!multiMap.containsKey(w.word.toLowerCase())) {
				list.add(w);
				multiMap.put(w.word.toLowerCase(), list);
			}else {
				multiMap.get(w.word.toLowerCase()).add(w);
			}
		}
//		Iterator<Word> iter = wordList.iterator();
//		ArrayList<String> uniqueWord = new ArrayList<>();
//		//generate a unique list 
//		while(iter.hasNext()) {
//			if(!uniqueWord.contains(iter.next().word)) {
//				uniqueWord.add(iter.next().word);
//			}
//		}
//		
//		
//			for(int i = 0; i < uniqueWord.size();i++) {
//				List<Word> meaningList = null;
//				for(Word w: wordList){
//					if(uniqueWord.get(i).equals(w.word)) {
//						meaningList.add(w);
//					}
//				}
//				multiMap.put(uniqueWord.get(i).toLowerCase(),meaningList);
//			}


	}
	
	/** searchWordList() takes a searchWord String and and searches for it in wordList.
	 * If found, it prints all its meanings. Else it prints 'Sorry! <word> not found!'
	 * @param searchWord
	 */
	void searchWordList(String searchWord) {
		//write your code here
		boolean b = false;
		for(Word w:wordList) {
			if(w.word.toLowerCase().equals(searchWord.toLowerCase())) {
				System.out.println(w.word+" "+w.meaning);
				b = true;
			}
			
		}
		if(b==false) {
			System.out.println("Sorry! "+searchWord+" not found!");
		}
		}
	
	/** searchSingleMap() takes a searchWord String and searches for it in singleMap.
	 * If found, it prints its meaning. Else it prints 'Sorry! <word> not found!'. 
	 * Note that key is lowercase in the map.
	 * @param searchWord
	 */
	void searchSingleMap(String searchWord) {
		//write your code here
		if(singleMap.containsKey(searchWord.toLowerCase())) {
			System.out.println(singleMap.get(searchWord.toLowerCase()).word+" "+singleMap.get(searchWord.toLowerCase()).meaning);
		}else {
			System.out.println("Sorry! "+searchWord+" not found!");
		}
//		for(Map.Entry<String,Word> e: singleMap.entrySet()) {
//			if(searchWord.toLowerCase().equals(e.getKey())) {
//				System.out.println(e.getValue().word+" "+e.getValue().meaning);
//			}
//		}
	}

	/** searchMultiMap() takes a searchWord String and searches for it in multiMap. 
	 * If found, it prints all its meanings. Else it prints 'Sorry! <word> not found!'
	 * Note that key is lowercase in the map.
	 * @param searchWord
	 */
	void searchMultiMap(String searchWord) {
		//write your code here
		
		if(multiMap.containsKey(searchWord.toLowerCase())) {
			for(int i= 0; i < multiMap.get(searchWord.toLowerCase()).size();i++) {
				System.out.println(multiMap.get(searchWord.toLowerCase()).get(i).word+" "+multiMap.get(searchWord.toLowerCase()).get(i).meaning);
			}
		}else {
			System.out.println("Sorry! "+searchWord+" not found!");
		}
		
	}
}
