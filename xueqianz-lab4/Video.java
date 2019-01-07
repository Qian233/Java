package lab4;

public class Video extends Content {
	static private int videoCount;
	static private int totalVideoTime;

	Video(String contentName, int learningTime) {
		super(contentName, learningTime);
		// TODO Auto-generated constructor stub
		videoCount++;
		totalVideoTime = totalVideoTime + learningTime;
	}

	public static int getVideoCount() {
		return videoCount;
	}


	public static int getTotalVideoTime() {
		return totalVideoTime;
	}


	@Override
	public void learn() {
		// TODO Auto-generated method stub
		System.out.println("Watching "+ contentId+contentName+learningTime);
	}

}
