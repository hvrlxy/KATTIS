#include <iostream>
#include <vector>

using namespace std;

int const max_stickers = 100;

int main()
{
	int tn;
	for (cin >> tn; tn--;)
	{
		int n, m;
		cin >> n >> m;
		
		vector<vector<int> > stickers(n);
		vector<int> value(n);
		for (int i = 0; i < n; i++)
		{
			int k;
			cin >> k;
			stickers[i] = vector<int>(k); 
			for (int j = 0; j < k; j++)
				cin >> stickers[i][j];
			cin >> value[i];
		}
		
		vector<int> stickersNum(m);
		for (int i = 0; i < m; i++)
			cin >> stickersNum[i];
			
		int total = 0;
		for (int i = 0; i < n; i++)
		{
			int minnum = max_stickers;
			for (size_t j = 0; j < stickers[i].size(); j++)
				if (minnum > stickersNum[stickers[i][j] - 1])
					minnum = stickersNum[stickers[i][j] - 1];
			total += minnum * value[i];
		}
		cout << total << endl;
	}
	return 0;
}
