//Xueqian Zhang id:xueqianz
package lab8;

public class TA implements Runnable, Comparable<TA>{
	int studentsHelped;
	static int totalHelpTime;
	int helpTime;
	static final int MAX_HELP_TIME = 120;
	int taID;
	static int taCount = 0;
	


	public TA() {
		taCount++;
		taID = taCount;
	}

	@Override
	public void run() {
		//write your code here
		Student s;
		while(helpTime<=MAX_HELP_TIME&&JavaCourse.allDone==false) {
			
			synchronized (JavaCourse.studentQ) {
				s = JavaCourse.studentQ.poll();
			}
			if(s !=null) {
				try {
					studentsHelped++;
					int askTime = s.askQuestion();
					totalHelpTime = totalHelpTime + askTime;
					helpTime = helpTime + askTime;
				
					Thread.sleep(askTime);
					System.out.println(spacer(taID) + "TA" + taID+":Student" + s.studentID+":"+ askTime + "min");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(totalHelpTime >= taCount*MAX_HELP_TIME&&JavaCourse.allDone==false) {
				JavaCourse.allDone = true;
				System.out.println("****All done flag set by TA" +taID );
			}
			
		}
	}

	@Override
	public int compareTo(TA o) {
		//write your code here
		return o.helpTime- this.helpTime;
	}
	
	
	//do not change this method
	String spacer(int taID) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 1; i < taID; i++) {
			spaces.append("\t\t");
		}
		return spaces.toString();
	}
}
