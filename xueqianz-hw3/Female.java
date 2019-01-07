//Xueqian Zhang id: xueqianz
package hw3;

public class Female extends Person {

	float[][] nutriConstantsTableFemale = new float[][]{
		//AgeGroups: 3M, 6M, 1Y, 3Y, 8Y, 13Y, 18Y, 30Y, 50Y, ABOVE 
		{1.52f, 1.52f, 1.2f, 1.05f, 0.95f, 0.95f, 0.71f, 0.8f, 0.8f, 0.8f}, //0: Protein constants
		{60, 60, 95, 130, 130, 130, 130, 130, 130, 130}, //1: Carbohydrate
		{19, 19, 19, 19, 25, 26, 26, 25, 25, 21},  //2: Fiber constants
		{36, 36, 32, 21, 16, 15, 14, 14, 14, 14}, 	//3: Histidine
		{88, 88, 43, 28, 22, 21, 19, 19, 19, 19}, 	//4: isoleucine
		{156, 156, 93, 63, 49, 47, 44 , 42, 42, 42},//5: leucine
		{107, 107, 89, 58, 46, 43, 40, 38, 38, 38}, //6: lysine
		{59, 59, 43, 28, 22, 21, 19, 19, 19, 19}, 	//7: methionine
		{59, 59, 43, 28, 22, 21, 19, 19, 19, 19}, 	//8: cysteine
		{135, 135, 84, 54, 41, 38, 35, 33, 33, 33}, //9: phenylalanine
		{135, 135, 84, 54, 41, 38, 35, 33, 33, 33}, //10: phenylalanine
		{73, 73, 49, 32, 24, 22, 21, 20, 20, 20}, 	//11: threonine
		{28, 28, 13, 8, 6, 6, 5, 5, 5, 5}, 			//12: tryptophan
		{87, 87, 58, 37, 28, 27, 24, 24, 24, 24	}  	//13: valine
	};
	
	Female(float age, float weight, float height, float physicalActivityLevel, String ingredientsToWatch) {
		super(age, weight, height, physicalActivityLevel, ingredientsToWatch);
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.physicalActivityLevel = physicalActivityLevel;
		this.ingredientsToWatch = ingredientsToWatch;
		// get age index
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
	
	@Override
	void initializeNutriConstantsTable() {
		//initialize
	    for(int i = 0; i< NutriProfiler.RECOMMENDED_NUTRI_COUNT; i++){
	        for (int j = 0; j < NutriProfiler.AGE_GROUP_COUNT; j++){
	        	nutriConstantsTable[i][j] = nutriConstantsTableFemale[i][j];
	        }
	    }
	}

	@Override
	float calculateEnergyRequirement() {
		int X;
		float A;
		float B;
		float C;
		float D;
		float energy = 0;
		//calculate Energy according to personal data:
		if(ageGroup.getAgeGroupIndex()==0) {
			X = -75;
			energy = 89 * weight - X;
		}
		if( ageGroup.getAgeGroupIndex()==1) {
			X = 44;
			energy = 89 * weight - X;
		}
		if(ageGroup.getAgeGroupIndex()==2) {
			X = 78;
			energy = 89 * weight - X;
		}
		if(ageGroup.getAgeGroupIndex()==3) {
			X = 80;
			energy = 89 * weight - X;
		}
		if(ageGroup.getAgeGroupIndex()== 4 || ageGroup.getAgeGroupIndex()== 5 || ageGroup.getAgeGroupIndex()== 6) {
			A = 135.3f;
			B = 30.8f;
			C = 10;
			D = 934;
			energy = A - (B * age) + physicalActivityLevel * (C *weight + D * height / 100) + 20;
		}
		if(ageGroup.getAgeGroupIndex()== 7|| ageGroup.getAgeGroupIndex()== 8 || ageGroup.getAgeGroupIndex()== 9) {
			A = 354;
			B = 6.91f;
			C = 9.36f;
			D = 726;
			energy = A - (B * age) + physicalActivityLevel* (C * weight + D * height / 100);
		}
		
		return energy;
	}

}
