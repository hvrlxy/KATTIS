#include <cstdio>
#include <cmath>
using namespace std;
int dp[20000][20000];
int a[20000];
int prefixSum[20000];
int d[151][501][101][51];

int roundToTen(int &m){
    if (m % 10 < 5){
        return m - m%10;
    }
    else{
        return m + (10 - m% 10);
    }
}

int main(){
    int n,d;
    scanf("%d %d\n", &n, &d);
    for (int i = 0; i < n; i++){
        scanf("%d", &a[i]);
    }

    for (int i = 0; i < n; i++){
        if (i == 0) prefixSum[i] = a[0];
        else{
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }
    }


    for (int i = 0; i < n; i++){
        for (int j = 0; j <= d; j++){
            if (i == 0){
                dp[i][j] = a[i];
            }
            else if (j == 0){
                dp[i][j] = prefixSum[i];
            }
            else{
                dp[i][j] = fmin(roundToTen(dp[i - 1][j - 1]) + a[i], dp[i - 1][j] + a[i]);
            }
        }
    }
   
    printf("%d\n", roundToTen(dp[n - 1][d]));
}
