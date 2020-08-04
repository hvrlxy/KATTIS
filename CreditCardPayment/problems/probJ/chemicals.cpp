#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int const max_start = 1000000000;
int const max_duration = 1000000000;

struct Stream
{
	int s, d, p, next;
	Stream(int s = 0, int d = 0, int p = 0, int next = -1): s(s), d(d), p(p), next(next) {}
	bool operator<(const Stream &rhs) const { return this->s < rhs.s || (this->s == rhs.s && this->d > rhs.d); }
};

vector<Stream> streams;
vector<vector<int> > profit;

int solve(int i, int j)
{
	if (j < i || j >= streams.size() || streams[i].s + streams[i].d < streams[j].s)
		return 0;
	if (profit[i][j] != -1)
		return profit[i][j];
	if (i == j)
		return (profit[i][i] = streams[i].p + solve(i, i + 1));
	if (streams[i].s + streams[i].d < streams[j].s + streams[j].d)
		return (profit[i][j] = solve(i, j + 1));
		
	return (profit[i][j] = max(solve(i, j + 1), solve(j, j) + solve(i, streams[j].next)));
}

int main()
{
	int n;
	while (cin >> n)
	{
		streams = vector<Stream>(n + 1);
		streams[0] = Stream(-1, max_start + max_duration + 2, 0);
		for (int i = 1; i <= n; i++)
			cin >> streams[i].s >> streams[i].d >> streams[i].p;
			
		sort(streams.begin(), streams.end());
				
		for (int i = 0; i < streams.size(); i++)
			for (int j = i + 1; j < streams.size(); j++)
				if (streams[j].s >= streams[i].s + streams[i].d)
				{
					streams[i].next = j;
					break;
				}

//		for (int i = 0; i < streams.size(); i++)
//			cout << streams[i].s << ' ' << streams[i].d << ' ' << streams[i].p << ' ' << streams[i].next << endl;
		
		profit = vector<vector<int> >(n + 1, vector<int>(n + 1, -1));
		cout << solve(0, 0) << endl;
	}
	
	return 0;
}
