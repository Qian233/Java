//Xueqian Zhang id:xueqianz
package exam2;

public class Caesar extends Message implements Encryptable {
	

	Caesar(String text, String key) {
		super(text, key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String encrypt() {
		// TODO Auto-generated method stub
		char[] c = new char[text.length()];
		c = text.toCharArray();
		char[] enc = new char[text.length()];
		for(int i = 0; i < text.length(); i++) {
			enc[i] = (char) (c[i] + Integer.parseInt(key.trim()));
			
			if(enc[i] > 'Z') {
				enc[i] = (char) (enc[i] + ('A' -'Z' -1));
			}
	
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < text.length(); i++) {
			sb.append(enc[i]);
		}
		
		return sb.toString();
	}

	@Override
	public String decrypt() {
		// TODO Auto-generated method stub
		char[] c = new char[text.length()];
		c = text.toCharArray();
		char[] dec = new char[text.length()];
		for(int i = 0; i < text.length(); i++) {
			
			dec[i] = (char) (c[i] - Integer.parseInt(key.trim()));
			
			if(dec[i] < 'A') {
				dec[i] = (char) (dec[i] - ('A' - 'Z' - 1));
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < text.length(); i++) {
			sb.append(dec[i]);
		}
		
		return sb.toString();
	}

	@Override
	boolean validateInput() {
		// TODO Auto-generated method stub
		for(int i = 0; i < text.length(); i++) {
			if(!(text.toUpperCase().charAt(i) >= 'A' && text.toUpperCase().charAt(i) <= 'Z') ) {
				return false;
			}
		}
		for(int i = 0; i < key.length(); i++) {
			if(!(key.toUpperCase().charAt(i) >= '0' && key.toUpperCase().charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}

}
