#include <iostream>
#include <vector>
#include <string>

using namespace std;

const int max_colors = 6;

int n, seen;
int dir[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
vector<string> board;
vector<vector<bool> > mark;

void floodfill(char c, int i, int j)
{
	if (i < 0 || i >= n || j < 0 || j >= n)
		return;
	if (board[i][j] != c)
		return;
	if (mark[i][j])
		return;
	
	seen++;
	mark[i][j] = true;
	for (int k = 0; k < 4; k++)
		floodfill(c, i + dir[k][0], j + dir[k][1]);
	return;
}

int main()
{
	int tn;
	for (cin >> tn; tn--;)
	{
		cin >> n;
		
		board = vector<string>(n);
		for (int i = 0; i < n; i++)
			cin >> board[i];
		
		vector<vector<bool> > connected(n, vector<bool>(n, false));
		seen = 0;
		mark = vector<vector<bool> >(n, vector<bool>(n, false));
		floodfill(board[0][0], 0, 0);
		connected = mark;
		
		int totalMoves = 0;
		vector<int> moves(max_colors, 0);
		int maxConnected = seen;
		while (maxConnected < n * n)
		{
			maxConnected = 0;
			int maxdex = -1;
			vector<vector<bool> > maxMark;
			for (int k = 1; k <= max_colors; k++)
			{
				for (int i = 0; i < board.size(); i++)
					for (int j = 0; j < board[i].length(); j++)
						if (connected[i][j])
							board[i][j] = '0' + k;
				
				seen = 0;
				mark = vector<vector<bool> >(n, vector<bool>(n, false));
				floodfill('0' + k, 0, 0);
				if (seen > maxConnected)
				{
					maxConnected = seen;
					maxdex = k;
					maxMark = mark;
				}
			}
			totalMoves++;
			moves[maxdex - 1]++;
			connected = maxMark;
		}
		
		cout << totalMoves << endl;
		for (int i = 0; i < max_colors; i++)
		{
			if (i > 0)
				cout << ' ';
			cout << moves[i];
		}
		cout << endl;
	}
	
	return 0;
}
