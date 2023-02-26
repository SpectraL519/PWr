#include <iostream>
#include <fstream>
#include <chrono>
#include <random>
#include <cassert>

#define RESULT_SIZE 6
#define MAX_URNS 100000
#define N_RERUNS 50





class Experiment {
    private:
        int n_urns;
        int result_size;
        std::mt19937_64 generator;
        std::uniform_int_distribution <int> uniform;


        bool printResultToCSV (std::ofstream &result_file, int result[]) {
            if (!result_file) {
                return false;
            }

            result_file << this->n_urns;
            for (int i = 0; i < RESULT_SIZE; i++) {
                result_file << ',' << result[i];
            }
            result_file << '\n';

            return true;
        }

        void printUrns (int n, int urns[], int ball) {
            std::cout << ball << ": ";
            for (int i = 0; i < n; i++) {
                std::cout << urns[i] << " ";
            }
            std::cout << "\n";
        }
        

    public:
        Experiment (int n_urns) {
            assert(n_urns > 0);
            this->n_urns = n_urns;

            unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
            this->uniform.param(std::uniform_int_distribution<int>::param_type(0, this->n_urns - 1));
            this->generator.seed(seed);
        }

        ~Experiment () {}

        /*
        result:
        0: Bn = first collision
        1: Un = number of empty urns after n balls
        2: Ln = max number of balls in an urn after n balls
        3: Cn = min number of balls after which there is at least 1 ball in every urn
        4: Dn = min number of balls after which there are at least 2 balls in every urn
        5: Dn - Cn = number of balls between Cn and Dn
        */
        void run (std::ofstream &result_file) {
            for (int r = 0; r < N_RERUNS; r++) {
                int result[RESULT_SIZE] = {0};
                int urns[MAX_URNS] = {0};

                int empty_urns = this->n_urns;
                int urns_with_2_balls = 0;
                int max_urn_size = 0;
                
                int ball = 1;

                while (urns_with_2_balls < this->n_urns) {
                    int rand_urn = this->uniform(this->generator) % this->n_urns;
                    urns[rand_urn]++;

                    if (urns[rand_urn] == 1) {
                        empty_urns--;
                    }
                    else if (urns[rand_urn] == 2) {
                        urns_with_2_balls++;
                        if (result[0] == 0) {
                            result[0] = ball;
                        }
                    }

                    if (urns[rand_urn] > max_urn_size) {
                        max_urn_size = urns[rand_urn];
                    }

                    if (ball == this->n_urns) {
                        result[1] = empty_urns;
                        result[2] = max_urn_size;
                    }

                    if (result[3] == 0 && empty_urns == 0) {
                        result[3] = ball;
                    }

                    ball++;
                }

                result[4] = ball - 1;
                result[5] = result[4] - result[3];


                if (!this->printResultToCSV(result_file, result)) {
                    std::cout << "Sth went wrong when trying to write to the 'hw2_result.csv' file!\n";
                }

                std::cout << r << " ";
            }
        }
};





int main (int argc, char* argv[])
{
    // std::ios_base::sync_with_stdio(0);

    std::ofstream result_file;
    try {
        result_file.open("hw2_results.csv");
        result_file << "n_urns,Bn,Un,Ln,Cn,Dn,Dn-Cn\n";
    }
    catch (std::exception &e) {
        std::cout << "Sth went wrong when trying to open the 'hw2_result.csv' file!\n";
    }

    for (int i = 0; i < 100; i++) {
        std::cout << (i + 1) * 1000 << " urns: ";
        Experiment exp = Experiment((i + 1) * 1000);
        exp.run(result_file);
        std::cout << '\n';
    }

    try {
        result_file.close();
    }
    catch (std::exception &e) {
        std::cout << "Sth went wrong when trying to close the 'hw2_result.csv' file!\n";
    }

    return 0;
}