#pragma once

#include <string>



class PrimeNumbers {
    private:
        int *primeSet;
        int position;
    
        bool prime (int n);

    public:
        PrimeNumbers (int n);
        ~PrimeNumbers ();
        
        void fillSet (int n);
    
        int number (int m);
};

std::string convertToString(char* a);