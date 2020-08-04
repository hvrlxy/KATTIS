import java.io.*
import java.util.PriorityQueue
import java.util.ArrayDeque

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }

data class Edge (val u: Int, val v: Int, val t0: Int, val p: Int, val d: Int)

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val s = rd.readInts()

	val adj = Array<MutableList<Edge>>(s[0]){mutableListOf<Edge>()}

	val d = IntArray(s[0]){-1} // this array indicates the latest time you have to be at station i
	d[s[0] - 1] = s[2]

	val Q = java.util.ArrayDeque<Int>()

	repeat(s[1]){
		val aline = rd.readInts()
		// put the edge into the adj list
		adj[aline[1]].add(Edge(aline[0], aline[1], aline[2], aline[3], aline[4]))
	}

	Q.add(s[0] - 1)
	while (Q.isNotEmpty()){
		val v = Q.getFirst()
		Q.removeFirst()
		for (e in adj[v]){
			if (d[v] - e.d < e.t0){
				continue
			}
			val timeWaiting = (d[v] - e.d - e.t0) % e.p
			val d0 = (d[v] - e.d) - timeWaiting
			if (d0 > d[e.u]){
				d[e.u] = d0
				Q.add(e.u)
			}
		}
	}

	if (d[0] < 0) println("impossible") else println(d[0])
}







