import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





class PascalsTriangle {
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
    JLabel PascalsTriangleVisualization;
    String number = "";


    static String ConvertToMultiline (String original) {
        return "<html>" + original.replaceAll("\n","<br>");
    }
    

    MyFrame () {
        super("Pascal's Triangle");

        this.setBounds(64,64,1024,768);
        this.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        this.setLayout(new GridLayout(5,1));
        
        Instructions = new Label ("Enter desired Pascal's triangle size and click \"Proceed\" or press ENTER",Label.CENTER);

        Description = new Label();
        Description.setAlignment(Label.CENTER);

        PascalsTriangleVisualization = new JLabel();
        PascalsTriangleVisualization.setHorizontalAlignment(SwingConstants.LEFT);
        PascalsTriangleVisualization.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        
        ScrollablePanel = new JScrollPane(PascalsTriangleVisualization);

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
        //add(PascalsTriangleVisualization); // for PascalsTriangleVisualization as Label not JLabel

        this.addWindowListener(new WinAdapt());

        //pack();
    }


    public void Action () {
        Description.setText("Pascal's triangle for range: " + number);
        try {
            int n = Integer.parseInt(number);
            PascalsTriangleVisualization.setOpaque(true); // allows for changing label colors
            if (n < 0) {
                PascalsTriangleVisualization.setText("Invalid range");
                PascalsTriangleVisualization.setBackground(Color.RED);
                Description.setBackground(Color.RED);
            }
            else if (n >= 30) {
                PascalsTriangleVisualization.setText("Out of range");
                PascalsTriangleVisualization.setBackground(Color.RED);
                Description.setBackground(Color.RED);
            }
            else {
                PascalsTriangle PT = new PascalsTriangle(n);
                PT.GenrateTriangle();
                PascalsTriangleVisualization.setText(ConvertToMultiline(PT.ConvertTriangle()));
                PascalsTriangleVisualization.setBackground(Color.GREEN);
                Description.setBackground(Color.GREEN);
            }
        }
        catch (NumberFormatException ex) {
            PascalsTriangleVisualization.setText("Incorrect data");
            PascalsTriangleVisualization.setBackground(Color.RED);
            Description.setBackground(Color.RED);
        }

        DataInput.setText("");
        number = "";
        //pack();
    }
}



public class GraphicPascal {
    public static void main (String[] args) {
        MyFrame Pascal = new MyFrame();
        Pascal.setVisible(true);
    }
}