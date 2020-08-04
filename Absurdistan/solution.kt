import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numRoads = sc.nextInt()

	val adj = MutableList<MutableList<Int>>(numRoads + 1){mutableListOf<Int>()}
	val array = BooleanArray(numRoads + 1){false}

	for (i in 0 until numRoads){
		val a = sc.nextInt()
		val b = sc.nextInt()

		adj[a].add(b)
		adj[b].add(a)
	}

	val Q = mutableListOf<Int>()
	for (v in 0 until adj.size){
		if (adj[v].size == 1) {
			Q.add(v)
		}
	}

	while (Q.isNotEmpty()){
		val v = Q[0]
		val w = adj[v][0]
		println("$v $w")
		array[v] = true
		Q.removeAt(0)
		adj[w].remove(v)
		adj[v].remove(w)
		if (adj[w].size == 1) Q.add(w)
	}
	
	fun dfs(v: Int){
		if (array[v] == true) return
		array[v] = true
		val w = adj[v][0]
		println("$v $w")
		adj[v].remove(w)
		adj[w].remove(v)
		dfs(w)
	}

	for (i in 1 until adj.size){
		if (array[i] == false) dfs(i)
	}
}