#include <cstdio>
#include <fstream>
#include <vector>
#include <random>
#include <chrono>
#include <cassert>





class GraphExplorer {
    private:
        int K;

        std::vector <int> N;
        std::vector <int> V0;
        std::vector <int**> G_matrix;
        std::vector <std::vector <int>*> G_list;
        std::vector <long long*> times;

        std::string graph;
        std::mt19937 generator;
        std::uniform_int_distribution <int> uniform;


        std::vector <int>* getListRepresentation (int index) {
            int n = this->N[index];
            std::vector <int>* neighbors = new std::vector <int> [n];
            for (int v = 0; v < n; v++) {
                for (int u = 0; u < n; u++) {
                    if (this->G_matrix[index][v][u] == 1) {
                        neighbors[v].push_back(u);
                    }
                }
            }

            return neighbors;
        }


        void getData () {
            try {
                printf("Reading data...\n");
                std::ifstream infile;
                infile.open("matrices/" + this->graph + ".csv");

                int n, v0;
                int i = 0;
                while (infile >> n >> v0) {
                    this->N.push_back(n);
                    this->V0.push_back(v0);

                    int** g = new int*[n];
                    for (int i = 0; i < n; i++) {
                        g[i] = new int[n];
                    }

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            infile >> g[i][j];
                        }
                    }

                    this->G_matrix.push_back(g);
                    this->times.push_back(new long long[this->K]);

                    this->G_list.push_back(this->getListRepresentation(i));
                    i++;
                }

                // TODO: getNeighbors

                printf("Success!\n");
            }
            catch (std::exception &e) {
                printf("Error: Reading from %s.csv file!\n", this->graph);
                std::exit(1);
            }
        }


        void exportResult () {
            try {
                printf("Eksporting results...");
                std::ofstream outfile;
                outfile.open("results/" + this->graph + ".csv");

                for (auto &n : this->N) {
                    outfile << "," << n;
                }
                outfile << "\n";

                int row = 0;
                for (int i = 0; i < this->K; i++) {
                    outfile << row;
                    for (auto &time : this->times) {
                        outfile << "," << time[i];
                    }
                    outfile << "\n";
                    row++;
                }

                printf("Success!");
            }
            catch (std::exception &e) {
                printf("Error: Exporting to %s_result.csv file!\n", this->graph);
                std::exit(1);
            }
        }



    public:
        GraphExplorer (std::string graph) {
            this->graph = graph;

            if (this->graph == "lollipop")
                this->K = 50;
            else
                this->K = 100;

            unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
            this->generator.seed(seed);
        }

        ~GraphExplorer () {
            for (auto &time : this->times) {
                delete time;
            }

            for (int i = 0; i < this->N.size(); i++) {
                for (int r = 0; r < this->N[i]; r++) {
                    delete this->G_matrix[i][r];
                }
                delete this->G_matrix[i];
            }

            for (int i = 0; i < this->N.size(); i++) {
                delete this->G_list[i];
            }
        }


        void run () {
            this->getData();

            printf("Processing... (k = %d)\n", this->K);
            int n_size = this->N.size();
            for (int n = 0; n < n_size; n++) {
                printf("%d:", this->N[n]);
                this->uniform.param(std::uniform_int_distribution<int>::param_type(0, this->N[n] - 1));
                
                for (int k = 0; k < this->K; k++) {
                    printf(" %d", k);
                    bool visited[this->N[n]] = {false};
                    int n_visited = 0;
                    long long time = 0;
                    int prev = this->V0[n];

                    while (n_visited < this->N[n]) {

                        int curr = this->G_list[n][prev][this->uniform(this->generator) % this->G_list[n][prev].size()];
                        if (!visited[curr]) {
                            visited[curr] = true;
                            n_visited++;
                        }
                        prev = curr;
                        time++;
                    }

                    this->times[n][k] = time;
                }
                printf("\n");
            }

            this->exportResult();
        }
};





int main (int argc, char* argv[]) {
    if (argc < 2) {
        printf("Error: Invalid arguments\n");
        return 1;
    }

    std::string graph = argv[1];
    GraphExplorer explorer(graph); 
    explorer.run();

    return 0;
}