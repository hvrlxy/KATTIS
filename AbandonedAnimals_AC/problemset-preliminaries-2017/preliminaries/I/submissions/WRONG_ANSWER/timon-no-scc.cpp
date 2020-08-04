#include <bits/stdc++.h>
using namespace std;
using vb = vector<bool>;
using vi = vector<int>;
using vvi = vector<vi>;

int dfs1(int u, vvi &re, vi &id) {
	int r = 0;
	for (int v : re[u]) {
		if (id[v] < 0) {
			id[v] = 0;
			r += 1 + dfs1(v, re, id);
		}
	}
	return r;
}

int dfs2(int u, vvi &e, vb &active, vi &id) {
	int r = 0;
	for (int v : e[u]) {
		if (id[v] == -1) {
			id[v] = id[u];
			r += dfs2(v, e, active, id);
		} else if (id[v] != id[u] && active[id[v]]) {
			active[id[v]] = false;
			--r;
		}
	}
	return r;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int S, E, C;
	cin >> S >> E >> C;

	vvi e(S, vi()), re(S, vi());
	while (C--) {
		int u, v;
		cin >> u >> v;
		e[u].push_back(v);
		re[v].push_back(u);
	}

	vi id(S, -1);
	while (E--) {
		int u;
		cin >> u;
		id[u] = 0;
	}

	int messages = 0;
	for (int u = 0; u < S; ++u)
		if (id[u] == 0)
			messages += dfs1(u, re, id);

	vb active(1, false);
	for (int u = 0, free = 1; u < S; ++u) {
		if (id[u] >= 0) continue;
		active.push_back(true);
		id[u] = free++;
		messages += 1 + dfs2(u, e, active, id);
	}

	cout << messages << endl;
}
