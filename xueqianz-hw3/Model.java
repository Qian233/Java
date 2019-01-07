//Xueqian Zhang id: xueqianz
package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Model {
	static ObservableMap<String,Product> productsMap = FXCollections.observableHashMap();//??
	static ObservableMap<String,Nutrient> nutrientsMap = FXCollections.observableHashMap();//??
	
	//new
	ObservableList<Product> searchResultsList = FXCollections.observableArrayList(); 
	
	void readNutrients(String filename) {
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
		CSVParser csvParser = CSVParser.parse(new FileReader(filename), csvFormat);

		for (CSVRecord csvRecord : csvParser) {
			//create new product object
			Product p = productsMap.get(csvRecord.get(0));
			if(Float.parseFloat(csvRecord.get(4)) != 0) {
				p.getProductNutrients().put(csvRecord.get(1), p.new ProductNutrient(csvRecord.get(1).trim(),Float.parseFloat(csvRecord.get(4).trim())));
			}
			//create new nutrient object if not in the map
			if(!nutrientsMap.containsKey(csvRecord.get(1))) {
				Nutrient nutrient = new Nutrient(csvRecord.get(1), csvRecord.get(2),
						csvRecord.get(5));
				nutrientsMap.put(csvRecord.get(1), nutrient);
			}
		}
		}
		catch (FileNotFoundException e1) { e1.printStackTrace(); }
		catch (IOException e1) { e1.printStackTrace(); }

	}
	
	void readProducts(String filename) {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(filename), csvFormat);
		for (CSVRecord csvRecord : csvParser) {
			Product product = new Product(csvRecord.get(0), csvRecord.get(1),
					csvRecord.get(4), csvRecord.get(7));
			//put product object into map:
			productsMap.put(csvRecord.get(0), product);
		}

		
		}
		catch (FileNotFoundException e1) { e1.printStackTrace(); }
		catch (IOException e1) { e1.printStackTrace(); }	
	}
	
	
	void readServingSizes(String filename) {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
		CSVParser csvParser = CSVParser.parse(new FileReader(filename), csvFormat);
		for (CSVRecord csvRecord : csvParser) {
			//do not need to loop through all the product to find a match, productsMap.get(csvRecord.get(0)) 
			//will return a product(that code is the same as the code from csvRecord.get(0)), can directly call 
			//setXXXXX method
				if(csvRecord.get(1).isEmpty()) {
					productsMap.get(csvRecord.get(0)).setServingSize(0);
				}else {
					productsMap.get(csvRecord.get(0)).setServingSize(Float.parseFloat(csvRecord.get(1).trim()));
				}
					//.isEmpty()
				if(csvRecord.get(2).isEmpty()) {
					productsMap.get(csvRecord.get(0)).setServingUom(" ");;
						
				}else {
					productsMap.get(csvRecord.get(0)).setServingUom(csvRecord.get(2));
				}
					
				if(csvRecord.get(3).isEmpty()) {
					productsMap.get(csvRecord.get(0)).setHouseholdSize(0);
						
				}else {
					productsMap.get(csvRecord.get(0)).setHouseholdSize(Float.parseFloat(csvRecord.get(3).trim()));
				}
					
				if(csvRecord.get(4).isEmpty()) {
					productsMap.get(csvRecord.get(0)).setHouseholdUom(" ");
						
				}else {
					productsMap.get(csvRecord.get(0)).setHouseholdUom(csvRecord.get(4));	
				}
				
			}
		}
		catch (FileNotFoundException e1) { e1.printStackTrace(); }
		catch (IOException e1) { e1.printStackTrace(); }
		
	}
	
	
	boolean readProfile(String filename) {
	
		if(filename.contains("csv")) {
			CSVFiler csv = new CSVFiler();
			return csv.readFile(filename);
		}
		if(filename.contains("xml")) {
			XMLFiler xml = new XMLFiler();
			return xml.readFile(filename);
		
	}
	
		return false;

	}
	//added
//	void writeProfile(String filename) {
//		
//		
//		
//		//Scanner input = new Scanner(System.in);
//		//String[] next = null;
//		try (
//		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
//		){
//		//do {
//		//next = input.nextLine().split("\\s+");
//		bw.write(String.format("%s,%s,%s,%s,%s%n", NutriByte.view.genderComboBox.getValue(), 
//				NutriByte.view.ageTextField.getText(), NutriByte.view.weightTextField.getText(), NutriByte.view.heightTextField.getText(),
//				NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedItem()));
//		//} while (!next[next.length-1].equals("*"));
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		
//		
//		
//		
//	}
	
	//replace writeProfile() method with this method to write profile data:
	void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}

