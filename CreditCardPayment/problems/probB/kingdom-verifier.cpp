#include <cstdio>
#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include <cassert>
#include <cstdlib>
#include <stack>

using namespace std;


ifstream judgein("kingdom.in");
ifstream teamout("kingdom.out");
ofstream vfyfile("kingdom.vfy");

int n, m;
vector<vector<bool> > adjmat;

void wrong_answer(const char *err) 
{
  vfyfile << err << endl;
  vfyfile << "WRONG OUTPUT" << endl;
  exit(1);
}

void read_input() //I should write this with cstdio
{
  assert(judgein >> n >> m);
  adjmat = vector<vector<bool> >(n, vector<bool>(n, false));
  for (int i = 0; i < m; i++)
    {	
      int u, v;
      assert(judgein >> u >> v);
      adjmat[u - 1][v - 1] = adjmat[v - 1][u - 1] = true;
    }
}

typedef int Edge;
struct Graph {
  vector<vector<Edge> > nbr;
  int num_nodes;
  Graph(int n) : nbr(n), num_nodes(n) { }
  
  // No check for duplicate edges!
  // Add (or remove) any parameters that matter for your problem
  void add_edge_directed(int u, int v) {
    Edge e = v;    nbr[u].push_back(e);
  }
};

const int MAX_N = 2000;
int po[MAX_N*2],comp[MAX_N*2],num_scc,dfn;

void DFS(const Graph& G,int v,stack<int>& P,stack<int>& S){
  po[v] = dfn++;
  S.push(v);  P.push(v);
  for(size_t i=0;i<G.nbr[v].size();i++){
    int w = G.nbr[v][i];
    if(po[w] == -1) DFS(G,w,P,S);
    else if(comp[w] == -1)
      while(!P.empty() && (po[P.top()] > po[w]))
        P.pop();
  }
  if(!P.empty() && P.top() == v){
    while(!S.empty()){
      int t = S.top();       S.pop();
      comp[t] = num_scc;
      if(t == v) break;
    }
    P.pop();   num_scc++;
  }
}

int SCC(const Graph& G){
  num_scc = dfn = 0;
  stack<int> P,S;
  fill(po,po+G.num_nodes,-1);
  fill(comp,comp+G.num_nodes,-1);
  for(int i=0;i<G.num_nodes;i++)
    if(po[i] == -1) DFS(G,i,P,S);
  return num_scc;
}

int VAR(int i) { return 2*i; }
int NOT(int i) { return i ^ 1; }

void add_clause(Graph &G, int v, int w) { // adds (v || w)
  if (v == NOT(w)) return;
  G.add_edge_directed(NOT(v), w);
  G.add_edge_directed(NOT(w), v);
}

bool twoSAT(const Graph &G, bool val[]) {   // assumes graph is built
  SCC(G);
  for (int i = 0; i < G.num_nodes; i += 2) {
    if (comp[i] == comp[i+1]) return false;
    val[i/2] = (comp[i] < comp[i+1]);
  }
  return true;
}

bool possible()
{
  Graph G(2*n);
  add_clause(G, VAR(0), VAR(0));
  add_clause(G, VAR(1), VAR(1));

  vector<vector<bool> > &orig = adjmat;
  
  for (int i = 2; i < n; i++) {
    bool ci0 = orig[i][0], ci1 = orig[i][1];
    assert(!(ci0 && ci1));   // can't be connected to both

    if (!ci0 && !ci1) {
      // not connected to either one, so it must be in the remainder
      add_clause(G, NOT(VAR(i)), NOT(VAR(i)));
    }
    for (int j = i+1; j < n; j++) {
      bool cj0 = orig[j][0], cj1 = orig[j][1];
      assert(!(cj0 && cj1));   // can't be connected to both

      if (!orig[i][j]) {
        // they are not connected

        // at least one of them must be given (or the remainder will not
        // be connected
        add_clause(G, VAR(i), VAR(j));

        if ((ci0 && cj0) || (ci1 && cj1)) {
          // if they are both connected to 0 (or 1), then they cannot be both
          // given
          add_clause(G, NOT(VAR(i)), NOT(VAR(j)));
        }
      }
    }
  }
  bool val[2*MAX_N];
  return twoSAT(G, val);
}

int check_output(istream &in) 
{
  string line[2];

  if (!getline(in, line[0])) {
    vfyfile << "failed to read the 1st line" << endl;
    return -1;
  }
	
  if (line[0] == "impossible")
    return 0;
  else {
    if (!getline(in, line[1])) {
      vfyfile << "failed to read the 2nd line" << endl;
      return -2;
    }
		
    vector<vector<int> > clique(3, vector<int>());
    vector<bool> notInC3(n, false);
    for (int i = 0; i < 2; i++) {
      int itmp;
      istringstream sin(line[i]);
      while (sin >> itmp) {
	notInC3[itmp - 1] = true;
	clique[i].push_back(itmp - 1);
      }
    }
		
    for (int i = 0; i < n; i++)
      if (!notInC3[i])
	clique[2].push_back(i);
    
    for (int i = 0; i < 3; i++)
      for (size_t j = 0; j < clique[i].size(); j++)
	for (size_t k = j + 1; k < clique[i].size(); k++)
	  if (!adjmat[clique[i][j]][clique[i][k]]) {
	    vfyfile << "Vertices " << j + 1 << " and " << k + 1 << " are not connected in cluster" << i + 1 << endl;
	    return -1;
	  }
  }
  return 1;
}

void check_case() 
{	
  read_input();

  int team_status = check_output(teamout);
  if (team_status < 0) 
    wrong_answer("Wrong Answer: error in team answer file!");

  bool p = possible();
  if (!team_status && p)
    wrong_answer("Team claims impossible, while it is possible!");

  if (!p && team_status) 
    vfyfile << "Judge claims impossible, while it is possible!";

  // team_sol = judge_sol, all is well...
  vfyfile << "CORRECT!" << endl;
  return;
}

const char *USAGE = "Usage: %s\n";

int main(int argc, char **argv)
{
  if (argc != 1)
    {
      fprintf(stderr, USAGE, argv[0]);
      exit(1);
    }
	
  check_case();

  string buf;
  if (teamout >> buf)
    wrong_answer("Trailing output");
   
  exit(0);
}

