package lab3;

public class Fraction {
	int numberator;
	int denominator;
	
	public Fraction() {
		numberator =1;
		denominator = 1;
	}
	
	public Fraction(int numberator, int denominator) {
		this.numberator = numberator;
		this.denominator = denominator;
	}

	@Override
	public String toString() {
		return  numberator + "/" + denominator ;
	}
	
	public double toDecimal() {
		return  numberator/(double)denominator ;
	}
	
	Fraction add(Fraction f){
		int newDenominator = this.denominator*f.denominator;
		int newNumerator = this.numberator*f.denominator+this.denominator*f.numberator;
		
		int finalNumerator = newNumerator/this.findGCD(newNumerator, newDenominator);
		int finalDenominator = newDenominator/this.findGCD(newNumerator, newDenominator);
		
		return new Fraction(finalNumerator,finalDenominator);
	}
	
	int findGCD(int numberator,int denominator) {
		if (numberator == 0) {
			return 1;
		}
		else if(denominator == 0) {
			return numberator;
		}else {
			return this.findGCD(denominator,numberator%denominator );
		}
		
	}
	

}
