public class FigureTest {
    public static void main (String args[]) {
        int l = args[0].length();
        int position = 1;

        for (int i = 0; i < l; i++) {
            if (args[0].charAt(i) == 'c') { // circle
                double r = Double.parseDouble(args[position++]);
                Figure c = new Circle(r);
                c.PrintValues(i + 1,"circle",c.Length(),c.Surface());
            }
            else if (args[0].charAt(i) == 'q') { // quadrangle
                double a = Double.parseDouble(args[position]);
                double phi = Double.parseDouble(args[position + 4]);
                if (phi == 90) {
                    double b = Double.parseDouble(args[position + 1]);
                    if (a != b) { // rectangle
                        Figure r = new Rectangle(a,b);
                        r.PrintValues(i + 1,"rectangle",r.Length(),r.Surface());
                    }
                    else {
                        b = Double.parseDouble(args[position + 2]);
                        if (a != b) { // rectangle
                            Figure r = new Rectangle(a,b);
                            r.PrintValues(i + 1,"rectangle",r.Length(),r.Surface());
                        }
                        else { // square
                            Figure s = new Square(a);
                            s.PrintValues(i + 1,"square",s.Length(),s.Surface());
                        }
                    }        
                }
                else { // rhomb
                    Figure rh = new Rhomb(a,phi);
                    rh.PrintValues(i + 1,"rhomb",rh.Length(),rh.Surface());
                }
                position += 5;
            }
            else if (args[0].charAt(i) == 'p') { // pentagon
                double a = Double.parseDouble(args[position++]);
                Figure p = new Pentagon(a);
                p.PrintValues(i + 1,"pentagon",p.Length(),p.Surface());
            }
            else if (args[0].charAt(i) == 'h') { // hexagon
                double a = Double.parseDouble(args[position++]);
                Figure h = new Hexagon(a);
                h.PrintValues(i + 1,"hexagon",h.Length(),h.Surface());
            }
            else { 
                System.out.println("Niepoprawne dane");
                break;
            }
        }
    }
}