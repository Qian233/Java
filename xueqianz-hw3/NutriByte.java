//Xueqian Zhang id: xueqianz
package hw3;

import hw3.Product.ProductNutrient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NutriByte extends Application{
	static Model model = new Model();  	//made static to make accessible in the controller
	static View view = new View();		//made static to make accessible in the controller
	static Person person;				//made static to make accessible in the controller
	
	
	Controller controller = new Controller();	//all event handlers 

	/**Uncomment the following three lines if you want to try out the full-size data files */
//	static final String PRODUCT_FILE = "data/Products.csv";
//	static final String NUTRIENT_FILE = "data/Nutrients.csv";
//	static final String SERVING_SIZE_FILE = "data/ServingSize.csv";
	
	/**The following constants refer to the data files to be used for this application */
	static final String PRODUCT_FILE = "data/Nutri2Products.csv"; 
	static final String NUTRIENT_FILE = "data/Nutri2Nutrients.csv";
	static final String SERVING_SIZE_FILE = "data/Nutri2ServingSize.csv";
	
	static final String NUTRIBYTE_IMAGE_FILE = "NutriByteLogo.png"; //Refers to the file holding NutriByte logo image 

	static final String NUTRIBYTE_PROFILE_PATH = "profiles";  //folder that has profile data files

	static final int NUTRIBYTE_SCREEN_WIDTH = 1015;
	static final int NUTRIBYTE_SCREEN_HEIGHT = 675;
	
	//binding recommendednutrient:
	ObjectBinding<Person> personBinding = new ObjectBinding<>() {
		{
			super.bind(view.genderComboBox.valueProperty(),view.ageTextField.textProperty(),
					view.weightTextField.textProperty(),view.heightTextField.textProperty(),
					view.physicalActivityComboBox.valueProperty());
		}

		@Override
		protected Person computeValue() {
			// TODO Auto-generated method stub
			TextField textField = view.ageTextField;
			float age =0, weight = 0, height = 0, physicalActivityLevel = 0;
			try {
				//test whether input is valid 
				textField.setStyle("-fx-text-inner-color: black;");
				age = Float.parseFloat(textField.getText().trim());
				
				textField = view.weightTextField;
				textField.setStyle("-fx-text-inner-color: black;");
				weight = Float.parseFloat(textField.getText().trim());
				
				textField = view.heightTextField;
				textField.setStyle("-fx-text-inner-color: black;");
				height = Float.parseFloat(textField.getText().trim());
				
				if(!(view.physicalActivityComboBox.getSelectionModel().getSelectedIndex()>-1)) {
					physicalActivityLevel = 1;
				}else {
					for(NutriProfiler.PhysicalActivityEnum pa: NutriProfiler.PhysicalActivityEnum.values()) {
						if(NutriByte.view.physicalActivityComboBox.getValue().equals(pa.getName())) {
							physicalActivityLevel = pa.getPhysicalActivityLevel();
						}
					}
				}
				//if select gender:
				if(view.genderComboBox.getSelectionModel().getSelectedIndex() >-1) {
					//if gender = female, create a female object:
					if(view.genderComboBox.getValue().equals("Female")) {
						if(view.ageTextField != null && view.weightTextField != null && view.heightTextField != null) {
							person = new Female(age, weight, height, 
									physicalActivityLevel, view.ingredientsToWatchTextArea.getText());
							person.initializeNutriConstantsTable();
							return person;
						}else {
							return null;
						}
						//if gender = male, create a male object:
					}else if(view.genderComboBox.getValue().equals("Male")){
						if(view.ageTextField != null && view.weightTextField != null && view.heightTextField != null) {
							person = new Male(age,weight,height,physicalActivityLevel,view.ingredientsToWatchTextArea.getText());
							person.initializeNutriConstantsTable();
							return person;
						}else {
							return null;
						}
						
					}else {
						return null; 
					}
				}else {
					return null;
				}
				
			}catch (NumberFormatException e) {
				//if input is not valid, turn red: 
				textField.setStyle("-fx-text-inner-color: red;");
				return null;
			}
			
			
		}
	};

	@Override
	public void start(Stage stage) throws Exception {
		model.readProducts(PRODUCT_FILE);
		model.readNutrients(NUTRIENT_FILE);
		model.readServingSizes(SERVING_SIZE_FILE );
		//System.out.println(model.nutrientsMap.get("204").getNutrientName());
		view.setupMenus();
		view.setupNutriTrackerGrid();
		view.root.setCenter(view.setupWelcomeScene());
		Background b = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
		view.root.setBackground(b);
		Scene scene = new Scene (view.root, NUTRIBYTE_SCREEN_WIDTH, NUTRIBYTE_SCREEN_HEIGHT);
		view.root.requestFocus();  //this keeps focus on entire window and allows the textfield-prompt to be visible
		setupBindings();
		stage.setTitle("NutriByte 2.0");
		stage.setScene(scene);
		stage.show();
		
		//add a listener to personBinding:
		personBinding.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				person = newValue;
					if (!(person.age < 0) && 
							!(person.weight < 0) && 
							!(person.height < 0)) { 
						NutriProfiler.createNutriProfile(person);
						view.recommendedNutrientsTableView.setItems(person.recommendedNutrientsList);

					} else view.recommendedNutrientsTableView.getItems().clear();
			} else  view.recommendedNutrientsTableView.getItems().clear();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	void setupBindings() {
		view.newNutriProfileMenuItem.setOnAction(controller.new NewMenuItemHandler());
		view.openNutriProfileMenuItem.setOnAction(controller.new OpenMenuItemHandler());
		view.exitNutriProfileMenuItem.setOnAction(event -> Platform.exit());
		view.aboutMenuItem.setOnAction(controller.new AboutMenuItemHandler());
		view.closeNutriProfileMenuItem.setOnAction(controller.new CloseMenuItemHandler());
		
		view.recommendedNutrientNameColumn.setCellValueFactory(recommendedNutrientNameCallback);
		view.recommendedNutrientQuantityColumn.setCellValueFactory(recommendedNutrientQuantityCallback);
		view.recommendedNutrientUomColumn.setCellValueFactory(recommendedNutrientUomCallback);

		view.createProfileButton.setOnAction(controller.new RecommendNutrientsButtonHandler());
		
		//new:
		view.searchButton.setOnAction(controller.new SearchButtonHandler());
		view.productsComboBox.setOnAction(controller.new ProductsComboBoxListener());
		view.productNutrientNameColumn.setCellValueFactory(productNutrientNameCallback);
		view.productNutrientQuantityColumn.setCellValueFactory(productNutrientQuantityCallback);
		view.productNutrientUomColumn.setCellValueFactory(productNutrientUomCallback);
		view.clearButton.setOnAction(controller.new ClearButtonHandler());
		view.addDietButton.setOnAction(controller.new AddDietButtonHandler());
		view.removeDietButton.setOnAction(controller.new RemoveDietButtonHandler());
		view.saveNutriProfileMenuItem.setOnAction(controller.new SaveMenuItemHandler());
		
		
	}
	//added
	//productNutrientName's Callback
	Callback<CellDataFeatures<Product.ProductNutrient, String>,ObservableValue<String>>  productNutrientNameCallback 
	= new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {

		@Override
		public ObservableValue<String> call(CellDataFeatures<ProductNutrient, String> arg0) {
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			//get name
			return nutrient.nutrientNameProperty();
		}
	};
	//productNutrientQuantity's Callback
	Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>> productNutrientQuantityCallback 
	= new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<ProductNutrient, String> arg0) {
			//get quantity
			return (new SimpleStringProperty(String.format("%.2f", arg0.getValue().getNutrientQuantity())));
		}
	};
	//productNutrientUom's Callback
	Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>> productNutrientUomCallback 
	= new Callback<CellDataFeatures<Product.ProductNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<ProductNutrient, String> arg0) {
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			//get uom
			return nutrient.nutrientUomProperty();
		}
	};
	//
	
	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientNameCallback 
	= new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			return nutrient.nutrientNameProperty();
		}
	};
	
	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientQuantityCallback 
	= new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			//write your code here
			//RecommendedNutrient recommendedNutrient = NutriProfiler.recommendedNutrientsList.get(arg0.getValue().getNutrientQuantity());
			//RecommendedNutrient(String nutrientCode, float nutrientQuantity)
			
			return (new SimpleStringProperty(String.format("%.2f", arg0.getValue().getNutrientQuantity())));
		}
	};
	
	Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>> recommendedNutrientUomCallback 
	= new Callback<CellDataFeatures<RecommendedNutrient, String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<RecommendedNutrient, String> arg0) {
			//write your code here
			Nutrient nutrient = Model.nutrientsMap.get(arg0.getValue().getNutrientCode());
			return nutrient.nutrientUomProperty();
		}
	};
}
