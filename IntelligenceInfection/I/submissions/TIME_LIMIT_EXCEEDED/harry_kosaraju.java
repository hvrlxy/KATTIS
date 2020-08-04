// @EXPECTED_RESULTS@: WRONG-ANSWER,TIMELIMIT,RUN-ERROR
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class harry_kosaraju {

	static int S, B, E; 
	static ArrayList<Integer> adj[];
	static ArrayList<Integer> adj_rev[];
	static Boolean[] baddies;
	static Boolean[] visited; 
	static Stack<Integer> stack = new Stack<Integer>();
	static ArrayList<ArrayList<Integer>> comps;
	static ArrayList<ArrayList<Integer>> adj_comp;
	static int[] indeg;
	static int[] node_to_comp;
	static int[] bbng;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.nextInt();
		B = sc.nextInt();
		E = sc.nextInt();
		
		adj = (ArrayList<Integer>[]) new ArrayList[S];
		adj_rev = (ArrayList<Integer>[]) new ArrayList[S];
		baddies = new Boolean[S];
		
		for(int i = 0; i < S; i++)
		{
			adj[i] = new ArrayList<Integer>();
			adj_rev[i] = new ArrayList<Integer>();
			baddies[i] = false;
		}
		
		
		for(int i = 0; i < E; i++)
		{
			int C1 = sc.nextInt();
			int C2 = sc.nextInt();
			adj[C1].add(C2);
			adj_rev[C2].add(C1);
		}
		
		
		
		for(int i = 0; i < B; i++)
		{
			int bad = sc.nextInt();
			baddies[bad] = true;
		}
		
		stack = new Stack<Integer>();
		visited = new Boolean[S];
		
		for(int i = 0; i < S; i++)
			visited[i] = false;
		
		for(int i = 0; i < S; i++)
			dfs(i);
		
		for(int i = 0; i < S; i++)
			visited[i] = false;
		
		comps = new ArrayList<ArrayList<Integer>>();
		adj_comp = new ArrayList<ArrayList<Integer>>();
		node_to_comp = new int[S];
		
		int index = -1;
		while(!stack.isEmpty())
		{
			int node = stack.pop();
			if(!visited[node])
			{
				index++;
				comps.add(new ArrayList<Integer>());
				comps.get(index).add(node);
				node_to_comp[node] = index;

				adj_comp.add(new ArrayList<Integer>());
				
				dfs_rev(node, index);			
			}
		}
		
		int nComps = comps.size();
		indeg = new int[nComps];
		bbng = new int[nComps]; // This will determine whether a SCC contains a bad spy.
		int indegzero = 0;
		
		for(int i = 0; i < nComps; i++)
		{
			ArrayList<Integer> list = adj_comp.get(i);
			for(int j: list)
				indeg[j]++;
		}
		
		
		for(int i = 0; i < nComps; i++)
		{
			bbng[i] = -1; // -1 = unvisited, 0 = good, 1 = bad
			ArrayList<Integer> comp = comps.get(i);
			for(int j: comp)
				if(baddies[j])
					bbng[i] = 1;
		}
		
		for(int i = 0; i < nComps; i++)
			if(bbng[i] == -1)
				find_baddies(i);
		
		for(int i = 0; i < nComps; i++)
		{
			if(bbng[i] == 1)
			{
				ArrayList<Integer> adjs = adj_comp.get(i);
				for(int j: adjs)
					indeg[j]--;
			
			}
			if(indeg[i] == 0 && bbng[i] == 0)
				indegzero++;
		}
		
		int total = indegzero;
		for(int i = 0; i < nComps; i++)
			if(bbng[i] == 1)
				total += comps.get(i).size();
		
		total -= B;
		
		System.out.println(total);

	}
	
	static void dfs(int node)
	{
		if(visited[node]) return;
		visited[node] = true;
		
		for(int i: adj[node]) 
			if(!visited[i]) 
			{
				dfs(i);
				visited[i] = true;
			}
		
		stack.push(node);		
	}
	
	static void dfs_rev(int node, int index)
	{
		if(visited[node]) return;
		visited[node] = true;
		
		for(int i: adj_rev[node])
		{
			if(!visited[i])
			{
				node_to_comp[i] = index;
				comps.get(index).add(node_to_comp[i]);
				dfs_rev(i, index);
			}
			else {	adj_comp.get(node_to_comp[i]).add(index);	}
		}
	}

	static void find_baddies(int comp)
	{
		for(int i: adj_comp.get(comp))
		{
			if(bbng[i] == -1)
				find_baddies(i);
			if(bbng[i] == 1)
				bbng[comp] = 1;
		}
		if(bbng[comp] == -1) bbng[comp] = 0;
	}
}
