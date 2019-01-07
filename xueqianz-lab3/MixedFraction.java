package lab3;

public class MixedFraction extends Fraction {
	int naturalNumber;

	public MixedFraction(int naturalNumber,int numberator, int denominator) {
		this.naturalNumber = naturalNumber;
		this.numberator = numberator;
		this.denominator = denominator;
	}
	
	@Override
	public String toString() {
		return naturalNumber +" "+ numberator + "/" + denominator ;
	}
	
	
	@Override
	public double toDecimal() {
		return ((naturalNumber*denominator)+numberator)/(double)denominator ;
	}
	
	public Fraction toFraction() {
		return(new Fraction(((naturalNumber*denominator)+numberator),denominator));
	}
	
	Fraction add(MixedFraction mf){
		return this.toFraction().add(mf.toFraction());
	}

}
