// Solution to the secret communication problem
// By Boas Kluiving
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

vector<int> index;
vector<int> lowlink;
int current_index = 1;
stack<int> s;
vector<bool> on_stack;

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

void strongly_connected(int v) {

    index[v] = current_index;
    lowlink[v] = current_index;
    current_index++;
    s.push(v);
    on_stack[v] = true;

    for (int i = 0; i < adj_list[v].size(); i++) {
        int w = adj_list[v][i];
        
        if (!index[w]) {
            strongly_connected(w);
            lowlink[v] = min(lowlink[v], lowlink[w]);
        } else if(on_stack[w]) {
            lowlink[v] = min(lowlink[v], index[w]);
        }
    }

    if(lowlink[v] == index[v]) {
        int w;

        vector<int> items_in_scc;

        do {
            w = s.top();
            s.pop();
            on_stack[w] = false;

            items_in_scc.push_back(w);
            scc_for_node[w] = scc_index;

        } while(w != v);

        scc_size.push_back(items_in_scc.size());
        scc_index++;
        /*cout << "component:";

        for(int j = 0; j < items_in_scc.size(); j++) {
            cout << " " << items_in_scc[j];
        }
            
        cout << endl;*/
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

    index.assign(S, 0);
    lowlink.assign(S, 0);
    on_stack.assign(S, false);


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


    for (int i = 0; i < S; i++) {
        if (!index[i]) {
            strongly_connected(i);
        }
    }

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
