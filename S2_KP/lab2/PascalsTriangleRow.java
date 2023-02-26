public class PascalsTriangleRow {
    private int Row[];
    private int size;

    private int Newton (int n, int k) {
        if (n * k != 0 && n != k) {
            int outcome = 1;
            int max = Math.max(k,n - k);
            for (int i = n; i > max; i--) { 
                outcome *= i; 
                outcome /= (n - i + 1);
            }

            return outcome;
        }

        return 1;
    }

    public PascalsTriangleRow (int n) {
        size = n + 1;
        Row = new int[size];
        for (int i = 0; i < size / 2; i++) {
            int factor = Newton(n,i); 
            Row[i] = factor;
            Row[n - i] = factor;
        }
        if (size % 2 != 0) {
            Row[(size / 2)] = Newton(n,size / 2); 
        }
    }

    public int Factor (int k) {
        if (k >= 0 && k < size) { return Row[k]; }

        return -1;
    }
}