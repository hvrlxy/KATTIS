#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int main(){
    string input;
    cin >> input;
    int t = 0;
    int g = 0;
    int c = 0;

    for (int i = 0; i < input.size(); i++){
        if (input[i] == 'T') {t++;}
        else if (input[i] == 'G'){g++;}
        else {c++;}
    }
    int result = t * t + g * g + c * c + 7 * fmin(fmin(c,g),t);
    cout << result << endl;
}
