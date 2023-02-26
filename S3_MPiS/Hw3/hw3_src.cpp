#include <fstream>
#include <random>
#include <ctime>



// Generating random byte sequence (exercise 1)
int main (int argc, char* argv[]) {
    std::ofstream file("cpp_bytes.txt");
    
    srand(time(NULL));
    for (int i = 0; i < 2 * 10e6; i++) {
        file << rand() % 2;
    }

    return 0;
}