#include <string>
#include <cmath>

#include "declarations.hpp"



bool PrimeNumbers::prime (int n) {
    double max = sqrt(n);
    for (int i = 2; i <= max; i++) {
        if (n % i == 0) return false;
    }
    
    return true;
}



PrimeNumbers::PrimeNumbers (int n) {
    primeSet = new int[n];
}



PrimeNumbers::~PrimeNumbers () {
    delete[] primeSet;
}



void PrimeNumbers::fillSet (int n) {
    position = 0;
    for (int i = 2; i < n; i++) {
        if (prime(i))   primeSet[position++] = i;
    }
}



int PrimeNumbers::number (int m) {
    if (m < position)   return primeSet[m];

    return 0;
}



std::string convertToString(char* a) {
    std::string s(a);
    return s;
}