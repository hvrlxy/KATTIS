#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <set>
#include <map>
#include <climits>

using namespace std;

int main() {
    int S, E, C; cin >> S >> E >> C;
    vector<bool> enemy(S, false), cont(S, false); 
    vector<int> ncp(S, 0);
    vector<vector<int>> to(S), from(S);
    for(int i = 0; i < C; i++) {
        int a, b;
        cin >> a >> b;
        to[a].push_back(b);
        from[b].push_back(a);
        ncp[b]++;
    }
    queue<int> cq;
    int contsend = 0;
    for(int i = 0; i < E; i++) {
        int a; cin >> a;
        cont[a] = true;
        cq.push(a);
        for(int b : to[a]) {
            ncp[b]--;
        }
    }
    while(!cq.empty()) {
        int cur = cq.front(); cq.pop();
        for(int fr : from[cur]) {
            if(!cont[fr]) {
                cont[fr] = true;
                for(int next : to[fr]) {
                    ncp[next]--;
                }
                cq.push(fr);
                contsend++;
            }
        }
    }
    for(int i = 0; i < S; i++) {
        if(!cont[i] && ncp[i] == 0) {
            contsend++;
        }
    }
    cout << contsend << endl;
    return 0;
}
