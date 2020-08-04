#include <iostream>
#include <fstream>
#include <algorithm>
using namespace std;

int main() {
    int N = 5000, M = 10000, k = 100;
    ofstream out;
    out.open("02.in");
    out << N << " " << M << endl;
    for (int i = 0; i < N; ++i) {
	for (int j = 0; j < k; ++j)
	    out << (char)('a'+rand()%('z'-'a'));
	out << endl;
    }
    for (int i = 0; i < M; ++i) {
	for (int j = 0; j < 3; ++j)
	    out << (char)('A'+rand()%('Z'-'A'+1));
	out << endl;
    }
    out.close();

    out.open("03.in");
    out << N << " " << M << endl;
    for (int i = 0; i < N; ++i) {
	if (i==N/2) {
	    for (int j = 0; j < k/2; ++j)
		out << (char)('k'+rand()%('t'-'k'));
	    for (int j = 0; j < k/2; ++j)
		out << (char)('a'+rand()%('k'-'a'));
	    out << endl;
	    continue;
	}
	for (int j = 0; j < k/2-1; ++j)
	    out << (char)('a'+rand()%('o'-'a'));
	if (i==N/2+1)
	    out << 'z';
	for (int j = 0; j < k/2-1; ++j)
	    out << (char)('o'+rand()%('t'-'o'));
	out << endl;
    }
    for (int i = 0; i < M; ++i) {
	for (int j = 0; j < 3; ++j)
	    out << (char)('A'+rand()%('Z'-'A'+1));
	out << endl;
    }
    out.close();
}

