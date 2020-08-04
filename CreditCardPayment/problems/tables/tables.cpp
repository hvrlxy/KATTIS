#include <iostream>
#include <vector>
#include <string>

using namespace std;

int const maxn = 9;

int main()
{
	int m;
	while (cin >> m, m)
	{
		vector<vector<string> > layout(m, vector<string>(maxn, "#####"));
		for (int i = 0; i < m; i++)
		{
			int N, j = 0;
			for (cin >> N; N--;)
			{
				while (layout[i][j] != "#####")
					j++;
				
				int r, c;
				cin >> r >> c;
				
				layout[i][j] = "--|??";
				layout[i][j][3] = '0' + i + 1;
				layout[i][j][4] = '0' + j + 1;				
				for (int k = 1; k < r; k++)
					layout[i + k][j] = "  |  ";
				for (int l = 1; l < c; l++)
					layout[i][j + l] = "--   ";
				for (int k = 1; k < r; k++)
					for (int l = 1; l < c; l++)
						layout[i + k][j + l] = "     ";
			}
		}
		
		int n;
		for (n = 0; n < maxn && layout[0][n] != "#####"; n++);
		vector<string> table(2 * m + 1, string(3 * n + 1, ' '));
		int offset[5][2] = {{0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}};
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < 5; k++)
					table[i * 2 + offset[k][0]][j * 3 + offset[k][1]] = layout[i][j][k];
		table[2 * m] = table[0];
		for (int j = 0; j <= 2 * m; j++)
			table[j][3 * n] = table[j][0];
		
		for (int i = 0; i < table.size(); i++)
			cout << table[i] << endl;
		cout << endl;
	}
	
	return 0;
}
