#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

// m = number of equations, n = number of variables,
// a[m][n+1] = coefficients matrix
// Returns vector<double> ans containing the solution, if there is no
// solution returns vector<double>()

const double eps = 1e-7;

bool zero(double a){return (a<eps) && (a>-eps);}

vector<double> solve(vector<vector<double > > a, int m, int n)
{
    int cur=0;
    for (int i=0;i<n;++i){
        for (int j=cur;j<m;++j)
            if (!zero(a[j][i])){
                if (j!=cur) swap(a[j],a[cur]);
                for (int sat=0;sat<m;++sat){
                    if (sat==cur) continue;
                    double num=a[sat][i]/a[cur][i];
                    for (int sot=0;sot<=n;++sot)
                        a[sat][sot]-=a[cur][sot]*num;
                }
                cur++;
                break;
            }
    }
    for (int j=cur;j<m;++j)
        if (!zero(a[j][n]))
            return vector<double>();
    vector<double> ans(n);
    for (int i=0,sat=0;i<n;++i){
        ans[i] = 0;
        if (sat<m && !zero(a[sat][i])){
            ans[i] = a[sat][n] / a[sat][i];
            sat++;
        }
    }
    return ans;
}

int main()
{
	int tn;
	for (cin >> tn; tn--;)
	{
		int n;
		cin >> n;
		vector<double> f(n);
		for (int i = 0; i < n; i++)
			cin >> f[i];
		
		double next = -1;
		bool found = false;
		for (int d = 1; d <= 3 && !found; d++)
		{
			vector<vector<double> > c(n - d, vector<double>(d + 1));
			for (int i = d; i < n; i++)
				for (int j = 0; j <= d; j++)
					c[i - d][d - j] = f[i - j];
			
			vector<double> a = solve(c, n - d, d);
			if (a.size() > 0)
			{
				next = 0;
				for (int i = 0; i < d; i++)
					next += a[i] * f[n - d + i];
				found = true;
			}
		}
		cout << (int)round(next) << endl;
		
	}
	return 0;
}
