public class PrimeNumbers {
    private int primeSet[];
    private int position;
    
    PrimeNumbers (int n) {
        primeSet = new int[n];
    }
    
    boolean prime (int n) {
        double max = Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
    
    public void fillSet (int n) {
        position = 0;
        for (int i = 2; i < n; i++) {
            if (prime(i))   primeSet[position++] = i;
        }
    }

    public int number (int m) {
        if (m < position)   return primeSet[m];
        
        return 0;
    }
}



class Test {
    public static void main (String args[]) {
        try {
            int n = Integer.parseInt(args[0]);
            int l = args.length;
            if (n > 1) {
                PrimeNumbers P = new PrimeNumbers(n);
                P.fillSet(n);
                for (int i = 1; i < l; i++) {
                    try { 
                        int m = Integer.parseInt(args[i]);
                        if (m >= 0 && P.number(m) != 0) { System.out.println(m + " - " + P.number(m)); }
                        else    { System.out.println(m + " - liczba spoza zakresu"); }
                    }
                    catch (NumberFormatException ex) {
                        System.out.println(args[i] + " - Niepoprawne dane");
                    }
                }
            }
            else {
                System.out.println(n + " - Nieprawidlowy zakres");
            }
        }
        catch (NumberFormatException ex) {
            System.out.println(args[0] + " - Niepoprawne dane");
        }
    }
}