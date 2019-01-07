//Xueqian Zhang id:xueqianz
package lab8;

import java.util.Random;

public class Student {
	static int totalStudentsCreated;
	static int totalStudentsHelped;
	int studentID;
	Random rand = new Random();
	
	public Student() {
		totalStudentsCreated++;
		studentID = totalStudentsCreated;
	}
	
	public int askQuestion() {
		totalStudentsHelped++;
		return rand.nextInt(26)+5;
		
	}
	
	

}
