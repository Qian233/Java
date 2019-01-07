package exam3;

import java.util.ArrayList;
import java.util.List;

public class Member implements Comparable<Member> {
	String name;
	List<String> friends = new ArrayList<>();
	
	public Member(String name) {
		this.name = name;
	}
	

	@Override
	public int compareTo(Member arg0) {
		// TODO Auto-generated method stub
		return arg0.friends.size() - friends.size();
	}
	
	

}
