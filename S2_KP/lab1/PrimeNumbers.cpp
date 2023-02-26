#include <iostream>
#include <string>

#include "declarations.hpp"



int main (int argc, char* argv[]) {
    try {
        int n = std::stoi(convertToString(argv[1]));
        if (n > 1) {
            PrimeNumbers *P = new PrimeNumbers(n);
            P->fillSet(n);
            for (int i = 2; i < argc; i++) {
                try {
                    int m = std::stoi(convertToString(argv[i]));
                    if (m >= 0 && P->number(m) != 0) std::cout << m << " - " << P->number(m) << std::endl;
                    else    std::cout << m << " - Number out of range\n";
                }
                catch (std::exception &err) {
                    std::cout << argv[i] << " - Incorrect data\n";
                }
            }
            delete P;
        }
        else    std::cout << n << " - Incorrect range\n";
    }
    catch (std::exception &err) {
        std::cout << argv[1] << " - Incorrect data\n";
    }

    return 0;
}