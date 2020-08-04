#include <bits/stdc++.h>
using namespace std;
using vb = vector<bool>;
using ii = pair<int, int>;
using vi = vector<int>;
using vvi = vector<vi>;

struct Tarjan {
	vvi &edges;
	int V, counter = 0, C = 0;
	vi n, l;
	vb vs;
	stack<int> st;

	Tarjan(vvi &e) : edges(e), V(e.size()),
		n(V, -1), l(V, -1), vs(V, false) { }

	void visit(int u, vi &com) {
		l[u] = n[u] = counter++;
		st.push(u); vs[u] = true;
		for (auto &&v : edges[u]) {
			if (n[v] == -1) visit(v, com);
			if (vs[v]) l[u] = min(l[u], l[v]);
		}
		if (l[u] == n[u]) {
			while (true) {
				int v = st.top(); st.pop(); vs[v] = false;
				com[v] = C;		//<== ACT HERE
				if (u == v) break;
			}
			C++;
		}
	}

	int find_sccs(vi &com) { // component indices will be stored in 'com'
		com.assign(V, -1);
		C = 0;
		for (int u = 0; u < V; ++u)
			if (n[u] == -1) visit(u, com);
		return C;
	}

	// scc is a map of the original vertices of the graph
	// to the vertices of the SCC graph, scc_graph is its
	// adjacency list.
	// Scc indices and edges are stored in 'scc' and 'scc_graph'.
	void scc_collapse(vi &scc, vvi &scc_graph) {
		find_sccs(scc);
		scc_graph.assign(C,vi());
		set<ii> rec; // recorded edges
		for (int u = 0; u < V; ++u) {
			assert(scc[u] != -1);
			for (int v : edges[u]) {
				if (scc[v] == scc[u] ||
					rec.find({scc[u], scc[v]}) != rec.end()) continue;
				scc_graph[scc[u]].push_back(scc[v]);
				rec.insert({scc[u], scc[v]});
			}
		}
	}
};

void dfsbad(int u, vvi &e, vb &bad) {
	for (int v : e[u]) if (!bad[v]) bad[v] = true, dfsbad(v, e, bad);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int S, E, C;
	cin >> S >> E >> C;

	vvi e(S, vi());
	while (C--) {
		int u, v;
		cin >> u >> v;
		e[u].push_back(v);
	}

	vi scc;
	vvi sccg;
	Tarjan(e).scc_collapse(scc, sccg);
	int G = (int)sccg.size();

	vb bad(G, false);
	for (int i = 0; i < E; ++i) {
		int u;
		cin >> u;
		bad[scc[u]] = true;
	}

	// Invert the SCC graph to propagate badiness.
	vvi rsccg(G, vi());
	for (int u = 0; u < G; ++u) for (int v : sccg[u]) rsccg[v].push_back(u);
	for (int u = 0; u < G; ++u) if (bad[u]) dfsbad(u, rsccg, bad);

	int messages = -E;
	vb seen(G, false);
	for (int u = 0; u < S; ++u) {
		if (bad[scc[u]]) ++messages, seen[scc[u]] = true;
		if (!seen[scc[u]]) {
			int indeg = 0;
			for (int v : rsccg[scc[u]]) if (!bad[v]) ++indeg;
			if (indeg == 0) ++messages;
			seen[scc[u]] = true;
		}
	}

	cout << messages << endl;
	return 0;
}
