#include "declarations.hpp"

#include <algorithm>
#include <string>



PascalsTriangleRow::PascalsTriangleRow (int n) {
    size = n + 1;
    Row = new int[size];
    for (int i = 0; i < size / 2; i++) {
        int factor = Newton(n,i); 
        Row[i] = factor;
        Row[n - i] = factor;
    }
    if (size % 2 != 0) {
        Row[(size / 2)] = Newton(n,size / 2); 
    }
}



PascalsTriangleRow::~PascalsTriangleRow () {
    delete[] Row;
}



int PascalsTriangleRow::Newton (int n, int k) {
    if (n * k != 0 && n != k) {
        int outcome = 1;
        int max = std::max(k,n - k);
        for (int i = n; i > max; i--) { 
            outcome *= i;
            outcome /= (n - i + 1); 
        }

        return outcome;
    }

    return 1;
}



int PascalsTriangleRow::Factor (int k) {
    if (k >= 0 && k < size) { return Row[k]; }

    return -1;
}



std::string convertToString (char* a) {
    std::string s(a);
    return s;
}