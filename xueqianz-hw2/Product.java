//Xueqian Zhang id: xueqianz
package hw2;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


public class Product {
	private StringProperty ndbNumber = new SimpleStringProperty();
	private StringProperty productName = new SimpleStringProperty();
	private StringProperty manufacturer = new SimpleStringProperty();
	private StringProperty ingredients = new SimpleStringProperty();
	private FloatProperty servingSize = new SimpleFloatProperty();
	private StringProperty servingUom = new SimpleStringProperty();
	private FloatProperty householdSize = new SimpleFloatProperty();
	private StringProperty householdUom = new SimpleStringProperty();
	ObservableMap<String, ProductNutrient> productNutrients = FXCollections.observableHashMap();
			
	
	public Product() {
		ndbNumber.set("");
		productName.set("");
		manufacturer.set("");
		ingredients.set("");
		servingSize.set(0);
		servingUom.set("");
		householdSize.set(0);
		householdUom.set("");
	}
	
	public Product(String ndbNumber, String productName, String manufacturer, String ingredients) {
		this.ndbNumber.set(ndbNumber);
		this.productName.set(productName);
		this.manufacturer.set(manufacturer);
		this.ingredients.set(ingredients);
		
	}
	
	public String getNdbNumber() {
		return ndbNumber.get();
	}

	public void setNdbNumber(String ndbNumber) {
		this.ndbNumber.set(ndbNumber);
	}
	
	public StringProperty ndbNumberProperty() { return ndbNumber; }

	public String getProductName() {
		return productName.get();
	}

	public void setProductName(String productName) {
		this.productName.set(productName);
	}
	
	public StringProperty productNameProperty() { return productName; }

	public String getManufacturer() {
		return manufacturer.get();
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer.set(manufacturer);
	}
	
	public StringProperty manufacturerProperty() { return manufacturer; }

	public String getIngredients() {
		return ingredients.get();
	}

	public void setIngredients(String ingredients) {
		this.ingredients.set(ingredients);
	}
	
	public StringProperty ingredientsProperty() { return ingredients; }

	public float getServingSize() {
		return servingSize.get();
	}

	public void setServingSize(float servingSize) {
		this.servingSize.set(servingSize);
	}
	
	public FloatProperty servingSizeProperty() { return servingSize; }

	public String getServingUom() {
		return servingUom.get();
	}

	public void setServingUom(String servingUom) {
		this.servingUom.set(servingUom);
	}
	
	public StringProperty servingUomProperty() { return servingUom; }

	public float getHouseholdSize() {
		return householdSize.get();
	}

	public void setHouseholdSize(float householdSize) {
		this.householdSize.set(householdSize);
	}
	
	public FloatProperty householdSizeProperty() { return householdSize; }

	public String getHouseholdUom() {
		return householdUom.get();
	}

	public void setHouseholdUom(String householdUom) {
		this.householdUom.set(householdUom);
	}
	
	public StringProperty householdUomProperty() { return householdUom; }
	
	//
	public void setProductNutrients(String nutrientCode, float nutrientQuantity) {
		productNutrients.put(nutrientCode, new ProductNutrient(nutrientCode, nutrientQuantity));
	}

	//
	public ObservableMap<String, ProductNutrient> getProductNutrients() {
		return productNutrients;
	}


	class ProductNutrient{
		private StringProperty nutrientCode = new SimpleStringProperty();
		private FloatProperty nutrientQuantity = new SimpleFloatProperty();
		
		public ProductNutrient() {
			nutrientCode.set("");
			nutrientQuantity.set(0);
		}
		
		//
		public ProductNutrient(String nutrientCode, float nutrientQuantity) {
			setNutrientCode(nutrientCode);
			setNutrientQuantity(nutrientQuantity);
		}

		public String getNutrientCode() {
			return nutrientCode.get();
		}

		public void setNutrientCode(String nutrientCode) {
			this.nutrientCode.set(nutrientCode);
		}
		
		public StringProperty nutrientCodeProperty() { return nutrientCode; }


		public float getNutrientQuantity() {
			return nutrientQuantity.get();
		}

		public void setNutrientQuantity(float nutrientQuantity) {
			this.nutrientQuantity.set(nutrientQuantity);
		}
		
		public FloatProperty nutrientQuantityProperty() { return nutrientQuantity; }

		
		
	}

}
