import java.util.Scanner
import java.io.*
import kotlin.math.min

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val adj = Array<MutableList<Int>>(sc.nextInt()){mutableListOf<Int>()}
	val tunnels = sc.nextInt()

	for (i in 0 until tunnels){
		val a = sc.nextInt()
		val b = sc.nextInt()
		adj[a].add(b)
		adj[b].add(a)
	}

	val indices = IntArray(adj.size){-1}
	val stack = mutableListOf<Int>()
	var c = adj.size


	// create another parameter u which is the parent of the vertex v
	fun dfs(v: Int, u: Int){
		stack.add(v)
		indices[v] = stack.size - 1
		for (w in adj[v]){
			if (indices[w] == -1){
				dfs(w, v)
			}

			if (w != u){
				// check whether w is not the parent of v, if it is, then don't update the indices
				indices[v] = min(indices[w], indices[v])
			}
		}

		//println(stack)

		if (stack[indices[v]] == v){
			c ++
			while (indices[v] < stack.size){
				indices[stack[stack.size - 1]] = c
				stack.removeAt(stack.size - 1)
			}
		}
	}

	dfs(0, -1)

	var result = 1

	for (i in 1 until indices.size){
		if (indices[i] == indices[0]){
			result ++
		}
	}

	println(result)
}