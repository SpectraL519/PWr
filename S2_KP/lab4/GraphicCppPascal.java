import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.Process;
import java.io.BufferedReader;
import java.io.InputStreamReader;





class WinAdapt extends WindowAdapter {
    public void windowClosing (WindowEvent e) { System.exit(0); }
}



class ButtonAdapt implements ActionListener {
    MyFrame F;
    ButtonAdapt (MyFrame F) { this.F = F; }
    public void actionPerformed (ActionEvent e) { F.Action(); }
}



class MyButton extends Button {
    MyButton (MyFrame F) {
        super("Proceed");
        addActionListener(new ButtonAdapt(F));
    }
}



class MyFrame extends Frame {
    Label Instructions;
    TextField DataInput;
    Label Description;
    JScrollPane ScrollablePanel;
    JLabel PascalsTriangleRowVisualization;
    String number = "";

    
    static String ConvertToMultiline (String original) {
        return "<html>" + original.replaceAll("\n","<br>");
    }
    

    MyFrame () {
        super("C++ Pascal's Triangle Row");

        this.setBounds(64,64,1024,768);
        this.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        this.setLayout(new GridLayout(5,1));
        
        Instructions = new Label ("Enter desired Pascal's triangle row number and click \"Proceed\" or press ENTER",Label.CENTER);

        Description = new Label();
        Description.setAlignment(Label.CENTER);

        PascalsTriangleRowVisualization = new JLabel();
        PascalsTriangleRowVisualization.setHorizontalAlignment(SwingConstants.LEFT);
        PascalsTriangleRowVisualization.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        
        ScrollablePanel = new JScrollPane(PascalsTriangleRowVisualization);

        DataInput = new TextField(10);
        DataInput.addKeyListener(new KeyAdapter() {
            public void keyPressed (KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Action();
                }
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (number.length() > 0) {
                        number = number.substring(0,number.length() - 1);
                        DataInput.setText(number);
                    }
                }
                else {
                    char c = e.getKeyChar();
                    number += c;
                }
            }
        });
        
        MyButton Action = new MyButton(this);

        this.add(Instructions);
        this.add(DataInput);
        this.add(Action);
        this.add(Description);
        this.add(ScrollablePanel); // for PascalsTriangleVizualization
        //add(PascalsTriangleRowVisualization);

        this.addWindowListener(new WinAdapt());
    }


    public void Action () {
        Description.setText("Pascal's triangle row for row number: " + number);
        try {
            int n = Integer.parseInt(number);
            PascalsTriangleRowVisualization.setOpaque(true); // allows for changing label colors
            if (n < 0) {
                PascalsTriangleRowVisualization.setText("Invalid range");
                PascalsTriangleRowVisualization.setBackground(Color.RED);
                Description.setBackground(Color.RED);
            }
            else if (n >= 30) {
                PascalsTriangleRowVisualization.setText("Out of range");
                PascalsTriangleRowVisualization.setBackground(Color.RED);
                Description.setBackground(Color.RED);
            }
            else {
                try {
                    String[] parameters = new String[n + 3]; // exe path, number, n + 1 = number od elements in row
                    parameters[0] = System.getProperty("user.dir") + "\\pascal.exe"; // plik skopiowany z C:\\Coding\\PWr\\S2_KP\\lab_listy\\Lista2, skompilowany z PascalsTriangleRow.cpp i declarations.cpp;
                    parameters[1] = String.valueOf(n);
                    int Index = 0;
                    for (int i = 2; i < n + 3; i++) {
                        parameters[i] = String.valueOf(Index++);
                    }

                    Process Pascal = new ProcessBuilder(parameters).start();
                    BufferedReader PascalOutReader = new BufferedReader(new InputStreamReader(Pascal.getInputStream()));

                    String outcome = "";
                    String tmp;
                    while ((tmp = PascalOutReader.readLine()) != null) {
                        outcome += tmp + "\n";
                    }
                    PascalsTriangleRowVisualization.setText(ConvertToMultiline(outcome));

                    Pascal.destroy();

                    PascalsTriangleRowVisualization.setBackground(Color.GREEN); 
                    Description.setBackground(Color.GREEN);
                }
                catch (Exception e) {
                    PascalsTriangleRowVisualization.setText("Something went wrong while operating the process");
                    PascalsTriangleRowVisualization.setBackground(Color.RED);
                    Description.setBackground(Color.RED);
                }
            }
        }
        catch (NumberFormatException ex) {
            PascalsTriangleRowVisualization.setText("Incorrect data");
            PascalsTriangleRowVisualization.setBackground(Color.RED);
            Description.setBackground(Color.RED);
        }
        DataInput.setText("");
        number = "";

    }
}



public class GraphicCppPascal {
    public static void main (String[] args) {
        MyFrame Pascal = new MyFrame();
        Pascal.setVisible(true);
    }
}
