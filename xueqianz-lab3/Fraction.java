//Xueqian Zhang id: xueqianz
package lab3;

public class Fraction {
	int numerator;
	int denominator;
	
	Fraction(){
		numerator = 1;
		denominator = 1;
	}
	
	Fraction(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString(){
		String s = numerator + "/" + denominator;
		return s;
		
	}
	
	public double toDecimal() {
		double d = numerator/(double)denominator;
		return d;
		
	}
	
	public Fraction add(Fraction f) {
		Fraction ff = new Fraction();
		int tempNumerator = this.numerator * f.denominator + this.denominator * f.numerator;
		int tempDenominator = this.denominator * f.denominator;
		
		int divisor = findGCD(tempNumerator, tempDenominator);
		ff.numerator = tempNumerator/divisor;
		ff.denominator = tempDenominator/divisor;
		return ff;
		
	}
	
	public int findGCD(int numerator, int denominator) {
		if(numerator == 0) {
			return 1;
		}
		if(denominator == 0) {
			return numerator;
		}
		
		return findGCD(denominator,numerator%denominator);
	}

}
