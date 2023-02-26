#include <iostream>
#include "declarations.hpp"



int main (int argc, char *argv[]) {
    std::string figures = convertToString(argv[1]);
    int l = figures.length();
    int position = 2;

    for (int i = 0; i < l; i++) {
        if (figures[i] == 'c') { // circle
            double r = std::stod(convertToString(argv[position++]));
            Figure *c = new Circle(r);
            c->PrintValues(i + 1,"circle",c->Length(),c->Surface());
            delete c;
        }
        else if (figures[i] == 'q') { // quadrangle
            double a = std::stod(convertToString(argv[position]));
            double phi = std::stod(convertToString(argv[position + 4]));
            if (phi == 90) {
                double b = std::stod(convertToString(argv[position + 1]));
                if (a != b) { // rectangle
                    Figure *r = new Rectangle(a,b);
                    r->PrintValues(i + 1,"rectangle",r->Length(),r->Surface());
                    delete r;
                }
                else {
                    b = std::stod(convertToString(argv[position + 2]));
                    if (a != b) { // rectangle
                        Figure *r = new Rectangle(a,b);
                        r->PrintValues(i + 1,"rectangle",r->Length(),r->Surface());
                        delete r;
                    }
                    else { // square
                        Figure *s = new Square(a);
                        s->PrintValues(i + 1,"square",s->Length(),s->Surface());
                        delete s;
                    }
                }        
            }
            else { // rhomb
                Figure *rh = new Rhomb(a,phi);
                rh->PrintValues(i + 1,"rhomb",rh->Length(),rh->Surface());
                delete rh;
            }
            position += 5;
        }
        else if (figures[i] == 'p') { // pentagon
            double a = std::stod(convertToString(argv[position++]));
            Figure *p = new Pentagon(a);
            p->PrintValues(i + 1,"pentagon",p->Length(),p->Surface());
            delete p;
        }
        else if (figures[i] == 'h') { // hexagon
            double a = std::stod(convertToString(argv[position++]));
            Figure *h = new Hexagon(a);
            h->PrintValues(i + 1,"hexagon",h->Length(),h->Surface());
            delete h;
        }
        else {
            std::cout << "Niepoprawne dane" << std::endl;
            break;
        }
    }

    return 0;
}