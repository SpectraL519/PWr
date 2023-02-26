#pragma once

#include <string>



class PascalsTriangleRow {
    private:
        int *Row;
        int size;
        int Newton (int n, int k);

    public:
        PascalsTriangleRow (int n);
        ~PascalsTriangleRow ();
        int Factor (int k);
};

std::string convertToString (char* a);