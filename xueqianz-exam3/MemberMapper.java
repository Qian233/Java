
package exam3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MemberMapper {

	String[] members = {"Alice", "Bob", "Charles", "David", "Frank"};
	String[][] memberFriends = 
		{{"Bob", "Joe", "Sara", "Amy", "Frank", "Nancy"}, 						//Alice's friends
				{"Alice", "Nancy", "Peter", "Steve", "Frank", "Tim", "Amy"}, 	//Bob's friends
				{"Sara", "Kevin", "Peter", "Steve"}, 							//Charles friends
				{"Steve", "Amy"}, 												//David's friends
				{"Alice", "Bob", "Mary"}};										//Frank's friends

	public static void main(String[] args) {
		MemberMapper mapper = new MemberMapper();
		List<Member> memberList = mapper.setupMemberList();
		System.out.println("\n------ Member Popularity ------");
		mapper.printPopularityList(mapper.getMemberPopularityList(memberList));
		Map<String, List<String>> memberMap = mapper.setupMemberMap(memberList);
		System.out.println("\n------ Member Map ------");
		mapper.printMap(memberMap);
		System.out.println("\n------ Reversed Map ------");
		Map<String, List<String>> reversedMap = mapper.setupReversedMap(memberList, memberMap);
		mapper.printMap(reversedMap);
		System.out.println("\n----- Find Common Friends -----");
		System.out.println("Enter first name");
		Scanner input = new Scanner(System.in);
		String firstName = input.nextLine();
		System.out.println("Enter second name");
		String secondName = input.nextLine();
		List<String> commonFriends = mapper.findCommonFriends(memberMap, reversedMap, firstName, secondName);
		if (commonFriends != null && commonFriends.size() >0) {
			System.out.println("The common friends are: ");
			for (String s : commonFriends) System.out.println(s);
		} else {
			System.out.println("Sorry! No common friends found!");
		}
		System.out.println("\n------ Thank you! ------");
		input.close();
	}

	/****************************************** printing ******************************************/

	void printPopularityList(List<Member> memberList) {
		if (memberList != null)
			for (Member c : memberList) {
				System.out.printf("Member: %s has %d friends%n", c.name, c.friends.size());
			}
	}

	void printMap(Map<String, List<String>> map) {
		if (map != null)
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ": ");
				for (String c: entry.getValue()) {
					System.out.print(c + " ");
				}
				System.out.println();
			}
	}

	/**************************Write your code from this line onwards **********************/

	/** setupMemberList() uses the data in the two arrays - members and memberFriends - 
	 * given above to create a memberList that has Member objects. 
	 * Each member object has a name and a list of friends
	 * In terms of indexing, member[i]'s friends are in memberFriends[i] array. 
	 */
	List<Member> setupMemberList() {
		//write your code here
		List<Member> lm = new ArrayList<>();
		for(int i = 0; i< members.length; i++) {
			Member m = new Member(members[i]);
			for(int j = 0; j< memberFriends[i].length;j++) {
				m.friends.add(memberFriends[i][j]);
			}
			lm.add(m);
		}
		return lm;
	}

	/**getMemberPopularityList() sorts the membersList in the order of 
	 * member's number of friends in descending order 
	 */
	List<Member> getMemberPopularityList(List<Member> memberList) {
		//write your code here
//		List<Member> lm = new ArrayList<>();
		//List<Member> memberPopularityList = 
		Collections.sort(memberList);
		
//		for(Member m: memberList) {
//			System.out.println("Member: "+m.name+ " has "+ m.friends.size() + " frineds");
//		}
		
		// need to be ficed
//		lm = Collections.sort(memberList);
		
		return memberList;//?????
	}

	/**setupMemberMap() takes memberList as parameter
	 * and converts it into a map. It returns the map.
	 * The map has member's name as the Key and the list of his/her friends's names as the value.
	 * There is no case-conversion, i.e. 'Alice' is stored as a key and not 'alice'. 
	 * Note that the keys as well as the friends' names list should be
	 * alphabetically sorted
	 * @param memberList
	 * @return
	 */
	Map<String, List<String>> setupMemberMap(List<Member> memberList) {
		//write your code here
		Map<String, List<String>> memberMap= new TreeMap<>(); 
		for(Member m: memberList) {
			memberMap.put(m.name, m.friends);
		}
//		Collections.sort(memberList, new Comparator());
		
		return memberMap;
	}


	/**setReversedMap() uses the memberList and memberMap to create a reversedMap
	 * You may or may not use both memberList and memberMap depending on your approach.
	 * The method should return reversed map with non-member names as the keys 
	 * and the list of their member-friends as their values.
	 * Note that the keys as well as the friends' names list should be
	 * alphabetically sorted
	 */
	Map<String, List<String>> setupReversedMap(List<Member> memberList, Map<String, List<String>> memberMap) {
		//write your code here
		Map<String, List<String>> reversedMap = new TreeMap<>();
		
		List<String> unique = new ArrayList<>();
		for(String[] s: memberFriends) {
			for(String str: s) {
				if(!unique.contains(str)) {
					unique.add(str);
				}
			}
		}
		for(String s: unique) {
			List<String> memList = new ArrayList<>();
			for(Member m: memberList) {
				if(!reversedMap.containsKey(s)) {
					if(m.friends.contains(s)&& !memberMap.keySet().contains(s)) {
						memList.add(m.name);
						reversedMap.put(s, memList);
					}
				}
				else {
					if(m.friends.contains(s)) {
						reversedMap.get(s).add(m.name);
					}
					
				}
				
			}
		}
//		for(Member m: memberList) {
//			for(String s: m.friends) {
//				for(String str: members) {
//					if(str.equals(s)) {
//						continue;
//					}else {
//						if(!reversedMap.keySet().contains(s)) {
//							List<String> memList = new ArrayList<>();
//							memList.add(m.name);
//							reversedMap.put(s, memList);
//						}
//						else {
//							reversedMap.get(s).add(m.name);
//						}
//					}
//				}
//			}
//		}
		//need to be fixed
		//Collections.sort(reversedMap.keySet().toString().split(""));
		return reversedMap;
	}

	/**findCommonFriends() takes two names: name1 and name2, and finds common friends, if any, using the two maps - memberMap and reversedMap. 
	 * Depending on your approach, you may or may not use both maps to find common friends. 
	 * The method returns a list of common friends, if found. Else it returns an empty list. 
	 */
	List<String> findCommonFriends(Map<String, List<String>> memberMap, Map<String, List<String>> reversedMap, String name1, String name2) {
		//write your code here
		//1both member;
		List<String> CommonFriends = new ArrayList<>();
		
		if(memberMap.keySet().contains(name1)&&memberMap.keySet().contains(name2)) {
			for(String s: memberMap.get(name1)) {
				for(String s2: memberMap.get(name2)) {
					if(s.equals(s2)) {
						CommonFriends.add(s);
					}
				}
			}
		}
		
		//2 1 member 1 other:
		if(memberMap.keySet().contains(name1)&&reversedMap.keySet().contains(name2)) {
			for(String s: memberMap.get(name1)) {
				for(String s2: reversedMap.get(name2)) {
					if(s.equals(s2)) {
						CommonFriends.add(s);
					}
				}
			}
		}
		//2 1 member 1 other:
		if(memberMap.keySet().contains(name2)&&reversedMap.keySet().contains(name1)) {
			for(String s: memberMap.get(name2)) {
				for(String s2: reversedMap.get(name1)) {
					if(s.equals(s2)) {
						CommonFriends.add(s);
					}
				}
			}
		}
		// 2 other:
		if(reversedMap.keySet().contains(name1)&&reversedMap.keySet().contains(name2)) {
			for(String s: reversedMap.get(name1)) {
				for(String s2: reversedMap.get(name2)) {
					if(s.equals(s2)) {
						CommonFriends.add(s);
					}
				}
			}
		}

		
		
		return CommonFriends;
	}
}
