package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaCourse {
	Content[] contents;

	public static void main(String[] args) {
		JavaCourse javaCourse = new JavaCourse();
		javaCourse.loadContentsArray(javaCourse.readJavaContent());
		javaCourse.printCourseSummary();
		javaCourse.peruseContent();
	}

	/**readJavaContent() reads JavaContent.csv
	 * and loads each of its row as a string into an array. 
	 * It returns the array.
	 */
	String[] readJavaContent() {
		StringBuilder contentStrings = new StringBuilder();
		try {
			Scanner input = new Scanner(new File("JavaContent.csv"));
			while (input.hasNextLine()) {
				contentStrings.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contentStrings.toString().split("\n");
	}

	
	void loadContentsArray(String[] contentStringsArray) {
		//write your code here
		//refer to the handout for instructions.
		contents = new Content[contentStringsArray.length];
		for(int i = 0; i<contentStringsArray.length;i++) {
			String[] data = contentStringsArray[i].split(",");
			if(data[0].toLowerCase().trim().equals("video")) {
				Video v = new Video(data[1].trim(),Integer.parseInt(data[2].trim()));
				contents[i] = v;
			}else {
				PracticeProblem p = new PracticeProblem(data[1].trim(),
						Integer.parseInt(data[2].trim()),Integer.parseInt(data[3].trim()));
				contents[i] = p;
			}
		}
	}
	
	//do not change this method
	void printCourseSummary() {
		System.out.println("*** JAVA COURSE SUMMARY ***");
		System.out.println("------------------------------------------");
		System.out.printf("%-30s%3d%n", "Total contents:", Content.getContentCount());
		System.out.printf("%-30s%3d%n", "Total videos:", Video.getVideoCount());
		System.out.printf("%-30s%3d%n", "Total practice problems:", PracticeProblem.getPracticeProblemCount());
		System.out.println("------------------------------------------");
		System.out.printf("%-30s%3d min.%n", "Total practice time:", PracticeProblem.getTotalPracticeTime());
		System.out.printf("%-30s%3d min.%n", "Total video time:", Video.getTotalVideoTime());
		System.out.println("------------------------------------------");
	}
	
	void peruseContent() {
		//write your code here
		//refer to the handout for instructions.
		for(Content c: contents) {
			System.out.println(c.contentId+". "+c.contentName+" "+c.learningTime);
		}
	}
}
