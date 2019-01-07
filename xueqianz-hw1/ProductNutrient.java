// Xueqian Zhang id: xueqianz
package practice;

public class ProductNutrient {
	public String ndbNumber;
	public String nutrientCode;
	public String nutrientName;
	public float quantity;
	public String nutrientUom;
	
	public ProductNutrient(String ndbNumber, String nutrientCode, String nutrientName, float quantity, String nutrientUom) {
		this.ndbNumber = ndbNumber;
		this.nutrientCode = nutrientCode;
		this.nutrientName = nutrientName;
		this.quantity = quantity;
		this.nutrientUom = nutrientUom;
	}
}