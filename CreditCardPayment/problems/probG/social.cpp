#include <iostream>
#include <vector>
#include <bitset>

using namespace std;

const int max_n = 20;

int main()
{
	int tn;
	for (cin >> tn; tn--;)
	{
		int n;
		cin >> n;
		vector<bitset<max_n> > adj(n);
		for (int i = 0; i < n; i++)
		{
			int d;
			for (cin >> d; d--;)
			{
				int v;
				cin >> v;
				adj[i].set(v - 1);
			}
			adj[i].set(i);
		}
		
		int best = n;
		for (int i = 0; i < (1 << n); i++)
		{
			int nb = 0;
			bitset<max_n> covered;
			for (int j = 0; j < n; j++)
				if (i & (1 << j))
					covered |= adj[j], nb++;
			if (covered.count() == n && best > nb)
				best = nb;
		}
		cout << best << endl;
	}
	return 0;
}
