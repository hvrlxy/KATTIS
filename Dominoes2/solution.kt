import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCases = sc.nextInt()

	for (i in 0 until numCases){
		val adj = Array<MutableList<Int>>(sc.nextInt() + 1){mutableListOf<Int>()}

		val numEdges = sc.nextInt()
		val numSources = sc.nextInt()

		for (j in 0 until numEdges){
			adj[sc.nextInt()].add(sc.nextInt())
		}

		val disc = IntArray(adj.size){-1}

		fun dfs(s: Int){
			if (disc[s] == -1){
				disc[s] = 1
			}

			for (j in adj[s]){
				if (disc[j] == -1){
					dfs(j)
				}
			}
		}

		for (j in 0 until numSources){
			dfs(sc.nextInt())
		}

		var result = 0
		for (j in 0 until disc.size){
			if (disc[j] == 1){
				result ++
			}
		}

		println(result)
	}
}