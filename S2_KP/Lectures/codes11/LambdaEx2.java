interface NumericTestInterface {
	public boolean computeTest(int n); 
}

class Even implements NumericTestInterface{
	public boolean computeTest(int n){
		return n % 2 == 0;
	}
}

class Negative implements NumericTestInterface{
	public boolean computeTest(int n){
		return n < 0;
	}
}


public class LambdaEx2{
public static void main(String args[]) {
    
	/// Tworzenie z uzyciem zdefiniowanych klas implementujacych interfejs
	NumericTestInterface isEven = new Even();
	NumericTestInterface isNegative = new Negative();

	System.out.println(isEven.computeTest(5));
	System.out.println(isNegative.computeTest(-5));


    /// Tworznie bez uzycia zdefiniowanych klas implementujacych interfejs
	NumericTestInterface  isEvenL = (n) -> (n % 2) == 0;
	NumericTestInterface  isNegativeL = (n) -> (n < 0);

	System.out.println(isEvenL.computeTest(5));
	System.out.println(isNegativeL.computeTest(-5));
}
}