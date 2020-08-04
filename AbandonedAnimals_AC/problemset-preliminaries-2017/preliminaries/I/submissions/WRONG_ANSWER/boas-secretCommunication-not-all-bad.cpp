// Solution to the secret communication problem
// By Boas Kluiving
//
// This solution first calculates all nodes that can reach 'enemy' nodes, 
// marking them dirty. For each dirty node, send_count is incremented.
// These nodes, including 'enemy' nodes are then removed from the graph.
// On the remaining graph, the strongly connected components are determined
// using Tarjan's algorithm and for all components that have no incoming edges
// outside the component itself, the send_count is incremented.
#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <iterator>
#include <stack>

using namespace std;

typedef vector<int> vi;

vector<vi> adj_list_to;
vector<vi> adj_list_from;
vector<bool> dirty;
vector<bool> visited;

vector<int> index;
vector<int> lowlink;
int current_index = 1;
stack<int> s;
vector<bool> on_stack;

int S, E, C;
unsigned long long send_count = 0;

void dfs(int u) {
    if (visited[u] || dirty[u])
        return;

    visited[u] = true;
    for (int i = 0; i < adj_list_to[u].size(); i++) {
        int index = adj_list_to[u][i];
        if (!visited[index]) {
            dfs(index);

            if (dirty[index]) {
                dirty[u] = true;
                send_count++;
                break;
            }
        }
    }
}

void strongly_connected(int v) {

    index[v] = current_index;
    lowlink[v] = current_index;
    current_index++;
    s.push(v);
    on_stack[v] = true;

    for (int i = 0; i < adj_list_to[v].size(); i++) {
        int w = adj_list_to[v][i];
        
        if (!index[w]) {
            strongly_connected(w);
            lowlink[v] = min(lowlink[v], lowlink[w]);
        } else if(on_stack[w]) {
            lowlink[v] = min(lowlink[v], index[w]);
        }
    }

    if(lowlink[v] == index[v]) {
        int w;
        vi items_in_comp;
        do {
            w = s.top();
            s.pop();
            on_stack[w] = false;
            items_in_comp.push_back(w);
        } while(w != v);

        vector<bool> from_edges;
        from_edges.assign(S, false);

        int incoming_edges = 0;

        for(int j = 0; j < items_in_comp.size(); j++) {
            int node = items_in_comp[j];
            for(int k = 0; k < adj_list_from[node].size(); k++) {
                int parent = adj_list_from[node][k];
                if(!from_edges[parent]) {
                    incoming_edges++;
                }
                from_edges[parent] = true;
            }
        }


        for(int j = 0; j < items_in_comp.size(); j++) {
            int node = items_in_comp[j];
            if(from_edges[node]) {
                incoming_edges--;
            }
            from_edges[node] = false;
        }


        
        if(incoming_edges == 0) {
            send_count++;
        }

        /*cout << "component:";

        for(int j = 0; j < items_in_comp.size(); j++) {
            cout << " " << items_in_comp[j];
        }
            
        cout << endl;*/
    }

}

int main() {


    cin >> S >> E >> C;

    adj_list_to.assign(S, vi());
    adj_list_from.assign(S, vi());
    dirty.assign(S, false);
    visited.assign(S, false);

    index.assign(S, 0);
    lowlink.assign(S, 0);
    on_stack.assign(S, false);


    for (int i = 0; i < C; i++) {
        int from, to;
        cin >> from >> to;

        adj_list_to[from].push_back(to);
        adj_list_from[to].push_back(from);

    }

    for (int i = 0; i < E; i++) {
        int e;
        cin >> e;
        dirty[e] = true;
    }


    for (int i = 0; i < S; i++) {
        dfs(i);
    }

    // remove all dirty nodes
    for (int i = 0; i < S; i++) {
        
        if(!dirty[i]) {
            continue;
        }

        for (int j = 0; j < adj_list_from[i].size(); j++) {
                int parent = adj_list_from[i][j];

                // remove the connection in adj_list_from
                adj_list_to[parent].erase(remove(adj_list_to[parent].begin(), adj_list_to[parent].end(), i), adj_list_to[parent].end());
        }


        for (int j = 0; j < adj_list_to[i].size(); j++) {
                int child_index = adj_list_to[i][j];

                // remove the connection in adj_list_from
                adj_list_from[child_index].erase(remove(adj_list_from[child_index].begin(), adj_list_from[child_index].end(), i), adj_list_from[child_index].end());
        }
    }

    for (int i = 0; i < S; i++) {
        if (!dirty[i] && !index[i]) {
            strongly_connected(i);
        }
    }

    /*for (int i = 0; i < S; i++)
    {
        if (!dirty[i] && adj_list_from[i].size() == 0){
            send_count++;
        }
    }*/

    cout << send_count << endl;

    return 0;
}
