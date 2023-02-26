public class PascalTest {
    public static void main (String args[]) {
        try {
            int n = Integer.parseInt(args[0]);
            if (n >= 0) {
                int l = args.length;
                PascalsTriangleRow R = new PascalsTriangleRow(n);
                for (int i = 1; i < l; i++) {
                    try { 
                        int k = Integer.parseInt(args[i]);
                        if (R.Factor(k) == -1)  { System.out.println(k + " - Liczba spoza zakresu"); }
                        else    { System.out.println(k + " - " + R.Factor(k)); }
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