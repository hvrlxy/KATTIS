#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>

using namespace std;

// 2-Sat Solver ----------------------------------------------------------------
// Set 'n' and use add edge
// Vertices are numbered 0..n-1 for true states.
// False state of the variable i is i+n, which is exactly other(i)
// 0<='val[i]'<=1 is the value for binary variable i in 2-Sat
// 0<='group[i]'<2*n is scc number of vertex i. 

const int maxn = 2000;

int n;
vector<int> adj[maxn*2];
vector<int> adjrev[maxn*2];
int val[maxn];

int marker,dfst,dfstime[maxn*2],dfsorder[maxn*2];
int group[maxn*2];

inline int other(int v){return v<n?v+n:v-n;}
inline int var(int v){return v<n?v:v-n;}
inline int type(int v){return v<n?1:0;}

void satclear() 
{
	for(int i=0;i<maxn+maxn;i++) {
		adj[i].resize(0);
		adjrev[i].resize(0);
	}
}

void dfs(int v)
{
	if(dfstime[v]!=-1)
		return;
	dfstime[v]=-2;
	int deg = adjrev[v].size();
	for(int i=0;i<deg;i++)
		dfs(adjrev[v][i]);
	dfstime[v] = dfst++;
}

void dfsn(int v) 
{
	if(group[v]!=-1)
		return;
	group[v]=marker;
	int deg=adj[v].size();
	for(int i=0;i<deg;i++)
		dfsn(adj[v][i]);
}

void addedge(int a,int b) 
{
	adj[other(a)].push_back(b);
	adjrev[a].push_back(other(b));
	adj[other(b)].push_back(a);
	adjrev[b].push_back(other(a));
}

int solve() 
{
	dfst=0;
	memset(dfstime,-1,sizeof dfstime);
	for(int i=0;i<n+n;i++)
		dfs(i);
	memset(val,-1,sizeof val);
	for(int i=0;i<n+n;i++)
		dfsorder[n+n-dfstime[i]-1]=i;
	memset(group,-1,sizeof group);
	for(int i=0;i<n+n;i++) {
		marker=i;
		dfsn(dfsorder[i]);
	}

	for(int i=0;i<n;i++) {
		if(group[i]==group[i+n])
			return 0;
		val[i]=(group[i]>group[i+n])?0:1;
	}

	return 1;
}
// End of 2-SAT solver ---------------------------------------------------------


int main()
{
	int m;
	while (scanf("%d %d", &n, &m) != EOF)
	{
		vector<vector<bool> > adjmatrix(n, vector<bool>(n, false));
		for (int i = 0; i < m; i++)
		{
			int u, v;
			scanf("%d %d", &u, &v);
			adjmatrix[u - 1][v - 1] = adjmatrix[v - 1][u - 1] = true;
		}
				
		//colors: clique 1-> 0, clique 2 -> 1, clique 3 -> 2, clique 1 or 3 -> 3, clique 2 or 3 -> 4
		vector<int> color(n, 2); 
		for (int i = 0; i < 2; i++)
		{
			color[i] = i;
			for (int j = 0; j < n; j++)
				if (adjmatrix[i][j])
					color[j] = 3 + i;
		}
		
		// False = either color 0 or color 1, True = color 2
		bool possible = true;
		for (int i = 2; i < n && possible; i++)
			for (int j = i + 1; j < n && possible; j++)
				if (!adjmatrix[i][j])
					if (color[i] == 2 && color[j] == 2) // impossible
						possible = false;
					else if (color[i] == 2) // var(j) must be false
						addedge(other(j), other(j));
					else if (color[j] == 2) // var(i) must be false
						addedge(other(i), other(i));
					else if (color[i] != color[j]) // i and j cannot both have color 2
						addedge(other(i), other(j)); 
					else // must have different colors
					{	
						addedge(i, j);
						addedge(other(i), other(j)); 
					}
			
		if (possible && solve())
		{
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < n; j++)
					if (adjmatrix[i][j])
						if (val[j])
							color[j] = 2;
						else
							color[j] = i;
		}
		else
			possible = false;
		
		if (possible)
			for (int i = 0; i < 2; i++)
			{
				bool first = true;
				for (int j = 0; j < n; j++)
					if (color[j] == i)
					{
						if (!first)
							printf(" ");
						printf("%d", j + 1);
						first = false;
					}
//				if (i == 0)
					printf("\n");
			}
		else
			printf("impossible\n");
	}
	
	return 0;
}
