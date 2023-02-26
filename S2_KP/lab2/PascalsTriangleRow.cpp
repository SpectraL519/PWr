#include <iostream>
#include <string>

#include "declarations.hpp"



int main (int argc, char* argv[]) {
    try {
        int n = std::stoi(convertToString(argv[1]));
        if (n > 1) {
            PascalsTriangleRow *R = new PascalsTriangleRow(n);
            for (int i = 2; i < argc; i++) {
                try {
                    int k = std::stoi(convertToString(argv[i]));
                    if (R->Factor(k) == -1) std::cout << k << " - Liczba spoza zakresu\n";
                    else    std::cout << k << " - " << R->Factor(k) << std::endl;
                }
                catch (std::exception &err) {
                    std::cout << argv[i] << " - Niepoprawne dane\n";
                }
            }
            delete R;
        }
        else    std::cout << n << " - Nieprawidlowy zakres\n";
    }
    catch (std::exception &err) {
        std::cout << argv[1] << " - Niepoprawne dane\n";
    }

    return 0;
}