//Xueqian Zhang id:xueqianz
package lab9;

public class Individual extends Guest {
	static int individualsServed;

	@Override
	void placeOrder() {
		// TODO Auto-generated method stub
		++individualsServed;
	}

}
