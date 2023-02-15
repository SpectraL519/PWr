#include <iostream>
#include <cmath>

#include "declarations.hpp"



std::string convertToString(char* a)
{
    std::string s(a);
    return s;
}

double pi = 2 * acos(0.0);



void Figure::PrintValues (int nr, std::string figure, double L, double S) {
    std::cout << "Object " << nr << ": " << figure << std::endl;
    std::cout << "\tLength = " << L << std::endl;
    std::cout << "\tSurface = " << S << std::endl;
}



Square::Square (double a) {
    this->a = a;
}



double Square::Length () {
    return 4 * this->a;
}



double Square::Surface () {
    return this->a * this->a;
}



Rectangle::Rectangle (double a, double b) {
    this->a = a;
    this->b = b;
}



double Rectangle::Length () {
    return 2 * (this->a + this->b);
}



double Rectangle::Surface () {
    return this->a * this->b;
}



Rhomb::Rhomb (double a, double phi) {
    this->a = a;
    this->phi = phi;
}



double Rhomb::Length () {
    return 4 * this->a;
}



double Rhomb::Surface () {
    return this->a * this->a * sin(this->phi * pi / 180);
}



Circle::Circle (double r) {
    this->r = r;
}



double Circle::Length () {
    return 2 * pi * this->r;
}



double Circle::Surface () {
    return pi * this->r * this->r;
}



Pentagon::Pentagon (double a) {
    this->a = a;
}



double Pentagon::Length () {
    return 5 * this->a;
}



double Pentagon::Surface () {
    return 5 * this->a * this->a * sqrt(3) / 4;
}



Hexagon::Hexagon (double a) {
    this->a = a;
}



double Hexagon::Length () {
    return 6 * this->a;
}



double Hexagon::Surface () {
    return 3 * this->a * this->a * sqrt(3) / 2;
}