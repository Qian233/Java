//Xueqian Zhang id: xueqianz
package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller {
	
	class NewMenuItemHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			/*
			 * NewMenuItemHandler: Switches the screen from welcome screen to NutriByte.view.nutriTrackerPane.
			   Invokes initializePrompts() method of View class. Clears the recommended nutrients TableView.
			 */
			//write your code here
			//initializePrompts()
			//NutriByte.view.nutriTrackerPane

			NutriByte.view.root.setCenter(NutriByte.view.nutriTrackerPane);
			NutriByte.view.initializePrompts();
			NutriByte.view.recommendedNutrientsTableView.getItems().clear();

		}
	}
	
	class OpenMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//write your code here
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
			
			
			File file = null;
			if ((file = fileChooser.showOpenDialog(new Stage())) != null){
				String fileName = file.getAbsolutePath();
				if(NutriByte.model.readProfile(fileName)) {
					NutriByte.view.heightTextField.setText(Float.toString(NutriByte.person.height));
					NutriByte.view.ageTextField.setText(Float. toString(NutriByte.person.age));
	                NutriByte.view.ingredientsToWatchTextArea.setText(NutriByte.person.ingredientsToWatch);
	                NutriByte.view.weightTextField.setText(Float.toString(NutriByte.person.weight));
				}
				

				
				}
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
		}
}

	class RecommendNutrientsButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//write your code here
			//
//			Takes all the data from the GUI controls (genderComboBox,
//			ageTextField, etc.) to create a profile, and then populate the recommendedNutrientsTableView.
			
			//need fixed
			if(!(NutriByte.view.genderComboBox.getSelectionModel().getSelectedIndex()>-1)) {
				
			}else {
				if(NutriByte.view.genderComboBox.getValue().equals("Female")) {
					float physicalActivityLevel = 0;
					//NutriByte.view.physicalActivityComboBox.getValue().isEmpty()||
					//NutriByte.view.physicalActivityComboBox.getValue().equals("Physical activity")
					if(!(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedIndex()>-1)) {
						physicalActivityLevel = 1;
					}else {
						for(NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
							if(NutriByte.view.physicalActivityComboBox.getValue().equals(pa.getName())) {
								physicalActivityLevel = pa.getPhysicalActivityLevel();
							}
						}
					}
					
					float age = 0;
					if(NutriByte.view.ageTextField.getText().isEmpty()) {
						age = 0;
					}else {
						age =Float.parseFloat(NutriByte.view.ageTextField.getText());
					}
					
					float weight = 0;
					if(NutriByte.view.weightTextField.getText().isEmpty()) {
						weight = 0;
					}else {
						weight =Float.parseFloat(NutriByte.view.weightTextField.getText());
					}
					
					float height = 0;
					if(NutriByte.view.heightTextField.getText().isEmpty()) {
						height = 0;
					}else {
						height =Float.parseFloat(NutriByte.view.heightTextField.getText());
					}
					
					NutriByte.person = new Female(age, weight, height, 
							physicalActivityLevel, NutriByte.view.ingredientsToWatchTextArea.getText());
					NutriByte.person.initializeNutriConstantsTable();
					
					
				}
				if(NutriByte.view.genderComboBox.getValue().equals("Male")) {
					float physicalActivityLevel = 0;
					//NutriByte.view.physicalActivityComboBox.getValue().isEmpty()||
					//NutriByte.view.physicalActivityComboBox.getValue().equals("Physical activity")
					if(!(NutriByte.view.physicalActivityComboBox.getSelectionModel().getSelectedIndex()>-1)) {
						physicalActivityLevel = 1;
					}else {
						for(NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
							if(NutriByte.view.physicalActivityComboBox.getValue().equals(pa.getName())) {
								physicalActivityLevel = pa.getPhysicalActivityLevel();
							}
						}
					}
					
					float age = 0;
					if(NutriByte.view.ageTextField.getText().isEmpty()) {
						age = 0;
					}else {
						age =Float.parseFloat(NutriByte.view.ageTextField.getText());
					}
					
					float weight = 0;
					if(NutriByte.view.weightTextField.getText().isEmpty()) {
						weight = 0;
					}else {
						weight =Float.parseFloat(NutriByte.view.weightTextField.getText());
					}
					
					float height = 0;
					if(NutriByte.view.heightTextField.getText().isEmpty()) {
						height = 0;
					}else {
						height =Float.parseFloat(NutriByte.view.heightTextField.getText());
					}
					NutriByte.person = new Male(age, weight, height, 
							physicalActivityLevel, NutriByte.view.ingredientsToWatchTextArea.getText());
					NutriByte.person.initializeNutriConstantsTable();
					
				}
				NutriProfiler.createNutriProfile(NutriByte.person);
				
				//NutriByte.person.calculateEnergyRequirement();
				
				NutriByte.view.recommendedNutrientsTableView.setItems(NutriProfiler.recommendedNutrientsList);
			}
			
			//
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

}



