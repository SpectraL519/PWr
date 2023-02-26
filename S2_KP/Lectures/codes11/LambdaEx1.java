interface Liczba {
   double podajWartosc();
}

interface Liczba2 {
    boolean test(int a, int b);
}

interface Silnia {
    int wartosc(int a);
}

interface SilniaGen<T> {
    T wartosc(T a);
}

public class LambdaEx1 {
    public static void main(String args[]) 
    {     
        // Tak nie mozna
        // Liczba p3 = new Liczba();

        // Przyklad 1
        // Klasa anonimowa implementujaca interface Liczba
        Liczba pi = new Liczba(){
            public double podajWartosc()
            {
                return 3.14;
            }
        };
        System.out.println(pi.podajWartosc());
                    
        // Zamiast klasy anonimowej definiujemy metode podajWartosc
        // za pomoca wyrazenia lambda. 
        // Wyrazenie musi byc zgodne z sygnatura metody.
        // W tym przypadku: bez argumentow, zwraca double
        Liczba piLambda = () -> 3.14;
        System.out.println(piLambda.podajWartosc());
        
        //-------------------------
        
        // Przyklad 2
        // Klasa anonimowa implementujaca interface Liczba
        Liczba2 sumaWiekszaOd10F = new Liczba2(){
             public boolean test(int a, int b)
             {
                 return ( a + b ) > 10;
             }   
        };      
        if ( sumaWiekszaOd10F.test(4,6) ) {
            System.out.println( " Suma liczb jest wieksza od 10 " );
        }
        
        // TO samo za pomoca wyrazenia lambda:               
        Liczba2 sumaWiekszaOd10 = (a,b) -> ( a + b ) > 10;
        if ( sumaWiekszaOd10.test(4,6) ) {
            System.out.println( " Suma liczb jest wieksza od 10 " );
        }
        
        //-------------------------       
        
        // Przyklad 3
        // Implementacja metody wartosc interfejsu Silnia.

        Silnia silnia = (n) -> {
			int r = 1;
			for ( int i=1;i<=n; i++ ) {
				r = i*r;
			}
			return r;
		};       
	    System.out.println("Silnia z 5 to:" + silnia.wartosc(5));

         
         /// To samo dla typu generycznego
         SilniaGen<Integer> silniagen = (n) -> {
			int r = 1;
			for ( int i=1;i<=n; i++ ) {
				r = i*r;
			}
			return r;
		};
         System.out.println("Silnia z 5 to:" + silniagen.wartosc(5));
    }
}