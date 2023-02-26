import java.util.function.*;

class LambdaFunction {
	public static void main(String args[]) {
		/// Wykorzystanie interface funkcyjnego. Metoda tego interfejsu przyjmuje 
        /// argument int i zwraca int.
        Function<Integer,Integer> func = (n) -> {
			int wynik = 1;
			for (int i = 1; i <= n; i++ ) {
				wynik = i * wynik;
			}
			return wynik;
        };
	    /// Apply jest metoda dla interfejsu Function.	
        System.out.println( func.apply(4)  );	
		

		/// W przeciwnym razie musialbym
		interface FunInterface {
			int test(int n); 
		}

		FunInterface func1 = (n) -> {
			int wynik = 1;
			for (int i = 1; i <= n; i++ ) {
				wynik = i * wynik;
			}
			return wynik;
		}; 
		System.out.println( func1.test(4) );

	}
}