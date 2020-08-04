#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main()
{
  srand(time(0));
  
  for (int n = 1; n <= 5000; n++) {
    cout << rand() << endl;
  }
  cout << 0 << endl;
  return 0;
}
