#include <iostream>
#include <cstdlib>
using namespace std;

const int MAXC = 30;
const int MAXI = 100;

int cstack[MAXI];
int top;

int main()
{
    int n, p, m;
    cin >> n >>p;
    cstack[0] = 0;
    top = 0;
    for(int i=0; i<p; i++) {
        string s;
        cin >> s;
        if (s == "undo") {
            cin >> m;
            top -= m;
        }
        else {
            m = atoi(s.c_str());
            cstack[top+1] = (cstack[top]+m) % n;
            top++;
        }
    }
    if (cstack[top] < 0)
        cstack[top] += n;
    cout << cstack[top] << endl;
}
