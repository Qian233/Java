//Xueqian Zhang id: xueqianz
package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFiler extends DataFiler {

	@Override
	public void writeFile(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean readFile(String filename) {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				StringBuilder sb = new StringBuilder();
				for(int i = 5; i < values.length - 1; i++) {
					sb.append(values[i].trim() + ",");
				}
				sb.append(values[values.length-1]);
				
				String ingredientsToWatchString = sb.toString();
				
				if(values[0].trim().toLowerCase().equals("Female".toLowerCase())) {
					NutriByte.person = new Female(Float.parseFloat(values[1]), Float.parseFloat(values[2]), 
							Float.parseFloat(values[3]), Float.parseFloat(values[4]), ingredientsToWatchString);
					NutriByte.person.initializeNutriConstantsTable();
				}
				else {
					NutriByte.person = new Male(Float.parseFloat(values[1]), Float.parseFloat(values[2]), 
							Float.parseFloat(values[3]), Float.parseFloat(values[4]), ingredientsToWatchString);
					NutriByte.person.initializeNutriConstantsTable();
				}
				return true;
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
