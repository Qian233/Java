//Xueqian Zhang id: xueqianz
package hw3;

import java.io.File;
import java.util.Map;

import hw3.Product.ProductNutrient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller {
	ObservableList<Product> noPersonDietProductsList = FXCollections.observableArrayList();
	
	class CloseMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//come back to welcome scene
			NutriByte.view.root.setCenter(NutriByte.view.setupWelcomeScene());
		}
	}
	
	class NewMenuItemHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			/*
			 * NewMenuItemHandler: Switches the screen from welcome screen to NutriByte.view.nutriTrackerPane.
			   Invokes initializePrompts() method of View class. Clears the recommended nutrients TableView.
			 */
			//clear all fields
			NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
			NutriByte.view.initializePrompts();
			NutriByte.view.recommendedNutrientsTableView.getItems().clear();
			NutriByte.view.servingSizeLabel.setText("0.00");
			NutriByte.view.householdSizeLabel.setText("0.00");
			NutriByte.view.dietProductsTableView.getItems().clear();
			NutriByte.view.productNutrientsTableView.getItems().clear();
			NutriByte.view.dietHouseholdUomLabel.setText("");
			NutriByte.view.dietServingUomLabel.setText("");
			NutriByte.view.searchResultSizeLabel.setText("");
			NutriByte.view.ingredientSearchTextField.setText("");
			NutriByte.view.productIngredientsTextArea.setText("");
			NutriByte.view.nutriChart.clearChart();
			NutriByte.view.ageTextField.clear();
			NutriByte.view.dietHouseholdSizeTextField.clear();
			NutriByte.view.dietServingSizeTextField.clear();
			NutriByte.view.heightTextField.clear();
			NutriByte.view.ingredientSearchTextField.clear();
			NutriByte.view.ingredientsToWatchTextArea.clear();
			NutriByte.view.nutrientSearchTextField.clear();
			NutriByte.view.productIngredientsTextArea.clear();
			NutriByte.view.productSearchTextField.clear();
			NutriByte.view.weightTextField.clear();

		}
	}
	
	class OpenMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			/*
			 * OpenMenuItemHandler: Opens the FileChooser for the user to choose a profile data file that 
			 * can be .csv or .xml. Passes the selected file¡¯s file name to the Model¡¯s readProfile() method. 
			 * Displays the profile data in the GUI. Finally, invokes NutriProfiler¡¯s createNutriProfile() method 
			 * to populate the recommendedNutrientsList.
			 */
			
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select file");
			fileChooser.setInitialDirectory(new File("profiles")); //local path
			fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("CSV Files", "*.csv"),
			new ExtensionFilter("XML Files", "*.xml"),
			new ExtensionFilter("All Files", "*.*"));
			
			// Switches the screen 
//			NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
			NutriByte.view.initializePrompts();
			NutriByte.view.recommendedNutrientsTableView.getItems().clear();
			NutriByte.view.dietProductsTableView.getItems().clear();
			NutriByte.view.productNutrientsTableView.getItems().clear();
			NutriByte.view.nutriChart.clearChart();
			NutriByte.view.servingSizeLabel.setText("0.00");
			NutriByte.view.householdSizeLabel.setText("0.00");
			NutriByte.view.dietProductsTableView.getItems().clear();
			NutriByte.view.productNutrientsTableView.getItems().clear();
			NutriByte.view.dietHouseholdUomLabel.setText("");
			NutriByte.view.dietServingUomLabel.setText("");
			NutriByte.view.searchResultSizeLabel.setText("");
			NutriByte.view.ingredientSearchTextField.setText("");
			NutriByte.view.productIngredientsTextArea.setText("");
			
			
			File file = null;
			if ((file = fileChooser.showOpenDialog(new Stage())) != null){
				String fileName = file.getAbsolutePath();
				if(NutriByte.model.readProfile(fileName)) {
					if(NutriByte.person!=null) {
						//read data from file and put them to corresponding field 
						NutriByte.view.heightTextField.setText(Float.toString(NutriByte.person.height));
						NutriByte.view.ageTextField.setText(Float. toString(NutriByte.person.age));
		                NutriByte.view.ingredientsToWatchTextArea.setText(NutriByte.person.ingredientsToWatch);
		                NutriByte.view.weightTextField.setText(Float.toString(NutriByte.person.weight));
		                
		                for (NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
							if(pa.getPhysicalActivityLevel() == NutriByte.person.physicalActivityLevel) {
								NutriByte.view.physicalActivityComboBox.setValue(pa.getName());
							}
						}
						if(NutriByte.person instanceof Male) {
							NutriByte.view.genderComboBox.setValue("Male");
						}else {
							NutriByte.view.genderComboBox.setValue("Female");
						}
						//Finally, invokes NutriProfiler¡¯s createNutriProfile() method to populate the recommendedNutrientsList.
						NutriProfiler np = new NutriProfiler();
						np.createNutriProfile(NutriByte.person);
						
						//display recommendedNutrients once open a profile, region 1
						NutriByte.view.recommendedNutrientsTableView.setItems(NutriByte.person.recommendedNutrientsList);
						//display dietproductnutrient once open a profile. region 3
						NutriByte.view.dietProductsTableView.setItems(NutriByte.person.dietProductsList);
						//display chart, region 4
						NutriByte.person.populateDietNutrientsMap();
						NutriByte.view.nutriChart.updateChart();
						
						
						//region 2: 
						
						if(NutriByte.person.dietProductsList.size() !=0) {
							NutriByte.view.productsComboBox.setItems(NutriByte.person.dietProductsList);
							NutriByte.view.searchResultSizeLabel.setText(NutriByte.person.dietProductsList.size() + " product(s) found");
							
							NutriByte.view.productsComboBox.setItems(NutriByte.person.dietProductsList);
							// default value: the first one in the list
							if(!NutriByte.person.dietProductsList.isEmpty()) {
								NutriByte.view.productsComboBox.setValue(NutriByte.person.dietProductsList.get(0));
							}
							
							//default productNutrientsTableView
							ObservableList<Product.ProductNutrient> data = FXCollections.observableArrayList();
							for(Map.Entry<String, ProductNutrient> e: NutriByte.person.dietProductsList.get(0).productNutrients.entrySet()) {
								data.add(e.getValue());
							}
							NutriByte.view.productNutrientsTableView.setItems(data);
							
							//default productIngredientsTextArea
							NutriByte.view.productIngredientsTextArea.setText(NutriByte.person.dietProductsList.get(0).getIngredients());
							
							//default serving and home size
							NutriByte.view.servingSizeLabel.setText(Float.toString(NutriByte.person.dietProductsList.get(0).getServingSize()));
							NutriByte.view.householdSizeLabel.setText(Float.toString(NutriByte.person.dietProductsList.get(0).getHouseholdSize()));
							NutriByte.view.dietServingUomLabel.setText(NutriByte.view.productsComboBox.getValue().getServingUom());
							NutriByte.view.dietHouseholdUomLabel.setText(NutriByte.view.productsComboBox.getValue().getHouseholdUom());

						}
											}
					
					
				}
				NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
				
				

				
				}
			
		}
}

	class RecommendNutrientsButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

//			Takes all the data from the GUI controls (genderComboBox,
//			ageTextField, etc.) to create a profile, and then populate the recommendedNutrientsTableView.
			
			//store region 1 data
			String content =NutriByte.view.genderComboBox.getValue()+","+ 
					NutriByte.view.ageTextField.getText()+","+
					NutriByte.view.weightTextField.getText()+","+
					NutriByte.view.heightTextField.getText()+",";
			//SEDENTARY("Sedentary", 1), LOW_ACTIVE("Low active", 1.1f), ACTIVE("Active", 1.25f), 
			//VERY_ACTIVE("Very active", 1.48f);
			//find out corresponding number for different pa level
			switch(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedItem()){
			case "Sedentary":
				content = content + "1,";
				break;
			case "Low active":
				content = content + "1.1f,";
				break;
			case "Active":
				content = content + "1.25f,";
				break;
			case "Very active":
				content = content + "1.48f,";
				break;
			}
			
			if(!NutriByte.view.ingredientsToWatchTextArea.getText().equals("")) {
				String[] ingredientList = NutriByte.view.ingredientsToWatchTextArea.getText().split("[^a-zA-Z0-9']+");
				for(int i = 0; i < ingredientList.length-1;i++) {
					content = content + ingredientList[i] + ",";
				}
				content = content + ingredientList[ingredientList.length-1] + "\n";
			}else {
				content = content + "\n";
			}
			
			CSVFiler csv = new CSVFiler();
			//validate person data:
			NutriByte.person= csv.validatePersonDataSaveFile(content);
			
			
			if(NutriByte.person!=null) {
				NutriByte.person.initializeNutriConstantsTable();
				//display recommendedNutrients in region 1
				if(Float.parseFloat(NutriByte.view.heightTextField.getText())>0){
					NutriProfiler.createNutriProfile(NutriByte.person);
					NutriByte.view.recommendedNutrientsTableView.setItems(NutriByte.person.recommendedNutrientsList);
				}
			}
					
//			try {
//				if(!(NutriByte.view.genderComboBox.getSelectionModel().getSelectedIndex()>-1)) {
//					throw new InvalidProfileException("Missing gender information." );
//				}
//			}catch (InvalidProfileException e){
//				
//			}
//			
//			//if not select gender, no reaction:
//			if(!(NutriByte.view.genderComboBox.getSelectionModel().getSelectedIndex()>-1)) {
//				
//			}else {
//				//if select gender and gender = female:
//				if(NutriByte.view.genderComboBox.getValue().equals("Female")) {
//					float physicalActivityLevel = 0;
//					//1.check age:
//					
//					if(NutriByte.view.ageTextField.getText().isEmpty()||
//							Float.parseFloat(NutriByte.view.ageTextField.getText())<0) {
//						try {
//							if(NutriByte.view.ageTextField.getText().isEmpty()) {
//								throw new InvalidProfileException("Missing age information." );
//								
//							}else if(Float.parseFloat(NutriByte.view.ageTextField.getText())<0) {
//								throw new InvalidProfileException("Age must be a positive number." );
//							}
//							
//						}catch (NumberFormatException e) {
//							throw new InvalidProfileException("Incorrect age input. Must be a number.");
//						}catch(InvalidProfileException e) {
//							
//						}
//					}else {
//						//2. check weight
//						if(NutriByte.view.weightTextField.getText().isEmpty()||
//								Float.parseFloat(NutriByte.view.weightTextField.getText())<0) {
//							try {
//								if(NutriByte.view.weightTextField.getText().isEmpty()) {
//									throw new InvalidProfileException("Missing weight information." );
//								}else if(Float.parseFloat(NutriByte.view.weightTextField.getText())<0) {
//									throw new InvalidProfileException("Weight must be a positive number." );
//								}
//								
//							}catch (NumberFormatException e) {
//								throw new InvalidProfileException("Incorrect weight input. Must be a number.");
//							}catch(InvalidProfileException e) {
//								
//							}
//						}else {
//							//3. check height
//							if(NutriByte.view.heightTextField.getText().isEmpty()||
//									Float.parseFloat(NutriByte.view.heightTextField.getText())<0) {
//								try {
//									if(NutriByte.view.heightTextField.getText().isEmpty()) {
//										throw new InvalidProfileException("Missing height information." );
//									}else if(Float.parseFloat(NutriByte.view.heightTextField.getText())<0) {
//										throw new InvalidProfileException("Height must be a positive number." );
//									}
//									
//								}catch (NumberFormatException e) {
//									throw new InvalidProfileException("Incorrect height input. Must be a number.");
//								}catch(InvalidProfileException e) {
//									
//								}
//							}else {
//								//4. check physicalActivity
//								
//								if(!(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedIndex()>-1)) {
//
//								}else {
//									for(NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
//										if(NutriByte.view.physicalActivityComboBox.getValue().equals(pa.getName())) {
//											physicalActivityLevel = pa.getPhysicalActivityLevel();
//										}
//									}
//								}
//								NutriByte.person = new Female(Float.parseFloat(NutriByte.view.ageTextField.getText()), 
//										Float.parseFloat(NutriByte.view.weightTextField.getText()), 
//										Float.parseFloat(NutriByte.view.heightTextField.getText()), 
//										physicalActivityLevel, NutriByte.view.ingredientsToWatchTextArea.getText());
//								NutriByte.person.initializeNutriConstantsTable();
//							}							
//						}						
//					}	
//				}
//				//if select gender and gender = male:
//				if(NutriByte.view.genderComboBox.getValue().equals("Male")) {
//					float physicalActivityLevel = 0;
//					//1. check age:
//					if(NutriByte.view.ageTextField.getText().isEmpty()||
//							Float.parseFloat(NutriByte.view.ageTextField.getText())<0) {
//						try {
//							if(NutriByte.view.ageTextField.getText().isEmpty()) {
//								throw new InvalidProfileException("Missing age information." );
//								
//							}else if(Float.parseFloat(NutriByte.view.ageTextField.getText())<0) {
//								throw new InvalidProfileException("Age must be a positive number." );
//							}
//							
//						}catch (NumberFormatException e) {
//							throw new InvalidProfileException("Incorrect age input. Must be a number.");
//						}catch(InvalidProfileException e) {
//							
//						}
//					}else {
//						//2. check weight
//						if(NutriByte.view.weightTextField.getText().isEmpty()||
//								Float.parseFloat(NutriByte.view.weightTextField.getText())<0) {
//							try {
//								if(NutriByte.view.weightTextField.getText().isEmpty()) {
//									throw new InvalidProfileException("Missing weight information." );
//								}else if(Float.parseFloat(NutriByte.view.weightTextField.getText())<0) {
//									throw new InvalidProfileException("Weight must be a positive number." );
//								}
//								
//							}catch (NumberFormatException e) {
//								throw new InvalidProfileException("Incorrect weight input. Must be a number.");
//							}catch(InvalidProfileException e) {
//								
//							}
//						}else {
//							//3. check height
//							if(NutriByte.view.heightTextField.getText().isEmpty()||
//									Float.parseFloat(NutriByte.view.heightTextField.getText())<0) {
//								try {
//									if(NutriByte.view.heightTextField.getText().isEmpty()) {
//										throw new InvalidProfileException("Missing height information." );
//									}else if(Float.parseFloat(NutriByte.view.heightTextField.getText())<0) {
//										throw new InvalidProfileException("Height must be a positive number." );
//									}
//									
//								}catch (NumberFormatException e) {
//									throw new InvalidProfileException("Incorrect height input. Must be a number.");
//								}catch(InvalidProfileException e) {
//									
//								}
//							}else {
//								//4. check physicalActivity
//								
//								if(!(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedIndex()>-1)) {
//
//								}else {
//									for(NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
//										if(NutriByte.view.physicalActivityComboBox.getValue().equals(pa.getName())) {
//											physicalActivityLevel = pa.getPhysicalActivityLevel();
//										}
//									}
//								}
//								NutriByte.person = new Male(Float.parseFloat(NutriByte.view.ageTextField.getText()), 
//										Float.parseFloat(NutriByte.view.weightTextField.getText()), 
//										Float.parseFloat(NutriByte.view.heightTextField.getText()), 
//										physicalActivityLevel, NutriByte.view.ingredientsToWatchTextArea.getText());
//								NutriByte.person.initializeNutriConstantsTable();
//							}							
//						}						
//					}	
//				}
//				
//				if(NutriByte.person!=null) {
//					//display recommendedNutrients in region 1
//					if(Float.parseFloat(NutriByte.view.heightTextField.getText())>0){
//						NutriProfiler.createNutriProfile(NutriByte.person);
//						NutriByte.view.recommendedNutrientsTableView.setItems(NutriByte.person.recommendedNutrientsList);
//					}
//				}
//			}
		}			
	}	

	class AboutMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("NutriByte");
			alert.setContentText("Version 2.0 \nRelease 1.0\nCopyleft Java Nerds\nThis software is designed purely for educational purposes.\nNo commercial use intended");
			Image image = new Image(getClass().getClassLoader().getResource(NutriByte.NUTRIBYTE_IMAGE_FILE).toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.showAndWait();
		}
	}
	
	//new 
	class SaveMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//store region 1 data
			String content =NutriByte.view.genderComboBox.getValue()+","+ 
					NutriByte.view.ageTextField.getText()+","+
					NutriByte.view.weightTextField.getText()+","+
					NutriByte.view.heightTextField.getText()+",";
			//SEDENTARY("Sedentary", 1), LOW_ACTIVE("Low active", 1.1f), ACTIVE("Active", 1.25f), 
			//VERY_ACTIVE("Very active", 1.48f);
			//find out corresponding number for different pa level
			switch(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedItem()){
			case "Sedentary":
				content = content + "1,";
				break;
			case "Low active":
				content = content + "1.1f,";
				break;
			case "Active":
				content = content + "1.25f,";
				break;
			case "Very active":
				content = content + "1.48f,";
				break;
			}
			
			if(!NutriByte.view.ingredientsToWatchTextArea.getText().equals("")) {
				String[] ingredientList = NutriByte.view.ingredientsToWatchTextArea.getText().split("[^a-zA-Z0-9']+");
				for(int i = 0; i < ingredientList.length-1;i++) {
					content = content + ingredientList[i] + ",";
				}
				content = content + ingredientList[ingredientList.length-1] + "\n";
			}else {
				content = content + "\n";
			}
			
			CSVFiler csv = new CSVFiler();
			//validate person data:
			Person indicator= csv.validatePersonDataSaveFile(content);
			
			//if person data passes all the validation, save product data and person data:
			if(indicator != null) {
			//store region 3 data
			if(NutriByte.person.dietProductsList.size() != 0) {
				for(Product p:NutriByte.person.dietProductsList) {
					content = content + p.getNdbNumber()+","+p.getServingSize()+","+p.getHouseholdSize()+ "\n";
				}
			}
			
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save file");
				fileChooser.setInitialDirectory(new File("profiles")); //local path
				fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("CSV Files", "*.csv"),
				new ExtensionFilter("XML Files", "*.xml"),
				new ExtensionFilter("All Files", "*.*"));
				

				File file = fileChooser.showSaveDialog(new Stage());
				 
	            if (file != null) {
	            	NutriByte.model.saveTextToFile(content, file);
	            }
			}
			
			
			
			
			
		}
	}
	
	class SearchButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			NutriByte.model.searchResultsList.clear();
			int productFoundIndicator = 0;
			int nutrientFoundIndicator = 0;
			int ingredientFoundIndicator = 0;
			String code = null;
			ObservableMap<String,Product> foundProductMap = FXCollections.observableHashMap();
			ObservableMap<String,Product> foundNutrientMap = FXCollections.observableHashMap();
			ObservableMap<String,Product> foundIngredientMap = FXCollections.observableHashMap();
			
			//product
			if(!NutriByte.view.productSearchTextField.getText().isEmpty()) {
				for(Map.Entry<String, Product> e1:NutriByte.model.productsMap.entrySet()) {
					if(e1.getValue().getProductName().toLowerCase().contains(NutriByte.view.productSearchTextField.getText().toLowerCase())) {
						foundProductMap.put(e1.getKey(), e1.getValue());
						// if productSearchTextField has valid value:
						productFoundIndicator = 1;
					}
				}	
			}else {
				foundProductMap = NutriByte.model.productsMap;
			}
			
			// nutrient
			if(!NutriByte.view.nutrientSearchTextField.getText().isEmpty() ) {
				for(Map.Entry<String, Nutrient> e:NutriByte.model.nutrientsMap.entrySet()) {
					if(e.getValue().getNutrientName().toLowerCase().contains(NutriByte.view.nutrientSearchTextField.getText().toLowerCase())) {
						code = e.getValue().getNutrientCode();
					}
				}
				for(Map.Entry<String, Product> e2:foundProductMap.entrySet()) {
					if(e2.getValue().getProductNutrients().keySet().contains(code)){
						foundNutrientMap.put(e2.getKey(), e2.getValue());
						// if nutrientSearchTextField has valid value:
						nutrientFoundIndicator =1;
					}
				}
			}else {
				foundNutrientMap = foundProductMap;
			}
			
			//ingredient
			if(!NutriByte.view.ingredientSearchTextField.getText().isEmpty()) {
				for(Map.Entry<String, Product> e3:foundNutrientMap.entrySet()) {
					if(e3.getValue().getIngredients().toLowerCase().contains(NutriByte.view.ingredientSearchTextField.getText().toLowerCase())){
						foundIngredientMap.put(e3.getKey(), e3.getValue());
						// if ingredientSearchTextField has valid value:
						ingredientFoundIndicator =1;
					}
				}
			}else {
				foundIngredientMap = foundNutrientMap;
			}
			//at least one of the fields(product, nutrient, ingredient) has valid value:
			if(productFoundIndicator + nutrientFoundIndicator + ingredientFoundIndicator >= 1) {
				for(Product p: foundIngredientMap.values()) {
					NutriByte.model.searchResultsList.add(p);
				}
				//NutriByte.model.searchResultsList = (ObservableList<Product>) foundIngredientMap.values();
			}else {
				for(Product p: NutriByte.model.productsMap.values()) {
					NutriByte.model.searchResultsList.add(p);
				}
			}
			

			
			NutriByte.view.searchResultSizeLabel.setText(NutriByte.model.searchResultsList.size() + " product(s) found");
			
			NutriByte.view.productsComboBox.setItems(NutriByte.model.searchResultsList);
			// default value: the first one in the list
			if(!NutriByte.model.searchResultsList.isEmpty()) {
				NutriByte.view.productsComboBox.setValue(NutriByte.model.searchResultsList.get(0));
			}
			
			//default productNutrientsTableView
			ObservableList<Product.ProductNutrient> data = FXCollections.observableArrayList();
			for(Map.Entry<String, ProductNutrient> e: NutriByte.model.searchResultsList.get(0).productNutrients.entrySet()) {
				data.add(e.getValue());
			}
			NutriByte.view.productNutrientsTableView.setItems(data);
			
			//default productIngredientsTextArea
			NutriByte.view.productIngredientsTextArea.setText(NutriByte.model.searchResultsList.get(0).getIngredients());
			
			//default serving and home size
			NutriByte.view.servingSizeLabel.setText(Float.toString(NutriByte.model.searchResultsList.get(0).getServingSize()));
			NutriByte.view.householdSizeLabel.setText(Float.toString(NutriByte.model.searchResultsList.get(0).getHouseholdSize()));
			NutriByte.view.dietServingUomLabel.setText(NutriByte.view.productsComboBox.getValue().getServingUom());
			NutriByte.view.dietHouseholdUomLabel.setText(NutriByte.view.productsComboBox.getValue().getHouseholdUom());
			
		}
	}
	class ClearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//clear all fields:
			NutriByte.view.productSearchTextField.clear();
			NutriByte.view.nutrientSearchTextField.clear();
			NutriByte.view.ingredientSearchTextField.clear();
			NutriByte.view.productsComboBox.getItems().clear();
			NutriByte.view.productNutrientsTableView.getItems().clear();
			NutriByte.view.productIngredientsTextArea.clear();
			NutriByte.view.searchResultSizeLabel.setText("");
			NutriByte.view.servingSizeLabel.setText("0.00");
			NutriByte.view.householdSizeLabel.setText("0.00");
		}
	} 
	class ProductsComboBoxListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//attach a listener to productsComboBox to display nutrient quantity and uom
			ObservableList<Product.ProductNutrient> data = FXCollections.observableArrayList();
			
			NutriByte.view.productsComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					for(Map.Entry<String, ProductNutrient> e: newValue.productNutrients.entrySet()) {
						data.add(e.getValue());
					}
					//set new value in region 2
					NutriByte.view.productNutrientsTableView.setItems(data);
					NutriByte.view.productIngredientsTextArea.setText(newValue.getIngredients());
					
					//set new value in region 3
					NutriByte.view.servingSizeLabel.setText(Float.toString(newValue.getServingSize()));
					NutriByte.view.householdSizeLabel.setText(Float.toString(newValue.getHouseholdSize()));
					NutriByte.view.dietServingUomLabel.setText(newValue.getServingUom());
					NutriByte.view.dietHouseholdUomLabel.setText(newValue.getHouseholdUom());
					
				}
				
				
				
			});
		}
	}
	class AddDietButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			if(NutriByte.person!=null) {
				//1. User doesn¡¯t enter anything in the two textfields
				if(NutriByte.view.dietServingSizeTextField.getText().equals("")
						&& NutriByte.view.dietHouseholdSizeTextField.getText().equals("")
						&&(NutriByte.view.productsComboBox.getSelectionModel().getSelectedIndex()>-1)) {
					NutriByte.person.dietProductsList.add(NutriByte.view.productsComboBox.getValue());
				
					//User enters only in dietServingSizeTextField && User enters both
				}else if((!NutriByte.view.dietServingSizeTextField.getText().equals("")
						&&NutriByte.view.dietHouseholdSizeTextField.getText().equals(""))||
						(!NutriByte.view.dietServingSizeTextField.getText().equals("")
								&& !NutriByte.view.dietHouseholdSizeTextField.getText().equals(""))) {
					Product p = 
							new Product(NutriByte.view.productsComboBox.getValue().getNdbNumber(),
									NutriByte.view.productsComboBox.getValue().getProductName(),
									NutriByte.view.productsComboBox.getValue().getManufacturer(),
									NutriByte.view.productsComboBox.getValue().getIngredients());
					p.setHouseholdSize((NutriByte.view.productsComboBox.getValue().getHouseholdSize()/NutriByte.view.productsComboBox.getValue().getServingSize())*Float.parseFloat(NutriByte.view.dietServingSizeTextField.getText().trim()));
					p.setHouseholdUom(NutriByte.view.productsComboBox.getValue().getHouseholdUom());
					
					//get user input
					p.setServingSize(Float.parseFloat(NutriByte.view.dietServingSizeTextField.getText().trim()));
					p.setServingUom(NutriByte.view.productsComboBox.getValue().getServingUom());
					
					for(Map.Entry<String, ProductNutrient> e:NutriByte.view.productsComboBox.getValue().productNutrients.entrySet()) {
						p.productNutrients.put(e.getKey(), e.getValue());
					}
					
					NutriByte.person.dietProductsList.add(p);
					
					//User enters only in dietHouseholdSizeTextField
				}else if(NutriByte.view.dietServingSizeTextField.getText().equals("")
						&&!NutriByte.view.dietHouseholdSizeTextField.getText().equals("")
						&&(NutriByte.view.productsComboBox.getSelectionModel().getSelectedIndex()>-1)) {
					Product p = 
							new Product(NutriByte.view.productsComboBox.getValue().getNdbNumber(),
									NutriByte.view.productsComboBox.getValue().getProductName(),
									NutriByte.view.productsComboBox.getValue().getManufacturer(),
									NutriByte.view.productsComboBox.getValue().getIngredients());
					
					p.setServingSize((NutriByte.view.productsComboBox.getValue().getServingSize()/NutriByte.view.productsComboBox.getValue().getHouseholdSize())*Float.parseFloat(NutriByte.view.dietHouseholdSizeTextField.getText().trim()));
					p.setServingUom(NutriByte.view.productsComboBox.getValue().getServingUom());
					
					//get user input
					p.setHouseholdSize(Float.parseFloat(NutriByte.view.dietHouseholdSizeTextField.getText().trim()));
					p.setHouseholdUom(NutriByte.view.productsComboBox.getValue().getHouseholdUom());
					
					for(Map.Entry<String, ProductNutrient> e:NutriByte.view.productsComboBox.getValue().productNutrients.entrySet()) {
						p.productNutrients.put(e.getKey(), e.getValue());
					}
					
					NutriByte.person.dietProductsList.add(p);
			}
				
				NutriByte.view.dietProductsTableView.setItems(NutriByte.person.dietProductsList);
				NutriByte.view.dietProductsTableView.getSelectionModel().selectLast();
				
				//region 4
				//dietNutrientsMap: used to store the total of all nutrients based on products in dietProductsList. Used by 
				//NutriChart class to display four nutrient¡¯s total actual values (region 4)
				NutriByte.person.populateDietNutrientsMap();
				//update chart:
				NutriByte.view.nutriChart.updateChart();

		}else {
			
			if(NutriByte.view.dietServingSizeTextField.getText().equals("")
					&& NutriByte.view.dietHouseholdSizeTextField.getText().equals("")
					&&(NutriByte.view.productsComboBox.getSelectionModel().getSelectedIndex()>-1)) {
				noPersonDietProductsList.add(NutriByte.view.productsComboBox.getValue());
			
				//User enters only in dietServingSizeTextField && User enters both
			}else if((!NutriByte.view.dietServingSizeTextField.getText().equals("")
					&&NutriByte.view.dietHouseholdSizeTextField.getText().equals(""))||
					(!NutriByte.view.dietServingSizeTextField.getText().equals("")
							&& !NutriByte.view.dietHouseholdSizeTextField.getText().equals(""))) {
				Product p = 
						new Product(NutriByte.view.productsComboBox.getValue().getNdbNumber(),
								NutriByte.view.productsComboBox.getValue().getProductName(),
								NutriByte.view.productsComboBox.getValue().getManufacturer(),
								NutriByte.view.productsComboBox.getValue().getIngredients());
				p.setHouseholdSize((NutriByte.view.productsComboBox.getValue().getHouseholdSize()/NutriByte.view.productsComboBox.getValue().getServingSize())*Float.parseFloat(NutriByte.view.dietServingSizeTextField.getText().trim()));
				p.setHouseholdUom(NutriByte.view.productsComboBox.getValue().getHouseholdUom());
				
				//get user input
				p.setServingSize(Float.parseFloat(NutriByte.view.dietServingSizeTextField.getText().trim()));
				p.setServingUom(NutriByte.view.productsComboBox.getValue().getServingUom());
				
				for(Map.Entry<String, ProductNutrient> e:NutriByte.view.productsComboBox.getValue().productNutrients.entrySet()) {
					p.productNutrients.put(e.getKey(), e.getValue());
				}
				
				noPersonDietProductsList.add(p);
				
				//User enters only in dietHouseholdSizeTextField
			}else if(NutriByte.view.dietServingSizeTextField.getText().equals("")
					&&!NutriByte.view.dietHouseholdSizeTextField.getText().equals("")
					&&(NutriByte.view.productsComboBox.getSelectionModel().getSelectedIndex()>-1)) {
				Product p = 
						new Product(NutriByte.view.productsComboBox.getValue().getNdbNumber(),
								NutriByte.view.productsComboBox.getValue().getProductName(),
								NutriByte.view.productsComboBox.getValue().getManufacturer(),
								NutriByte.view.productsComboBox.getValue().getIngredients());
				
				p.setServingSize((NutriByte.view.productsComboBox.getValue().getServingSize()/NutriByte.view.productsComboBox.getValue().getHouseholdSize())*Float.parseFloat(NutriByte.view.dietHouseholdSizeTextField.getText().trim()));
				p.setServingUom(NutriByte.view.productsComboBox.getValue().getServingUom());
				
				//get user input
				p.setHouseholdSize(Float.parseFloat(NutriByte.view.dietHouseholdSizeTextField.getText().trim()));
				p.setHouseholdUom(NutriByte.view.productsComboBox.getValue().getHouseholdUom());
				
				for(Map.Entry<String, ProductNutrient> e:NutriByte.view.productsComboBox.getValue().productNutrients.entrySet()) {
					p.productNutrients.put(e.getKey(), e.getValue());
				}
				
				noPersonDietProductsList.add(p);
		}
			
			NutriByte.view.dietProductsTableView.setItems(noPersonDietProductsList);
			NutriByte.view.dietProductsTableView.getSelectionModel().selectLast();
		}
	}
	}
		
	class RemoveDietButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			//region 3
			//checks the current selected index 
			//if it is >= 0, it removes the item at that index from dietProductsList
			if(!NutriByte.view.dietProductsTableView.getSelectionModel().getSelectedItems().isEmpty()) {
				int index = NutriByte.view.dietProductsTableView.getSelectionModel().getFocusedIndex();
				if (NutriByte.person.dietProductsList.size() > index) NutriByte.person.dietProductsList.remove(index);
				if (NutriByte.person.dietProductsList.size() == 0) {
					NutriByte.view.productsComboBox.getSelectionModel().clearSelection();
				}
				
				//region 4
				NutriByte.person.populateDietNutrientsMap();
				NutriByte.view.nutriChart.updateChart();
			}
			
		}
	}

}
	



