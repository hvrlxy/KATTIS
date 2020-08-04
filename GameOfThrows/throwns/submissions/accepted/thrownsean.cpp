#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>

using namespace std;

int main(){
  int n, k;
  cin >> n >> k;
  int cur_ball = 0;
  vector<int> positions(k);
  int cur_pos = 0;
  for(int i = 0; i < k; i++){
    string cur_throw;
    cin >> cur_throw;
    if(cur_throw == "undo"){
      int next;
      cin >> next;
      cur_pos = cur_pos - next;
      cur_ball = positions[cur_pos-1];
    }
    else{
      int num = atoi(cur_throw.data());
      cur_ball = (cur_ball + num)%n;
      if(cur_ball < 0)
	cur_ball = cur_ball + n;
      positions[cur_pos] = cur_ball;
      cur_pos++;
    }
    //  cout << cur_ball << endl;
  }
  cout << cur_ball << endl;
}
    
