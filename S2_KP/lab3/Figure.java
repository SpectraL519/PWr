interface FigureValues {
    double Length();
    double Surface();
}



public abstract class Figure implements FigureValues {
    public void PrintValues (int nr, String figure, double L, double S) {
        System.out.println("Object " + nr + ": " + figure);
        System.out.println("\tLength = " + L);
        System.out.println("\tSurface = " + S);      
    }
}



abstract class Quadrangle extends Figure {}



class Square extends Quadrangle {
    private double a;
    Square (double a) {
        this.a = a;
    }
    @Override 
    public double Length() {
        return 4 * this.a;
    }
    @Override 
    public double Surface() {
        return this.a * this.a;
    }
}



class Rectangle extends Quadrangle {
    private double a, b;
    Rectangle (double a, double b) {
        this.a = a;
        this.b = b;
    }
    @Override 
    public double Length() {
        return 2 * (this.a + this.b);
    }
    @Override 
    public double Surface() {
        return this.a * this.b;
    }
} 



class Rhomb extends Quadrangle {
    private double a, phi;
    Rhomb (double a, double phi) {
        this.a = a;
        this.phi = phi;
    }
    @Override 
    public double Length() {
        return 4 * this.a;
    }
    @Override 
    public double Surface() {
        return this.a * this.a * Math.sin(phi * Math.PI / 180);
    }
}



class Circle extends Figure {
    private double r;
    Circle (double r) {
        this.r = r;
    }
    @Override 
    public double Length() {
        return 2 * Math.PI * this.r;
    }
    @Override 
    public double Surface() {
        return Math.PI * this.r * this.r;
    }
}



class Pentagon extends Figure {
    private double a;
    Pentagon (double a) {
        this.a = a;
    }
    @Override 
    public double Length() {
        return 5 * this.a;
    }
    @Override 
    public double Surface() {
        return 5 * this.a * this.a * Math.sqrt(3) / 4;
    }
}



class Hexagon extends Figure {
    private double a;
    Hexagon (double a) {
        this.a = a;
    }
    @Override 
    public double Length() {
        return 6 * this.a;
    }
    @Override 
    public double Surface() {
        return 3 * this.a * this.a * Math.sqrt(3) / 2;
    }
}