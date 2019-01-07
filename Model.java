// Xueqian Zhang id: xueqianz
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import practice.Nutrient;
import practice.Product;
import practice.ProductNutrient;

class Model{
	Product[] products;
	Nutrient[] nutrients;
	ProductNutrient[] productNutrients; 
	
	void readProducts(String filename) {
		/*
		 * Reads NutriByte.NUTRIENT_FILE to load objects in the nutrients and productNutrients arrays. Note
		 * that the nutrients array will hold only unique nutrient objects, stored in the order in which they are read from the file.
		 */
		// read the file
		Scanner fileinput = null;
		try {
			fileinput = new Scanner(new File(filename));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		while(fileinput.hasNextLine()) {
			sb.append(fileinput.nextLine() + "\n");
		}
		
		String[] fileContent = sb.toString().split("\n");
		
		//initialize products
		products = new Product[fileContent.length-1];
		
		StringBuilder sb2 = new StringBuilder();
		for(String content: fileContent) {
			sb2.append(content + "\n");
		}
		
		Scanner data = new Scanner(sb2.toString());
		
		//remove headers
		data.nextLine();
		
		int i = 0; 
		while(data.hasNextLine()) {
			// use delimiter ","
			Scanner record = new Scanner(data.nextLine()).useDelimiter("\",\"");
			
			//use " to delimit
			StringBuilder sb3 = new StringBuilder();
			while(record.hasNext()){
				sb3.append(record.next() + "\"") ;
			}
			
			// use delimiter " to split the string
			String[] rrecord = sb3.toString().split("\"");
			
			Product p = new Product("", "","","");
			if(rrecord.length == 9) {
				p = new Product(rrecord[1], rrecord[2], rrecord[5], rrecord[8]);
			}
			//if the ingredients_english column does not have value, the length is 8.
			if(rrecord.length == 8) {
				p = new Product(rrecord[1], rrecord[2], rrecord[5], " ");
			}
			products[i] = p;
			i++;
		}
	}
	
	void readNutrients(String filename) {
		//Reads NutriByte.PRODUCT_FILE file to load product objects in the products array
		
		//read the file
		Scanner fileinput = null;
		try {
			fileinput = new Scanner(new File(filename));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		while(fileinput.hasNextLine()) {
			sb.append(fileinput.nextLine() + "\n");
		}
		
		String[] fileContent = sb.toString().split("\n");
		
		StringBuilder sb2 = new StringBuilder();
		for(String content: fileContent) {
			sb2.append(content + "\n");
		}
		
		Scanner data = new Scanner(sb2.toString());
		
		//ignore the headers
		data.nextLine();
		
		//Nutrient[] with duplicate nutrient
		Nutrient[] duplicateNutrients = new Nutrient[fileContent.length-1];
		
		productNutrients = new ProductNutrient[fileContent.length-1]; 
		
		int i = 0;//Count the number of line
		
		while(data.hasNextLine()) {
			// use delimiter "," 
			Scanner record = new Scanner(data.nextLine()).useDelimiter("\",\"");
			
			StringBuilder sb3 = new StringBuilder();
			while(record.hasNext()){
				//use " to delimit
				sb3.append(record.next() + "\"") ;
			}
			
			// use delimiter " to split the string
			String[] rrecord = sb3.toString().split("\"");
			
			Nutrient n = new Nutrient(rrecord[2], rrecord[3], rrecord[6]);
			ProductNutrient pr = new ProductNutrient(rrecord[1], rrecord[2], rrecord[3], Float.parseFloat(rrecord[5]), rrecord[6]);
			
			duplicateNutrients[i] = n;
			
			productNutrients[i] = pr;
			
			i++;
		}
		
		String[] nutriName = new String[duplicateNutrients.length];
		String[] nutriCode = new String[duplicateNutrients.length];
		String[] nutriUom = new String[duplicateNutrients.length];
		
		for(int j = 0; j < duplicateNutrients.length; j++) {
			nutriName[j] = duplicateNutrients[j].nutrientName;
			nutriCode[j] = duplicateNutrients[j].nutrientCode;
			nutriUom[j] = duplicateNutrients[j].nutrientUom;
		}
		
		StringBuilder distinctNutrientName = new StringBuilder();
		StringBuilder distinctNutrientCode = new StringBuilder();
		StringBuilder distinctNutrientUom = new StringBuilder();
		
		//remove duplicate nutrient
		for(int k=0;k<nutriName.length;k++){
			if(distinctNutrientName.indexOf(nutriName[k]) == -1) {
				distinctNutrientName.append(nutriName[k] + ";");
				distinctNutrientCode.append(nutriCode[k] + ";");
				distinctNutrientUom.append(nutriUom[k] + ";");
			}
            }
		
		String[] dNutrientName = distinctNutrientName.toString().split(";");
		String[] dNutrientCode = distinctNutrientCode.toString().split(";");
		String[] dNutrientUom = distinctNutrientUom.toString().split(";");
		
		//load data into nutrients (dones't have duplicate)
		nutrients = new Nutrient[dNutrientName.length];
		for(int l = 0; l < dNutrientName.length; l++) {
			Nutrient n = new Nutrient(dNutrientCode[l], dNutrientName[l], dNutrientUom[l]);
			nutrients[l] = n;
		}
	}
	
	void readServingSizes(String filename) {
		/*
		 * Reads NutriByte.SERVING_SIZE_FILE to populate four fields ¨C servingSize, servingUom,
		 * householdSize, householdUom - in each product object in the products array
		 */
		//read file
		Scanner fileinput = null;
		try {
			fileinput = new Scanner(new File(filename));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		while(fileinput.hasNextLine()) {
			sb.append(fileinput.nextLine() + "\n");
		}
		
		String[] fileContent = sb.toString().split("\n");
		
		StringBuilder sb2 = new StringBuilder();
		for(String content: fileContent) {
			sb2.append(content + "\n");
		}
		
		Scanner data = new Scanner(sb2.toString());
		
		//ignore the headers
		data.nextLine();
		
		// use delimiter ","
		while(data.hasNextLine()) {
			Scanner record = new Scanner(data.nextLine()).useDelimiter("\",\"");
			
			StringBuilder sb3 = new StringBuilder();
			
			//use " to delimit 
			while(record.hasNext()){
				sb3.append(record.next() + "\"") ;
			}
			
			// use delimiter " to split the string
			String[] rrecord = sb3.toString().split("\"");
			
			// load data into product[] from the file ServingSize
			for(int i = 0; i < products.length; i++) {
				if(products[i].ndbNumber.equals(rrecord[1])) {
					products[i].servingSize = Float.parseFloat(rrecord[2]);
					products[i].servingUom = rrecord[3];
					products[i].householdSize = Float.parseFloat(rrecord[4]);
					products[i].householdUom = rrecord[5];
				}
			}	
			}
		}
	}