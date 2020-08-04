#include <cmath>
#include <algorithm>
using namespace std;

int TMAX = 10005;
int DMAX = 1005;

double dp[1005][10005];
bool roof[1005];
double rain[10005];

int main(){
	int d,t,c,r;
	scanf("%d %d %d %d\n", &d, &t, &c, &r);

	for (int i = 0; i <= r + 1; i ++){
		rain[i] = 0.0;
	}

	for (int i = 0; i <= DMAX; i ++){
		roof[i] = false;
	}

	for (int i = 1; i < DMAX; i++){
		for (int j = 0; j < TMAX; j++){
			dp[i][j] = 1000000000000.0;
		}
	}
	for (int i = 0; i < c; i++){
		int s,e,a;
		double p;
		scanf("%d %d %lf %d\n", &s, &e, &p, &a);
		rain[s] += p * a;
		rain[e] -= p * a;
	}

	for (int i = 1; i <= t; i ++){
		rain[i] += rain[i - 1];
	}

	for (int i = 0; i < r; i++){
		int s,e;
		scanf("%d %d\n", &s, &e);
		for (int j = s; j < e; j++){
			roof[j] = true;
		}
	}

	for (int i = 0; i <= t; i++){
		dp[0][i] = 0.0;
	}

	for (int i = 1; i <= d; i++){
		for (int j = i; j <= t; j++){
			if (!roof[i] && !roof[i - 1]){
				dp[i][j] = dp[i][j - 1] + rain[j - 1];
			}
			else{
				dp[i][j] = dp[i][j - 1];
			}

			if (roof[i - 1]){
				dp[i][j] = min(dp[i][j], dp[i - 1][j - 1]);
			}
			else{
				dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + rain[j - 1]);
			}

		}
	}

	printf("%lf\n", dp[d][t]);
}