#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<long long> R;
vector<long long> K;

void choose(long long u, int h)
{
	long long l = u * (long long)1 << h;
	long long r = (u * (long long)1 << h) + ((long long)1 << h) - 1;
	
	bool found = false;
	for (int i = 0; i < R.size() && !found; i++)
		if (l <= R[i] && R[i] <= r)
			found = true;
	
	if (!found)
		K.push_back(u);
	else if (l < r)
	{
		choose(2 * u, h - 1);
		choose(2 * u + 1, h - 1);
	}
}

int main()
{
	int n, r;
	cin >> n >> r;
	R = vector<long long>(r, -1);
	for (int i = 0; i < r; i++)
		cin >> R[i];
		
	K = vector<long long>();
	choose(1, n);
	sort(K.begin(), K.end());
	
	for (int i = 0; i < K.size(); i++)
	{
		if (i > 0)
			cout << ' ';
		cout << K[i];
	}
	cout << endl;	
	return 0;
}
