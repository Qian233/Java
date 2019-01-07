package lab1;

import java.util.Arrays;
import java.util.Scanner;

/**NameSorter class takes n number of names in the form of string inputs 
 * from the user. It then asks user for which name to search for. 
 * It sorts the names entered by the user, and then prints the 
 * position of the search-name in the sorted list of the names, 
 * if it is found.
 */

public class NameSorter {
	Scanner input = new Scanner(System.in);
	
	/**getNameInputs takes an int parameter n and creates an array of size n. 
	 * It then asks user to Enter n names that get stored in the array. 
	 * It uses the helper method toTitleCase() to convert all names to Title case. 
	 * It returns the array filled with names entered by the user.
	 */
	String[] getNameInputs(int n) {
		StringBuilder nameArray = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter names " + (i+1));
			String name = input.next();
			nameArray.append(toTitleCase(name) + ",");
		}
		String[] names = nameArray.toString().split(",");

		return names;
	}
	
	/**toTitleCase() takes one string argument as name and returns the string in title case. 
	 * If the name is null or the string is empty, it returns null.
	 */
	String toTitleCase(String name) {
		String titleName = "";
		if (name == null || name == "") {
			return null;
		}
		else {
			String char1 = Character.toString(name.charAt(0)).toUpperCase();
			titleName = char1 + name.substring(1).toLowerCase();
		}
		return titleName;
	}

	/**sortAndSearch() takes two arguments. The first is an array of strings and the second
	 * is a searchString. The method first sorts the array in increasing alphabetical order, 
	 * and prints it in that order.
	 * It then searches for the searchString in a case-insensitive way. If the searchString is found,
	 * it returns the position of the searchString according to the sorted list. 
	 * If it is not found, then it returns -1.
	 */
	int sortAndsearch(String[] strings, String searchString) {
		if(strings == null) {
			return -1;
		}
		Arrays.sort(strings);
		StringBuilder sortNames = new StringBuilder();
		for(int i = 0; i < strings.length; i++) {
			 System.out.println((i+1) + ". " + strings[i]);
			 sortNames.append(strings[i] + ",");

		}
		
		String[] sortNameString = sortNames.toString().split(",");
		if(searchString == null) {
			return -1;
		}
		int i;
		for (i = 0; i < sortNameString.length; i++) {
			if (searchString.equalsIgnoreCase(sortNameString[i])) {
				return i;
			}
			
			
		}
		return -1;
	}

	/**DO NOT CHANGE THIS METHOD */
	public static void main(String[] args) {
		NameSorter ns = new NameSorter();
		System.out.println("*** How many names to store? ***");
		int n = ns.input.nextInt();
		if (n > 0) {
			String[] names = ns.getNameInputs(n);
			System.out.println("*** Enter the name to search ***");
			String name = ns.input.next();
			int position = ns.sortAndsearch(names, name);
			if (position >=0 ) System.out.println(name + " found at position " + (position+1));
			else System.out.println("Sorry! " + name + " not found!");
		} else System.out.println("Good Bye!");
	}
}
