import java.util.Scanner
import java.io.*
import kotlin.math.min

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numVertices = sc.nextInt()
	val numSources = sc.nextInt()
	val numEdges = sc.nextInt()

	val d = IntArray(numVertices){Int.MAX_VALUE}

	for (i in 0 until numSources){
		d[sc.nextInt()] = 0
	}

	val adj = Array<MutableList<Int>>(numVertices){mutableListOf<Int>()}

	for (i in 0 until numEdges){
		val a = sc.nextInt()
		val b = sc.nextInt()
		adj[a].add(b)
		adj[b].add(a)
	}

	val Q = mutableListOf<Int>()
	for (i in 0 until numVertices){
		if (d[i] == 0) Q.add(i)
	}
	while (Q.isNotEmpty()){
		val v = Q[0]
		//println(v)
		Q.removeAt(0)
		for (w in adj[v]){
			if (d[w] > d[v] + 1){
				d[w] = d[v] + 1
				Q.add(w)
			}
		}
	}

	//println(d.joinToString())

	val max = d.max()
	for (i in 0 until numVertices){
		if (d[i] == max) return println(i)
	}
}