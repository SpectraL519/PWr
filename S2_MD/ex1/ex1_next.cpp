#include <iostream>
#include <cassert>
#include <algorithm>
using namespace std;





void print_set (int *set, int size) {
    if (set == NULL)    cout << "Given element is the last in the set M(n,k)" << endl;
    else {
        cout << "The next element from the set M(n,k) is {";
        for (int i = 0; i < size - 1; i++) {
            cout << set[i] << ",";
        }
        cout << set[size - 1] << "}" << endl;
    }
}



int* next_tuple (int n, int k, int *L) {
    if (L[0] == n - k + 1)    return NULL;
    else {
        if (L[k - 1] == n) {
            int tmp;
            for (int i = k - 1; i >= 0; i--) { // O(k)
                if (L[i] + 1 < L[i + 1]) {
                    L[i]++;
                    tmp = i + 1;
                    break;
                }
            }
            for (int i = tmp; i < k; i++) L[i] = L[i - 1] + 1; // O(k)
        }
        else {
            for (int i = k - 1; i >= 0; i--) { // O(k)
                if (L[i] + 1 < L[i + 1]) {
                    L[i]++;
                    break;
                }
            }
        }
    }
    return L; // O(k)
}



int main() {
    int n, k;
    cout << "Enter n: "; 
    cin >> n;
    assert(n > 0);
    cout << "Enter k: ";
    cin >> k;
    assert(k > 0 && k <= n);
    cout << "Enter an element from the set M(n,k): ";
    int L[k + 1];
    for (int i = 0; i < k; i++) cin >> L[i];
    L[k] = n + 1;

    sort(L,L + k + 1);
    print_set(next_tuple(n,k,L),k);

    return 0;
}