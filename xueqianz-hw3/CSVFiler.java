//Xueqian Zhang id: xueqianz
package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import hw3.Product.ProductNutrient;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class CSVFiler extends DataFiler {

	@Override
	//saveTextToFile method in Model class can write file, so do not need to use this method.
	public void writeFile(String filename) {
		// TODO Auto-generated method stub

	}
	
	//used for reading file 
	// data: first line in the file
	Person validatePersonData(String data) {
		String gender;
		float age, weight, height, pa;
		
		String[] personData = data.split(",");
		
		//1.check gender
		try {
		if(!(personData[0].trim().toLowerCase().equals("female")||personData[0].trim().toLowerCase().equals("male"))) {
			throw new InvalidProfileException("The profile must have gender:\n Female or Male as first word.");
		}else {
			gender = personData[0].trim();
			
			//2. check age
			try {
				age  = Float.parseFloat(personData[1].trim());
				if (age < 0) {
					throw new InvalidProfileException("Age must be a positive number.");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid data for age: " + personData[1] + "\nAge must be a number");
			}
			
			//3.check weight 
			try {
				weight = Float.parseFloat(personData[2].trim());
				if (weight < 0) {
					throw new InvalidProfileException("Weight cannot be negative");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid data for weight: " + personData[2] + "\nWeight must be a number");
			}
			
			//4. check height 
			try {
				height = Float.parseFloat(personData[3].trim());
				if (height < 0) {
					throw new InvalidProfileException("Height cannot be negative");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid data for height: " + personData[3] + "\nHeight must be a number");
			}
			
			//5. check physical activities
			try {
				pa = Float.parseFloat(personData[4].trim());
				if (pa != 1.0f && pa != 1.1f && pa != 1.25f && pa != 1.48f) {
					throw new InvalidProfileException("Invalid physical activity level: " + pa + "\nMust be: 1.0, 1.1, 1.25 or 1.48");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid physical activity level: " + personData[4] + "\nMust be: 1.0, 1.1, 1.25 or 1.48");
			}
			
			//6. ingredientToWatch
			StringBuilder ingredientToWatch = new StringBuilder();
			for (int i = 5; i < personData.length; i++ ) {
				ingredientToWatch.append(personData[i] + " ");
			}
			
			if(gender.equalsIgnoreCase("female")) {
				return new Female(age,weight,height, pa,ingredientToWatch.toString().trim());
			}else {
				return new Male(age,weight,height, pa,ingredientToWatch.toString().trim());
			}
		}
		}catch(InvalidProfileException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Profile Data Error" );
			alert.setTitle("NutriByte 3.0");
			alert.setContentText("Could not read profile data.");
			alert.showAndWait();
			return null;
		}
		}
	//used for saving file
	Person validatePersonDataSaveFile(String data) {
		String gender;
		float age, weight, height, pa;
		
		String[] personData = data.split(",");
		
		//1.check gender
		try {
		if(!(personData[0].trim().toLowerCase().equals("female")||personData[0].trim().toLowerCase().equals("male"))) {
			throw new InvalidProfileException("Missing gender information.");
		}else {
			gender = personData[0].trim();
			
			//2. check age
			try {
				if(NutriByte.view.ageTextField.getText().isEmpty()) {
					throw new InvalidProfileException("Missing age information.");
				}else {
					age  = Float.parseFloat(personData[1].trim());
					if (age < 0) {
						throw new InvalidProfileException("Age must be a positive number.");
					}
				}
				

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Incorrect age input: " + personData[1] + "\nMust be a number");
			}
			
			//3.check weight 
			try {
				if(NutriByte.view.weightTextField.getText().isEmpty()) {
					throw new InvalidProfileException("Missing weight information.");
				}else {
					weight = Float.parseFloat(personData[2].trim());
				}
				
				if (weight < 0) {
					throw new InvalidProfileException("Weight must be a positive number");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid weight input: " + personData[2] + "\nMust be a number");
			}
			
			//4. check height 
			try {
				if(NutriByte.view.heightTextField.getText().isEmpty()) {
					throw new InvalidProfileException("Missing height information.");
				}else {
					height = Float.parseFloat(personData[3].trim());
				}
				
				if (height < 0) {
					throw new InvalidProfileException("Height must be a positive number");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid height input: " + personData[3] + "\nMust be a number");
			}
			
			//5. check physical activities
			try {
				pa = Float.parseFloat(personData[4].trim());
				if (pa != 1.0f && pa != 1.1f && pa != 1.25f && pa != 1.48f) {
					throw new InvalidProfileException("Invalid physical activity level: " + pa + "\nMust be: 1.0, 1.1, 1.25 or 1.48");
				}

			}catch (NumberFormatException e) {
				throw new InvalidProfileException("Invalid physical activity level: " + personData[4] + "\nMust be: 1.0, 1.1, 1.25 or 1.48");
			}
			
			//6. ingredientToWatch
			StringBuilder ingredientToWatch = new StringBuilder();
			for (int i = 5; i < personData.length; i++ ) {
				ingredientToWatch.append(personData[i] + " ");
			}
			
			if(gender.equalsIgnoreCase("female")) {
				return new Female(age,weight,height, pa,ingredientToWatch.toString().trim());
			}else {
				return new Male(age,weight,height, pa,ingredientToWatch.toString().trim());
			}
		}
		}catch(InvalidProfileException e){
			return null;
		}
		}
	
	
	Product validateProductData(String data) {
		String[] productData = data.split(",");
		
		try {
		//missing data:
		if (productData.length != 3) {
			throw new InvalidProfileException("Missing data.\nThe data must be String, number, number\n - for ndb number, serving size, household size.");
		}
		else {
			//invalid product number:
			if(NutriByte.model.productsMap.get(productData[0].trim())==null) {
				throw new InvalidProfileException("No product found with this code: " + productData[0]);
			}
						
			try {
				//test whether have NumberFormatException:
				Float.parseFloat(productData[1]);
				Float.parseFloat(productData[2]);
				
				//if does not have NumberFormatException, create new product object and return it.
				Product p = new Product();
				p.setNdbNumber(productData[0].trim());
				p.setServingSize(Float.parseFloat(productData[1]));
				p.setHouseholdSize(Float.parseFloat(productData[2]));
				p.setProductName(NutriByte.model.productsMap.get(p.getNdbNumber()).getProductName());
				p.setManufacturer(NutriByte.model.productsMap.get(p.getNdbNumber()).getManufacturer());
				p.setIngredients(NutriByte.model.productsMap.get(p.getNdbNumber()).getIngredients());
				p.setServingUom(NutriByte.model.productsMap.get(p.getNdbNumber()).getServingUom());
				p.setHouseholdUom(NutriByte.model.productsMap.get(p.getNdbNumber()).getHouseholdUom());
				
				for(Map.Entry<String, ProductNutrient> e:NutriByte.model.productsMap.get(p.getNdbNumber()).productNutrients.entrySet()) {
					p.productNutrients.put(e.getKey(), e.getValue());
				}
				
				
				return p;
	
			}catch (NumberFormatException e){
				throw new InvalidProfileException("Cannot read: "+ productData[0]+","+productData[1]+","+productData[2] +"\nThe data must be String, number, number\n - for ndb number, serving size, household size.");
			}

		}
	}catch(InvalidProfileException e) {

		return null;
	}
	}

	@Override
	public boolean readFile(String filename) {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				
			}
			String[] data = sb.toString().split("\n");
			
			// validate person data:
			NutriByte.person= validatePersonData(data[0]);
			
			//if successfully create a person:
			if(NutriByte.person !=null) {
				//if have product data:
				if(data.length >1) {

						for (int i = 1; i < data.length; i++) {
							if(validateProductData(data[i]) ==null) {
								continue;
							}else {
								//validate product data and put it into dietProductsList if pass validation:
								NutriByte.person.dietProductsList.add(validateProductData(data[i]));
							}
						}
				}
				return true;
			}
			return false;
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}
