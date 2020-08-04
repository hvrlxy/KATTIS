#include <algorithm>
#include <cassert>
#include <iostream>
#include <set>
#include <stack>
#include <vector>
using namespace std;

struct Tarjan {
	vector<vector<int>> &edges;
	int V, counter = 0, C = 0;
	vector<int> n, l;
	vector<bool> vs;
	stack<int> st;

	Tarjan(vector<vector<int>> &e) : edges(e), V(e.size()), n(V, -1), l(V, -1), vs(V, false) {}

	void visit(int u, vector<int> &com) {
		l[u] = n[u] = counter++;
		st.push(u);
		vs[u] = true;
		for(auto &&v : edges[u]) {
			if(n[v] == -1) visit(v, com);
			if(vs[v]) l[u] = min(l[u], l[v]);
		}
		if(l[u] == n[u]) {
			while(true) {
				int v = st.top();
				st.pop();
				vs[v]  = false;
				com[v] = C; //<== ACT HERE
				if(u == v) break;
			}
			C++;
		}
	}

	int find_sccs(vector<int> &com) { // component indices will be stored in 'com'
		com.assign(V, -1);
		C = 0;
		for(int u = 0; u < V; ++u)
			if(n[u] == -1) visit(u, com);
		return C;
	}

	// scc is a map of the original vertices of the graph
	// to the vertices of the SCC graph, scc_graph is its
	// adjacency list.
	// Scc indices and edges are stored in 'scc' and 'scc_graph'.
	void scc_collapse(vector<int> &scc, vector<vector<int>> &scc_graph) {
		find_sccs(scc);
		scc_graph.assign(C, vector<int>());
		set<pair<int, int>> rec; // recorded edges
		for(int u = 0; u < V; ++u) {
			assert(scc[u] != -1);
			for(int v : edges[u]) {
				if(scc[v] == scc[u] || rec.find({scc[u], scc[v]}) != rec.end()) continue;
				scc_graph[scc[u]].push_back(scc[v]);
				rec.insert({scc[u], scc[v]});
			}
		}
	}
};

int main() {
	int s, e, c;
	cin >> s >> e >> c;
	vector<vector<int>> g(s), sccg;
	while(c--) {
		int u, v;
		cin >> u >> v;
		g[v].push_back(u);
	}
	vector<int> spies(e); // a stack of bad spies
	for(auto &x : spies) cin >> x;
	vector<int> scc;
	Tarjan(g).scc_collapse(scc, sccg);
	// for(int i = 0; i < scc.size(); ++i) cerr << "scc " << i << " : " << scc[i] << endl;
	// for(int i = 0; i < sccg.size(); ++i) {
	// cerr << i << ":";
	// for(auto x : sccg[i]) cerr << ' ' << x;
	// cerr << endl;
	//}
	vector<int> sizes(sccg.size());
	for(auto x : scc) ++sizes[x];
	vector<bool> bad(sccg.size(), false); // bad components
	for(auto &x : spies) x = scc[x];
	int messages           = -e; // first count # private messages needed
	// cerr << "start messages at " << messages << endl;
	while(!spies.empty()) {
		int u = spies.back();
		spies.pop_back();
		if(bad[u]) continue;
		bad[u] = true;
		//++messages;
		messages += sizes[u];
		// cerr << u << " is bad; increment messages to " << messages << endl;
		for(auto v : sccg[u]) spies.push_back(v);
	}
	vector<int> scc_size(sccg.size());
	for(auto x : scc) ++scc_size[x];
	for(int u = 0; u < sccg.size(); ++u)
		if(!bad[u] && all_of(sccg[u].begin(), sccg[u].end(), [&](int v) { return bad[v]; }))
			//++messages, cerr << u << " is good and no bad outgoing edges\n";
			++messages;
	cout << messages << endl;
	return 0;
}
