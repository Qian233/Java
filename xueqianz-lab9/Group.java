//Xueqian Zhang id:xueqianz
package lab9;

public class Group extends Guest {
	static int groupsServed;

	@Override
	void placeOrder() {
		// TODO Auto-generated method stub
		++groupsServed;
	}

}
