package lab4;

public class PracticeProblem extends Content{
	static private int practiceProblemCount;
	static private int totalPracticeTime;
	int fileSize;

	PracticeProblem(String contentName, int learningTime,int fileSize) {
		super(contentName, learningTime);
		this.fileSize = fileSize;
		practiceProblemCount++;
		totalPracticeTime = totalPracticeTime + learningTime;
		// TODO Auto-generated constructor stub
	}

	public static int getPracticeProblemCount() {
		return practiceProblemCount;
	}


	public static int getTotalPracticeTime() {
		return totalPracticeTime;
	}


	@Override
	public void learn() {
		// TODO Auto-generated method stub
		System.out.println("Working on "+ contentId+contentName+learningTime);
	}

}
