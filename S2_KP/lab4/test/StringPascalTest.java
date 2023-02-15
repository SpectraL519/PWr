package test;

public class StringPascalTest {
    static class PascalsTriangle {
        private int[][] Triangle;
        private int n;
    
        PascalsTriangle (int n) {
            this.n = n;
            Triangle = new int[this.n + 1][this.n + 1];
        }
    
        public void GenrateTriangle () {
            Triangle[0][0] = 1;
            for (int i = 1; i < this.n + 1; i++) {
                Triangle[i][0] = 1;
                Triangle[i][i] = 1;
                for (int j = 1; j < i; j++) {
                    Triangle[i][j] = Triangle[i - 1][j] + Triangle[i - 1][j - 1];
                }
            }
        }
    
        public String ConvertTriangle () {
            String outcome = "";
            for (int i = 0; i < this.n + 1; i++) {
                outcome += String.valueOf(i) + ": ";
                for (int j = 0; j < i + 1; j++) {
                    outcome += String.valueOf(Triangle[i][j]) + " ";
                }
                outcome += "\n";
            }
    
            return outcome;
        }
    }

    public static void main (String[] args) {
        int n = 3;
        PascalsTriangle PT = new PascalsTriangle(n);
        PT.GenrateTriangle();
        System.out.println(PT.ConvertTriangle());
    }
}
