//Xueqian Zhang id: xueqianz
package hw3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nutrient {
	private StringProperty nutrientCode = new SimpleStringProperty();
	private StringProperty nutrientName = new SimpleStringProperty();
	private StringProperty nutrientUom = new SimpleStringProperty();
	
	public Nutrient() {
		nutrientCode.set("");
		nutrientName.set("");
		nutrientUom.set("");
	}
	
	public Nutrient(String nutrientCode, String nutrientName, String nutrientUom) {
		this.nutrientCode.set(nutrientCode);
		this.nutrientName.set(nutrientName);
		this.nutrientUom.set(nutrientUom);
	}

	public String getNutrientCode() {
		return nutrientCode.get();
	}

	public void setNutrientCode(String nutrientCode) {
		this.nutrientCode.set(nutrientCode);
	}
	
	public StringProperty nutrientCodeProperty() { return nutrientCode; }

	public String getNutrientName() {
		return nutrientName.get();
	}

	public void setNutrientName(String nutrientName) {
		this.nutrientName.set(nutrientName);
	}
	
	public StringProperty nutrientNameProperty() { return nutrientName; }

	public String getNutrientUom() {
		return nutrientUom.get();
	}

	public void setNutrientUom(String nutrientUom) {
		this.nutrientUom.set(nutrientUom);
	}
	
	public StringProperty nutrientUomProperty() { return nutrientUom; }

}
