#include <iostream>
#include <cassert>
#include <cmath>
using namespace std;





void Init (string *list, int listSize, int setSize) {
    string elementSet = "";
    for (int i = 0; i < setSize; i++) elementSet += "0";
    for (int i = 0; i < listSize; i++) {
        list[i] = elementSet;
        int position = setSize - 1;
        elementSet[position]++;
        while (elementSet[position] > '1') {
            elementSet[position] = '0';
            if (--position >= 0)    elementSet[position]++;
        }
    }
}



int count (string element) {
    int length = element.length();
    int count = 0;
    for (int i = 0; i < length; i++)
        if (element[i] == '1')  count++;
    return count;

    return 0;
}



void printElement (string element) {
    int length = element.length();
    int outcome[length];
    int position = 0;
    for (int i = 0; i < length; i++) {
        if (element[i] == '1')  outcome[position++] = i + 1;
    }
    
    cout << "{";
    for (int i = 0; i < position; i++) {
        if (i == position - 1)  cout << outcome[i];
        else    cout << outcome[i] << ",";
    }
    cout << "}\n";
}



void gen_tuples (int n, int k) {
    cout << "\n[{1,...,n}]^k:\n";

    int listSize = pow(2,n);
    string list[listSize];
    Init(list,listSize,n);
    int lp = 1;
    for (int i = listSize - 1; i >= 0; i--) {
        if (count(list[i]) == k) {
            cout << lp++ << ". ";
            printElement(list[i]);
        }
    }
}



int main() {
    int n, k;
    cout << "Enter n: "; 
    cin >> n;
    assert(n > 0);
    cout << "Enter k: ";
    cin >> k;
    assert(k > 0 && k <= n);
    
    gen_tuples(n,k);

    return 0;
}