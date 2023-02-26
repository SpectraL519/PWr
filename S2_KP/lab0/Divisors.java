public class Divisors {
    public static int div (int n) {
        n = Math.abs(n);
        if (n % 2 == 0) { return n / 2; }
        else {
            for (int i = 3; i <= n / 2; i += 2) {
                if (n % i == 0) { return n / i; }
            }    
        }

        return 1;
    }

    public static void main (String[] args) {
        int l = args.length;
        for (int i = 0; i < l; i++) {
            try { 
                int n = Integer.parseInt(args[i]);
                if (n == 0) { System.out.println(n + " 0 doesn't have natuiral divisors"); }
                else { System.out.println(n + " " + div(n)); }
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i] + " is not a natural number");
            }
        }

    }
}
