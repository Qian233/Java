//Xueqian Zhang id: xueqianz
package lab3;

public class MixedFraction extends Fraction {
	int naturalNumber;
	
	MixedFraction(int naturalNumber, int numerator, int denominator){
		this.naturalNumber = naturalNumber;
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString(){
		String s = naturalNumber + " " + numerator + "/" + denominator;
		return s;
	}
	
	public double toDecimal() {
		double d = (naturalNumber * denominator + numerator)/(double)denominator;
		return d;
	}
	
	public Fraction toFraction() {
		Fraction Fra = new Fraction(naturalNumber*denominator + numerator,denominator);
		return Fra;
	}
	
	public Fraction add(MixedFraction mf) {
		Fraction fa = this.toFraction().add(mf.toFraction());

		return fa;
		
	}

}
