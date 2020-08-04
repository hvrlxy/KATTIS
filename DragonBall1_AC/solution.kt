import kotlin.math.*
import java.io.*
import java.util.ArrayDeque
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints


/* Be careful with long */

val INF = 10000000000000000L

data class Edge (val v: Int, val weight: Long) // this one store the edge

data class Vertex (val v: Int, val weight: Long): Comparable<Vertex>{
	// this data structure tells us the min distance of a vertex at current time
	override fun compareTo(other: Vertex) = this.weight.compareTo(other.weight)
}

fun dijkstra (graph: Array<MutableList<Edge>>, s: Int): LongArray{
	val numVertices = graph.size
	val d = LongArray(numVertices){INF} //distance
	val p = IntArray(numVertices){-1} // parents 
	d[s] = 0L
	p[s] = s

	val Q = PriorityQueue<Vertex>()
	Q.add(Vertex(s, 0))

	while (Q.isNotEmpty()){
		val u = Q.poll()

		for (i in graph[u.v]){
			if (u.weight + i.weight < d[i.v]){
				d[i.v] = u.weight + i.weight
				p[i.v] = u.v
				Q.add(Vertex(i.v, d[i.v]))
			}
		}
	}

	return d
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val n = s[0] //cities
    val m = s[1] //roads

    val graph = Array<MutableList<Edge>>(n + 1){mutableListOf<Edge>()}

    repeat(m){
    	val a = rd.readInts()

    	graph[a[0]].add(Edge(a[1], a[2].toLong()))
    	graph[a[1]].add(Edge(a[0], a[2].toLong()))
    }

    val dragonLocations = rd.readInts().toMutableSet().toIntArray()

    val size = dragonLocations.size

    val newGraph = Array<LongArray>(size){LongArray(size){INF}}

    val one = dijkstra(graph, 1)

    for (i in dragonLocations){
    	if (one[i] == INF) return println(-1)
    }

    for (i in 0 until size){
    	val dijkstra = dijkstra(graph, dragonLocations[i])

    	for (j in 0 until size){
    		newGraph[i][j] = dijkstra[dragonLocations[j]]
    	}
    }

    // for (i in 0 until size){
    // 	println(newGraph[i].joinToString())
    // }

    val total = 1 shl size

    val dp = Array<LongArray>(total){LongArray(size){INF}}

    for (i in 0 until size){
    	dp[0][i] = one[dragonLocations[i]]
    }

    for (m in 1 until total){
    	for (i in 0 until size){

    		for (j in 0 until size){
    			if (m and (1 shl j) != 0){
    				dp[m][i] = min(dp[m][i], dp[m and (1 shl j).inv()][j] + 
    					newGraph[j][i])
    			}
    		}
    	}
    }

    var min = INF

    for (i in 0 until size){

    	if (dp[total - 1][i] < min) min = dp[total - 1][i]
    }

    println(min)
}

