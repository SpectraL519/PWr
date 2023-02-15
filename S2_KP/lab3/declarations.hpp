#pragma once



std::string convertToString(char* a);



class Figure {
    public:
        virtual double Length() = 0;
        virtual double Surface() = 0;
        void PrintValues(int nr, std::string figure, double L, double S);
};



class Quadrangle: public Figure {};



class Square: public Quadrangle {
    private:
        double a;
    
    public:
        Square (double a);
        double Length();
        double Surface();
};



class Rectangle: public Quadrangle {
    private:
        double a, b;
    
    public:
        Rectangle (double a, double b);
        double Length();
        double Surface();
};



class Rhomb: public Quadrangle {
    private:
        double a, phi;

    public:
        Rhomb (double a, double phi);
        double Length();
        double Surface();
};



class Circle: public Figure {
    private:
        double r;

    public:
        Circle (double r);
        double Length();
        double Surface();
};



class Pentagon: public Figure {
    private:
        double a;

    public:
        Pentagon (double a);
        double Length();
        double Surface();
};



class Hexagon: public Figure {
    private:
        double a;

    public:
        Hexagon (double a);
        double Length();
        double Surface();
};