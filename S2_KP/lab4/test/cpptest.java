package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;

public class cpptest {
    public static void main (String[] args) {
        int n = 5;
        String[] parameters = new String[n + 3]; // exe path, number, n + 1 = number od elements in row
        parameters[0] = "C:\\Coding\\PWr\\S2_KP\\lab_listy\\Lista2\\pascal.exe";
        parameters[1] = String.valueOf(n);
        int Index = 0;
        for (int i = 2; i < n + 3; i++) {
            parameters[i] = String.valueOf(Index++);
        }
        try {
            //C:\\Coding\\PWr\\S2_KP\\lab_listy\\Lista2
            Process Pascal = new ProcessBuilder(parameters).start();
            //InputStream PascalOut = Pascal.getInputStream();
            //InputStreamReader PascalOutReader = new InputStreamReader(Pascal.getInputStream());
            BufferedReader PascalOutReader = new BufferedReader(new InputStreamReader(Pascal.getInputStream()));
            String out;
            while ((out = PascalOutReader.readLine()) != null) {
                System.out.println(out);
            }
            Pascal.destroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
