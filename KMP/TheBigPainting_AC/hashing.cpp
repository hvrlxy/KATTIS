#include <cstdio>

#define MAXN 4005
#define MAXP 4005

#define PRIME0 2147483647

#define BASE 2

using namespace std;

int a, b, h, w;

unsigned long long phash;
char pattern[MAXP][MAXP];
char text[MAXN][MAXN];

unsigned long long M[MAXN][MAXN]; // M[i][j] = Hash of [T[i, j], ..., T[i+a, j]]]
unsigned long long H[MAXN][MAXN]; // Hash of pattern starting at this position

long long expsimp[MAXP];
long long expcomp[MAXP];

void calcM() {
  for (int j = 0; j < w; j++) {
    long long hash = 0;
    for (int i = 0; i < a; i++)
      hash = (hash + ((text[i][j]*expsimp[a-i-1]) % PRIME0)) % PRIME0;
    M[0][j] = hash;

    for (int i = a; i < h; i++) {
      // subtract top number
      hash = (hash + PRIME0 - ((text[i-a][j]*expsimp[a-1]) % PRIME0)) % PRIME0;
      // shift hash
      hash = (hash*BASE)%PRIME0;
      // add new number
      hash = (hash+text[i][j]) % PRIME0;
      M[i-a+1][j] = hash;
    }
  }
}

void calcH() {
  // build H[k][0]
  for (int i = 0; i < h-a+1; i++) {
    long long hash = 0;
    for (int j = 0; j < b; j++)
      hash = (hash+(M[i][j]*expcomp[b-j-1] % PRIME0)) % PRIME0;
    H[i][0] = hash;
  }
  
  for (int j = 1; j < w-b+1; j++)
    for (int i = 0; i < h-a+1; i++) {
      H[i][j] = (H[i][j-1] + PRIME0 - (M[i][j-1]*expcomp[b-1] % PRIME0)) % PRIME0;
      H[i][j] = (H[i][j] * expcomp[1]) % PRIME0;
      H[i][j] = (H[i][j] + M[i][j+b-1]) % PRIME0;
    }
}

int main() {
  scanf("%d %d %d %d", &a, &b, &h, &w);
  for (int i = 0; i < a; i++) 
    scanf("%s", pattern[i]);
  for (int i = 0; i < h; i++) 
    scanf("%s", text[i]);

  // convert pattern and text to numbers
  for (int i = 0; i<a; i++)
    for  (int j = 0; j<b; j++)
      pattern[i][j] = (pattern[i][j] == 'x'?1:(pattern[i][j] == 'o'?0:'.'));
  for (int i = 0; i<h; i++)
    for  (int j = 0; j<w; j++)
      text[i][j] = (text[i][j] == 'x'?1:(text[i][j] == 'o'?0:'.'));

  // pre-calculate "BASE^0, ..., BASE^a" mod PRIME0
  expsimp[0] = 1;
  for (int i = 1; i<=a; i++)
    expsimp[i] = (expsimp[i-1]*BASE) % PRIME0;

  // pre-calculate "BASE^a, BASE^2a, ..., BASE^ba" mod PRIME0
  expcomp[0] = 1;
  for (int i = 1; i<=b; i++)
    expcomp[i] = (expcomp[i-1]*expsimp[a]) % PRIME0;
  
  // calc pattern hash
  for (int j = 0; j < b; j++)
    for (int i = 0; i < a; i++) 
      phash = ((phash*BASE % PRIME0) + pattern[i][j]) % PRIME0;
  //printf("PHASH: %lld\n", phash);

  // calc hash of every "size b column"
  calcM();

  // find pattern in text
  calcH();

  /*
  puts("M:");
  for  (int i = 0; i<h; i++) {
    for (int j = 0; j<w; j++)
      printf("%llu ", M[i][j]);
    putchar('\n');
  }

  puts("H:");
  for  (int i = 0; i<h; i++) {
    for (int j = 0; j<w; j++)
      printf("%llu ", H[i][j]);
    putchar('\n');
  }
  */

  int cnt = 0;
  for (int i = 0; i < h; i++) 
    for (int j = 0; j < w; j++)
      if (H[i][j] == phash) 
	cnt++;
  //printf("%d %d\n", i+1, j+1);
  printf("%d\n", cnt);
  return 0;
}
