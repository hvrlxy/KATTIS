#include <iostream>
#include <vector>
#include <utility>

using namespace std;

typedef vector<int> vi;

vector<vi> adj_list;
vector<int> incoming_edge_counts;
vector<bool> dirty;
vector<bool> visited;

unsigned long long send_count = 0;

void dfs(int u) {
    if (visited[u] || dirty[u])
        return;

    visited[u] = true;
    for (int i = 0; i < adj_list[u].size(); i++) {
        int index = adj_list[u][i];
        if (!visited[index]) {
            dfs(index);

            if (dirty[index]) {

                dirty[u] = true;
                send_count++;

                for (int j = 0; j < adj_list[u].size(); j++) {
                    int child_index = adj_list[u][j];
                    incoming_edge_counts[child_index]--;
                }
                break;
            }
        }
    }
}

int main() {

    int S, E, C;

    cin >> S >> E >> C;

    adj_list.assign(S, vi());
    incoming_edge_counts.assign(S, 0);
    dirty.assign(S, false);
    visited.assign(S, false);

    for (int i = 0; i < C; i++) {
        int from, to;
        cin >> from >> to;

        adj_list[from].push_back(to);
        incoming_edge_counts[to]++;
    }

    for (int i = 0; i < E; i++) {
        int e;
        cin >> e;
        dirty[e] = true;

        for (int j = 0; j < adj_list[e].size(); j++) {
            int child_index = adj_list[e][j];
            incoming_edge_counts[child_index]--;
        }
    }


    for (int i = 0; i < S; i++) {
        dfs(i);
    }

    for (int i = 0; i < S; i++)
    {
        if (!dirty[i] && incoming_edge_counts[i] == 0){
            send_count++;
        }
    }

    cout << send_count << endl;

    return 0;
}
