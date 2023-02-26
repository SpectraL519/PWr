public class FigureEnum {
    interface SingleParameterFigureValues {
        double SingleLength (double parameter);
        double SingleSurface (double parameter);
    }

    

    interface MultiParameterFigureValues {
        double MultiLength (double parameter1, double parameter2);
        double MultiSurface (double parameter1, double parameter2);
    }



    public enum SingleParameterFigure implements SingleParameterFigureValues {
        Circle {
            public double SingleLength (double r) {
                return 2 * Math.PI * r;
            }
            public double SingleSurface (double r) {
                return Math.PI * r * r;
            }
        },

        Square {
            public double SingleLength (double a) {
                return 4 * a;
            }
            public double SingleSurface (double a) {
                return a * a;
            }
        },

        Pentagon {
            public double SingleLength (double a) {
                return 5 * a;
            }
            public double SingleSurface (double a) {
                return 5 * a * a * Math.sqrt(3) / 4;
            }
        },

        Hexagon {
            public double SingleLength (double a) {
                return 6 * a;
            }
            public double SingleSurface (double a) {
                return 6 * a * a * Math.sqrt(3) / 2;
            }
        };
    }



    public enum MultiParameterFigue implements MultiParameterFigureValues {
        Rectangle {
            public double MultiLength (double a, double b) {
                return 2 * (a + b);
            }
            public double MultiSurface (double a, double b) {
                return a * b;
            }
        },

        Rhomb {
            public double MultiLength (double a, double phi) {
                return 4 * a;
            }
            public double MultiSurface (double a, double phi) {
                return a * a * Math.sin(phi * Math.PI / 180);
            }
        }
    }
}