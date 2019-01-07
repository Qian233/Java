//Xueqian Zhang id:xueqianz
package lab9;

import java.util.Queue;
import java.util.Random;

public class DynamicKitchen implements Runnable{
	
	int mealStock = 175, mealRate = 6;
	boolean underStock;
	int guestsServed, income;
	Queue<Guest> guestQ;
	
	DynamicKitchen (Queue<Guest> guestQ) {
		this.guestQ = guestQ;
	}

	@Override
	public void run() {
		//write your code here
		
		Guest g;
		while(DynamicDiner.dinerOpen && !underStock) {
			synchronized (guestQ) {
				g = guestQ.poll();
			}
			if(g !=null) {
				try {
					guestsServed++;
					g.placeOrder();
					
					if(g instanceof Individual) {
						mealStock = mealStock -1;
						income = income + mealRate;
					}else {
						mealStock = mealStock -4;
						income = income + 4*mealRate;
					}
					
					Random r = new Random();
					int servetime = r.nextInt(16)+5;
					Thread.sleep(servetime);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(mealStock <= 4) {
				underStock = true;
			}
		}

		
	}
}
