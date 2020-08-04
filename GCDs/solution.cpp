#include <cstdio>
#include <cmath>
using namespace std;

int table[101][101];
bool seen[101];

int gcd(int a, int b){
	if (a == 0) { return b; }
	else { return gcd(b % a, a); }
}

int main(){
	int val;
	scanf("%d\n", &val);

	int array[val];

	for (int i = 0; i < val; i++){
		scanf("%d\n", &array[i]);
	}

	for (int i = 0; i < 101; i++){
		seen[i] = false;
	}

	for (int i = 0; i < 101; i++){
		for (int j = i; j < 101; j++){
			table[i][j] = gcd(i,j);
			table[j][i] = table[i][j];
		}
	}

	int count;
	count = 0;

	for (int i = 0; i < val; i ++){
		int dp[val];
		for (int j = 0; j < val; j++){
			if (i == j){dp[j] = array[j];}
			else{
				dp[j] = table[dp[j - 1]][array[j]];
			}

			if (seen[dp[j]] == false){
				seen[dp[j]] = true;
				count ++;
			}
		}
	}

	printf("%d\n", count);
}