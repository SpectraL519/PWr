public class FigureEnumTest {
    public static void PrintValues (int nr, String figure, double L, double S) {
        System.out.println("Object " + nr + ": " + figure);
        System.out.println("\tLength = " + L);
        System.out.println("\tSurface = " + S);      
    }


    
    public static void main (String args[]) {
        int l = args[0].length();
        int position = 1;

        for (int i = 0; i < l; i++) {
            if (args[0].charAt(i) == 'c') { // circle
                double r = Double.parseDouble(args[position++]);
                PrintValues(i + 1,"circle",FigureEnum.SingleParameterFigure.Circle.SingleLength(r),FigureEnum.SingleParameterFigure.Circle.SingleSurface(r));
            }
            else if (args[0].charAt(i) == 'q') { // quadrangle
                double a = Double.parseDouble(args[position]);
                double phi = Double.parseDouble(args[position + 4]);
                if (phi == 90) {
                    double b = Double.parseDouble(args[position + 1]);
                    if (a != b) { // rectangle
                        PrintValues(i + 1,"rectangle",FigureEnum.MultiParameterFigue.Rectangle.MultiLength(a,b),FigureEnum.MultiParameterFigue.Rectangle.MultiSurface(a,b));
                    }
                    else {
                        b = Double.parseDouble(args[position + 2]);
                        if (a != b) { // rectangle
                            PrintValues(i + 1,"rectangle",FigureEnum.MultiParameterFigue.Rectangle.MultiLength(a,b),FigureEnum.MultiParameterFigue.Rectangle.MultiSurface(a,b));
                        }
                        else { // square
                            PrintValues(i + 1,"square",FigureEnum.SingleParameterFigure.Square.SingleLength(a),FigureEnum.SingleParameterFigure.Square.SingleSurface(a));
                        }
                    }        
                }
                else { // rhomb
                    PrintValues(i + 1,"rhomb",FigureEnum.MultiParameterFigue.Rhomb.MultiLength(a,phi),FigureEnum.MultiParameterFigue.Rhomb.MultiSurface(a,phi));
                }
                position += 5;
            }
            else if (args[0].charAt(i) == 'p') { // pentagon
                double a = Double.parseDouble(args[position++]);
                PrintValues(i + 1,"pentagon",FigureEnum.SingleParameterFigure.Pentagon.SingleLength(a),FigureEnum.SingleParameterFigure.Pentagon.SingleSurface(a));
            }
            else if (args[0].charAt(i) == 'h') { // hexagon
                double a = Double.parseDouble(args[position++]);
                Figure h = new Hexagon(a);
                h.PrintValues(i + 1,"hexagon",FigureEnum.SingleParameterFigure.Hexagon.SingleLength(a),FigureEnum.SingleParameterFigure.Hexagon.SingleSurface(a));
            }
            else { 
                System.out.println("Niepoprawne dane");
                break;
            }
        }
    }
}
