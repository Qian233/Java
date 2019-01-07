//Xueqian Zhang id: xueqianz
package hw2;

import hw2.NutriProfiler.NutriEnum;

public abstract class Person {

	float age, weight, height, physicalActivityLevel; //age in years, weight in kg, height in cm
	String ingredientsToWatch;
	float[][] nutriConstantsTable = new float[NutriProfiler.RECOMMENDED_NUTRI_COUNT][NutriProfiler.AGE_GROUP_COUNT];

	NutriProfiler.AgeGroupEnum ageGroup;

	abstract void initializeNutriConstantsTable();
	abstract float calculateEnergyRequirement();
	
	//remove this default constructor once you have defined the child's constructor
	//Person() {}

	Person(float age, float weight, float height, float physicalActivityLevel, String ingredientsToWatch) {
		//write your code here
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.physicalActivityLevel = physicalActivityLevel;
		this.ingredientsToWatch = ingredientsToWatch;
		
		int ageIndex;
		if(age < 0.25f) {
			ageIndex = 0;
		}else if(age < 0.5f){
			ageIndex = 1;
		}else if(age < 1){
			ageIndex = 2 ;
		}else if(age < 3){
			ageIndex = 3 ;
		}else if(age < 8){
			ageIndex = 4 ;
		}else if(age < 13){
			ageIndex = 5 ;
		}else if(age < 18){
			ageIndex = 6 ;
		}else if(age < 30){
			ageIndex = 7 ;
		}else if(age < 50){
			ageIndex = 8 ;
		}
		else {
			ageIndex = 9 ;
		}
		
		for(NutriProfiler.AgeGroupEnum ae: NutriProfiler.AgeGroupEnum.values()) {
			if(ae.getAgeGroupIndex() == ageIndex) {
				ageGroup = ae;
			}
		}
		
	}
	
	//returns an array of nutrient values of size NutriProfiler.RECOMMENDED_NUTRI_COUNT. 
	//Each value is calculated as follows:
	//For Protein, it multiples the constant with the person's weight.
	//For Carb and Fiber, it simply takes the constant from the 
	//nutriConstantsTable based on NutriEnums' nutriIndex and the person's ageGroup
	//For others, it multiples the constant with the person's weight and divides by 1000.
	//Try not to use any literals or hard-coded values for age group, nutrient name, array-index, etc. 
	
	/*Uses the nutriConstantsTable populated by the Male or Female class to determine
	the constant factor from Table 3. For Carb and Fiber, it simply returns the constant. For Protein and amino acids,
	it multiplies the constant with the person¡¯s weight, and returns the value.
	*/
	
	float[] calculateNutriRequirement() {
		//write your code here
		
		
		float[] nutrientValues = new float[NutriProfiler.RECOMMENDED_NUTRI_COUNT];
		
			nutrientValues[0] = nutriConstantsTable[NutriEnum.PROTEIN.getNutriIndex()][ageGroup.getAgeGroupIndex()]*weight;
			nutrientValues[1] = nutriConstantsTable[NutriEnum.CARBOHYDRATE.getNutriIndex()][ageGroup.getAgeGroupIndex()];
			nutrientValues[2] = nutriConstantsTable[NutriEnum.FIBER.getNutriIndex()][ageGroup.getAgeGroupIndex()];
			
			for (int i = 3; i < NutriProfiler.RECOMMENDED_NUTRI_COUNT; i++)  {
				nutrientValues[i] = nutriConstantsTable[i][ageGroup.getAgeGroupIndex()]*weight/1000;
			}
		
		
		
		return nutrientValues;
	}
}
