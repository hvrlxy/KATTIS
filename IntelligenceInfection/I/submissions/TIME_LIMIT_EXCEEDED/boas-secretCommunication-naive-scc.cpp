// Solution to the secret communication problem
// This solution finds the connect components in a naive way (n^2)
// EXPECTED RESULT: TIME LIMIT EXCEEDED
// By Boas Kluiving
//
//
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <iterator>
#include <stack>

using namespace std;

typedef vector<int> vi;
typedef vector<vector<int>> vvi;

vector<vi> adj_list;
vector<bool> scc_dirty;

int scc_index = 0;
vvi scc_adj_list;
vvi scc_adj_list_from;
vector<int> scc_for_node;
vector<int> scc_size;

vector<bool> visited;
vector<vector<bool>> reachable;

int S, E, C;
long long send_count = 0;

void dfs(int u) {
    if (scc_dirty[u])
        return;

    for(int index: scc_adj_list[u]) {
        dfs(index);

        if (scc_dirty[index]) {
            scc_dirty[u] = true;
            break;
        }
    }
}

void dfs2(int node, int origin) {
    if(visited[node])
        return;

    visited[node] = true;
    reachable[node][origin] = true;
    for(int i = 0; i < adj_list[node].size(); i++) {
        int child = adj_list[node][i];
        if(!visited[child]) {
            dfs2(child, origin);
        }
    }
}

void check_incoming_edges(vector<int> items_in_comp) {
    for(auto item : items_in_comp) {
        scc_for_node[item] = scc_index;
    }
    scc_size.push_back(items_in_comp.size());
    scc_index++;
}

void strongly_connected(void) {

    for(int i = 0; i < S; i++) {
        visited.assign(S, false);
        dfs2(i,i); 
    }


    vector<bool> is_in_scc;
    is_in_scc.assign(S,false);

    for(int i = 0; i < S; i++) {
        if(is_in_scc[i])
            continue;

        vector<int> current_scc;

        for(int j = 0; j < S; j++) {
            if(reachable[i][j] && reachable[j][i]) {
                is_in_scc[j] = true;
                current_scc.push_back(j);
            }
        }

        check_incoming_edges(current_scc);
    }
}


void create_scc_graph() {
    for (int u = 0; u < S; u++) {
        vector<int> already_added(S, false);
        for (int j = 0; j < adj_list[u].size(); j++) {
            int v = adj_list[u][j];
            int u_scc = scc_for_node[u];
            int v_scc = scc_for_node[v];

            if(u_scc != v_scc && !already_added[v_scc] && !scc_dirty[u_scc] && !scc_dirty[v_scc]) {
                scc_adj_list[u_scc].push_back(v_scc); 
                scc_adj_list_from[v_scc].push_back(u_scc);
                already_added[v_scc] = true;
            }
        }
    }
}

int main() {


    cin >> S >> E >> C;

    adj_list.assign(S, vi());
    scc_for_node.assign(S, -1);

    reachable.resize(S);
    for (int i =0; i < S; i++) {
        reachable[i].resize(S);
        for(int j = 0; j < S; j++) {
            reachable[i][j] = false;
       }
    }

    for (int i = 0; i < C; i++) {
        int from, to;
        cin >> from >> to;

        adj_list[from].push_back(to);
    }

    vector<int> enemies;

    for (int i = 0; i < E; i++) {
        int e;
        cin >> e;
        enemies.push_back(e);
    }


    strongly_connected();

    scc_adj_list.assign(scc_index, vi());
    scc_adj_list_from.assign(scc_index, vi());
    scc_dirty.assign(scc_index, false);
    
    create_scc_graph();

    for(int e : enemies) {
        int scc = scc_for_node[e];
        scc_dirty[scc] = true;
    }

    for(int i = 0; i < scc_index; i++) {
        dfs(i);
    }

    for(int i = 0; i < scc_index; i++) {
        if(scc_dirty[i]) {       
            send_count += scc_size[i];
        }
    }
    send_count -= E;

    for(int v = 0; v < scc_index; v++) {
        if(scc_dirty[v]) {
            continue;
        }
        bool has_incoming = false;
        for(int u : scc_adj_list_from[v]) {
            if(!scc_dirty[u]) {
                has_incoming = true;
            }
        }
        if(!has_incoming) {
            send_count++;
        }

    }



    cout << send_count << endl;

    return 0;
}
