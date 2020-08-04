import java.io.*
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toIntArray() // list of ints

data class Edge (val v: Int, val weight: Int) // this one store the edge

data class Vertex (val v: Int, val weight: Int): Comparable<Vertex>{
	// this data structure tells us the min distance of a vertex at current time
	override fun compareTo(other: Vertex) = this.weight.compareTo(other.weight)
}

val INF = 1000000000

fun dijkstra (graph: Array<MutableList<Edge>>, s: Int): IntArray{
	val numVertices = graph.size
	val d = IntArray(numVertices){INF}
	val p = IntArray(numVertices){-1}
	d[s] = 0
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

}