//Xueqian Zhang id: xueqianz
package hw2;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecommendedNutrient {
	private StringProperty nutrientCode = new SimpleStringProperty();
	private FloatProperty nutrientQuantity = new SimpleFloatProperty();
	
	public RecommendedNutrient() {
		nutrientCode.set("");
		nutrientQuantity.set(0);
	}
	
	
	public RecommendedNutrient(String nutrientCode, float nutrientQuantity) {
		this.nutrientCode.set(nutrientCode);;
		this.nutrientQuantity.set(nutrientQuantity);
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


	public void setNutrientQuantity(Float nutrientQuantity) {
		this.nutrientQuantity.set(nutrientQuantity);
	}
	
	public FloatProperty nutrientQuantityProperty() { return nutrientQuantity; }
	
	

}
