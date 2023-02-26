package test;

public class LongPascalTest {
    private static long Newton (long n, long k) {
        if (n * k != 0 && n != k) {
            int outcome = 1;
            long max = Math.max(k,n - k);
            for (long i = n; i > max; i--) { 
                outcome *= i; 
                outcome /= (n - i + 1);
            }

            return outcome;
        }

        return 1;
    }
    public static void main (String[] args) {
        long n = 1;
        long newton = Newton(n, n / 2);
        while (newton > 0) {
            n++;
            newton = Newton(n, n / 2);
        }
        System.out.println("wywala sie dla " + n + ": " + newton);
    }
}
