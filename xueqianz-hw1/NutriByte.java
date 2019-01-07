// Xueqian Zhang id: xueqianz
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import practice.Nutrient;
import practice.Product;
import practice.ProductNutrient;

public class NutriByte {
	Model model = new Model();  				//will handle all data read from the data files
	Scanner input = new Scanner(System.in);  	//to be used for all console i/o in this class

	static final String PRODUCT_FILE = "data/SampleProducts.csv";
	static final String NUTRIENT_FILE = "data/SampleNutrients.csv";
	static final String SERVING_SIZE_FILE = "data/SampleServingSize.csv";

	//do not change this method
	public static void main(String[] args) {
		NutriByte nutriByte = new NutriByte();
		nutriByte.model.readProducts(PRODUCT_FILE);
		nutriByte.model.readNutrients(NUTRIENT_FILE);
		nutriByte.model.readServingSizes(SERVING_SIZE_FILE );
		switch (nutriByte.getMenuChoice()) {
		case 1: {
			nutriByte.printSearchResults(nutriByte.searchProductsWithSelectedIngredients(nutriByte.getIngredientChoice()));
			break;
		}
		case 2: {
			int nutrientChoice = nutriByte.getNutrientChoice();
			nutriByte.printSearchResults(nutriByte.searchProductsWithSelectedNutrient(nutrientChoice), nutrientChoice);
			break;
		}
		case 3:  
		default: System.out.println("Good Bye!"); break;
		}
	}

	//do not change this method
	int getMenuChoice() {
		System.out.println("*** Welcome to NutriByte ***");
		System.out.println("--------------------------------------------------");
		System.out.println("1. Find ingredient(s)");
		System.out.println("2. Find a nutrient");
		System.out.println("3. Exit");
		input = new Scanner(System.in);
		return input.nextInt();
	}

	//do not change this method
	String getIngredientChoice() {
		input.nextLine();
		System.out.println("Type the keywords to search for the ingredients");
		System.out.println("--------------------------------------------------");
		return input.nextLine();
	}

	int getNutrientChoice() {
		//write your code here
		/*
		 * Uses the nutrients[] array in model to display the list of nutrients in three columns. Returns the
		 * choice number selected by the user. (see Figure 5)
		 */
		
		System.out.println("Select the nutrient that you are looking for");
		System.out.println("--------------------------------------------------");
		
		for(int i = 1; i < model.nutrients.length + 1; i++) {
			// add a space before the dot for every item less than 10 to align. 
			if(i < 10) {
				// print 3 columns
				if(i%3 == 0) {
					System.out.println(i + " . " + model.nutrients[i-1].nutrientName);			
				}
				else {
					System.out.print(i + " . ");
					System.out.printf("%-35s", model.nutrients[i-1].nutrientName);
				}
			}
			else {
				// print 3 columns
				if(i%3 == 0) {
					System.out.println(i + ". " + model.nutrients[i-1].nutrientName);			
				}
				else {
					System.out.print(i + ". ");
					System.out.printf("%-35s", model.nutrients[i-1].nutrientName);
				}
			}
		}
		
		System.out.println("\n--------------------------------------------------");
		Scanner input = new Scanner(System.in);
		
		int choiceNum = input.nextInt();
		return choiceNum;
	}

	Product[] searchProductsWithSelectedIngredients(String searchString) {
		/*
		 * Takes a search string as parameter and searches case-insensitively for
		 * products that have the ingredients matching the words in the search string (see Figure 2, Figure 3, and Figure 4).
		 * Returns the search result as an array of products that have the ingredient(s). If no match is found, returns an array
		 * with size 0 (not null)
		 */
		
		//split the search string and store it in a String array
		String[] searchKeyword = searchString.split(" ");
		
		StringBuilder sb2 = new StringBuilder();
		
		//loop through every product
		for(int i = 0; i < model.products.length; i++) {
			
			//loop through every search keyword
			for(int j =0; j < searchKeyword.length; j++) {
				
				//compare the keyword and the product's ingredient
				if(model.products[i].ingredients.toLowerCase().indexOf(searchKeyword[j].toLowerCase()) != -1) {
					
					// if the product has the ingredient, append the index of the product to the StringBuilder sb2.
					sb2.append(i + ";");
				}
			}
		}
		
		// if 0 product is found, return a size 0 Product array.  
		if(sb2.toString().equals("")) {
			Product[] p = new Product[0];
			return p;
		}
		
		String[] indexFound = sb2.toString().split(";");
		
		//StringBuilder indexUnique is to store unique value of indexFound
		StringBuilder indexUnique = new StringBuilder();
		
		//Below is to remove the duplicated product index
		for(int i = 0; i < indexFound.length; i++) {
			if(indexUnique.indexOf(indexFound[i]) == -1) {
				indexUnique.append(indexFound[i] + ";");
				}
			}
		
		// convert StringBuilder indexUnique into String array 
		String[] indexUniqueArray = indexUnique.toString().split(";");
		
		//remove the last none value String that is generated when spiting indexUniqueArray
		String[] indexUniqueArray2 = Arrays.copyOfRange(indexUniqueArray, 0, indexUniqueArray.length);
		
		//Convert String array indexUniqueArray2 to int array index(this store the unique index of product that has the keyword ingredient).
		int[] index = new int[indexUniqueArray2.length];
		for(int i = 0; i < indexUniqueArray2.length; i++) {
			index[i] = Integer.parseInt(indexUniqueArray2[i]);
			}
		
		//store not duplicated products into Product array p
		Product[] p = new Product[index.length];
		for(int i = 0; i < index.length; i++) {
			p[i] = model.products[index[i]];
		}
		return p;
		}

	Product[] searchProductsWithSelectedNutrient(int menuChoice) {
		//Returns an array of Products that have the selected nutrient as per the menu choice.
		
		//find out products that have the selected nutrient as per the menu choice and store the products' ndbNumber into StringBuilder sb
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < model.productNutrients.length; i++) {
			if((model.productNutrients[i].nutrientName.equalsIgnoreCase(model.nutrients[menuChoice-1].nutrientName) && (model.productNutrients[i].quantity != 0))) {
				 sb.append(model.productNutrients[i].ndbNumber + ";");
			 }
		}
		
		String[] pndbNumberHasMenuChoice = sb.toString().split(";");
		
		Product[] p = new Product[pndbNumberHasMenuChoice.length];
		
		//store the product that have the selected nutrient as per the menu choice into Product array p
		for(int i = 0; i < pndbNumberHasMenuChoice.length; i++){
			for(int j = 0; j < model.products.length; j++) {
				if(model.products[j].ndbNumber.equals(pndbNumberHasMenuChoice[i])) {
					p[i] = new Product(model.products[j].ndbNumber, model.products[j].productName, model.products[j].manufacturer, model.products[j].ingredients);
					// also need to store servingSize, servingUom, householdSize, householdUom into the product
					p[i].servingSize = model.products[j].servingSize;
					p[i].servingUom = model.products[j].servingUom;
					p[i].householdSize = model.products[j].householdSize;
					p[i].householdUom = model.products[j].householdUom;
				}
			}
		}
		return p;
	}

	void printSearchResults(Product[] searchResults) {
		/*
		 * This is an overloaded method. Prints the output as shown in Figure 2 , Figure 3, Figure 4, Figure
		 * 5. When a single parameter of type Product[] array is passed, it means that the ingredient search results are to be
		 * printed. 
		 */
		System.out.println("*** " + searchResults.length + " products found***");
		for(int i = 0; i < searchResults.length; i++) {
			System.out.println((i+1) + ". " + searchResults[i].productName + " from " + searchResults[i].manufacturer);
		}
	}
	
	void printSearchResults(Product[] searchResults, int nutrientChoice) {
		/* 
		 * When a Product[] array along with an int x is passed, it means that the nutrient search results are to be
		 * printed for the nutrient at index x in the nutrients array.
		 */
		
		System.out.println("*** " + searchResults.length + " products found***");
		
		ProductNutrient[] pn = new ProductNutrient[searchResults.length];
		ProductNutrient eachPn = null;
		
		//loop through searchResults (the product that have the selected nutrient as per the menu choice)
		for(int i = 0; i < searchResults.length; i++) {
			//loop through all productNutrients
			for(int j = 0; j < model.productNutrients.length; j++) {
				//find out the products' ndbNumber, nutrientCode, nutrientName, quantity, and nutrientUom through matching products' ndbNumber and nutrientName( between searchResults and productNutrients)
				if((searchResults[i].ndbNumber.equals(model.productNutrients[j].ndbNumber) && model.productNutrients[j].nutrientName.equals(model.nutrients[nutrientChoice-1].nutrientName))) {
					eachPn = new ProductNutrient(model.productNutrients[j].ndbNumber, model.productNutrients[j].nutrientCode, model.productNutrients[j].nutrientName, model.productNutrients[j].quantity, model.productNutrients[j].nutrientUom);
					//Store the result in ProductNutrient[] pn
					pn[i] = eachPn;
				}
			}
		}
		

		for(int i = 0; i < searchResults.length; i++) {
			System.out.print((i+1) + ". ");
			System.out.print(searchResults[i].householdSize+ " " + searchResults[i].householdUom + " of " + pn[i].ndbNumber + ": " + searchResults[i].productName + " has ");
			System.out.printf("%.2f", pn[i].quantity*searchResults[i].servingSize/100);
			System.out.println(pn[i].nutrientUom + " of " + model.nutrients[nutrientChoice-1].nutrientName);
		}
	}
}








