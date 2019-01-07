// Xueqian Zhang id: xueqianz
package practice;

public class Product {

	public String ndbNumber;
	public String productName;
	public String manufacturer;
	public String ingredients;
	public float servingSize;
	public String servingUom;
	public float householdSize;
	public String householdUom;
	
	public Product(String ndbNumber, String productName,String manufacturer, String ingredients) {
		this.ndbNumber = ndbNumber;
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.ingredients = ingredients;
	}

}