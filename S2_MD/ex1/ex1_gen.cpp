#include <iostream>
#include <cassert>
using namespace std;





void print_set (int *set, int size) {
    cout << "{";
    for (int i = 0; i < size - 1; i++) {
        cout << set[i] << ",";
    }
    cout << set[size - 1] << "}" << endl;
}



int* next_tuple (int n, int k, int *L) {
    if (L[0] == n - k + 1)    return NULL;
    else {
        if (L[k - 1] == n) {
            int tmp;
            for (int i = k - 1; i >= 0; i--) {
                if (L[i] + 1 < L[i + 1]) {
                    L[i]++;
                    tmp = i + 1;
                    break;
                }
            }
            for (int i = tmp; i < k; i++) L[i] = L[i - 1] + 1;
        }
        else {
            for (int i = k - 1; i >= 0; i--) {
                if (L[i] + 1 < L[i + 1]) {
                    L[i]++;
                    break;
                }
            }
        }
    }
    return L;
}



void gen_tuples (int n, int k, int *L) {
    cout << "\n1. ";
    print_set(L,k);
    int lp = 2;
    while (L[0] != n - k + 1) {
        cout << lp++ << ". ";
        print_set(next_tuple(n,k,L),k);
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
    
    int L[k + 1];
    for (int i = 0; i < k; i++) L[i] = i + 1;
    L[k] = n + 1;

    gen_tuples(n,k,L);

    return 0;
}