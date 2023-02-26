interface LambdaInterface {
        String getValue(String a);
}

class MyClass {
    String strMethod1(String str) {
        String back= "";
	for ( int i=str.length()-1;i>=0; i-- ) {
		back += str.charAt(i);
	}
	return back;
    }
}        
        
public class LArg {
        // Metoda pobiera jako parametr obiekt interfejsu oraz String.
        // Metoda zwraca String (tak samo jak metoda getValue)
        public static String strMethod(LambdaInterface lba,String s) {
                return lba.getValue(s);
        } 
        
	public static void main(String args[]){
            
                
                // Metoda wywolana jest od wyrazenia lambda (obiekt interfejsu) oraz Stringa
                LambdaInterface obj = (str)-> str.toUpperCase();
                String res = strMethod(obj,"Jakie litery?");
		System.out.println(res );
            
                /// Referencja do metody strMethod1. Mozna poniewaz metoda jest zgodna z 
                /// metoda getValue          
                MyClass my = new MyClass();
                String res1 = strMethod (my::strMethod1, "To jest moj tekst!!");
		System.out.println(res1);
	}
}