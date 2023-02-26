public class FigureTestTab {
    private static void PrintValues (int nr, String figure, double L, double S) {
        System.out.println("Object " + nr + ": " + figure);
        System.out.println("\tLength = " + L);
        System.out.println("\tSurface = " + S);      
    }


    
    public static void main (String args[]) {
        int l = args[0].length();
        int position = 1;

        String FigureType[] = new String[l];
        double LengthTab[] = new double[l];
        double SurfaceTab[] = new double[l];

        for (int i = 0; i < l; i++) {
            if (args[0].charAt(i) == 'c') { // circle
                double r = Double.parseDouble(args[position++]);
                Figure c = new Circle(r);
                FigureType[i] = "circle";
                LengthTab[i] = c.Length();
                SurfaceTab[i] = c.Surface();
            }
            else if (args[0].charAt(i) == 'q') { // quadrangle
                double a = Double.parseDouble(args[position]);
                double phi = Double.parseDouble(args[position + 4]);
                if (phi == 90) {
                    double b = Double.parseDouble(args[position + 1]);
                    if (a != b) { // rectangle
                        Figure r = new Rectangle(a,b);
                        FigureType[i] = "rectangle";
                        LengthTab[i] = r.Length();
                        SurfaceTab[i] = r.Surface();
                    }
                    else {
                        b = Double.parseDouble(args[position + 2]);
                        if (a != b) { // rectangle
                            Figure r = new Rectangle(a,b);
                            FigureType[i] = "rectangle";
                            LengthTab[i] = r.Length();
                            SurfaceTab[i] = r.Surface();
                        }
                        else { // square
                            Figure s = new Square(a);
                            FigureType[i] = "square";
                            LengthTab[i] = s.Length();
                            SurfaceTab[i] = s.Surface();
                        }
                    }        
                }
                else { // rhomb
                    Figure rh = new Rhomb(a,phi);
                    FigureType[i] = "rhomb";
                    LengthTab[i] = rh.Length();
                    SurfaceTab[i] = rh.Surface();
                }
                position += 5;
            }
            else if (args[0].charAt(i) == 'p') { // pentagon
                double a = Double.parseDouble(args[position++]);
                Figure p = new Pentagon(a);
                FigureType[i] = "pentagon";
                LengthTab[i] = p.Length();
                SurfaceTab[i] = p.Surface();
            }
            else if (args[0].charAt(i) == 'h') { // hexagon
                double a = Double.parseDouble(args[position++]);
                Figure h = new Hexagon(a);
                FigureType[i] = "hexagon";
                LengthTab[i] = h.Length();
                SurfaceTab[i] = h.Surface();
            }
            else { 
                FigureType[i] = "Niepoprawne dane";
            }
        }
        
        for (int i = 0; i < l; i++) {
            if (FigureType[i] == "Niepoprawne dane") { System.out.println(FigureType[i]); }
            else { PrintValues(i + 1,FigureType[i],LengthTab[i],SurfaceTab[i]); }

        }
    }
}
