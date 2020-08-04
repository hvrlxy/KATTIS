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

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()
    while (a[0] + a[1] + a[2] + a[3] != 0){
    	val adj = Array<MutableList<Edge>>(a[0]){mutableListOf<Edge>()}

    	repeat(a[1]){
    		val aline = rd.readStrings()
    		adj[aline[0].toInt()].add(Edge(aline[1].toInt(), aline[2].toInt()))
    	}

    	val d = IntArray(a[0]){Int.MAX_VALUE}
    	d[a[3]] = 0

    	val Q = PriorityQueue<Vertex>()
    	Q.add(Vertex(a[3],0))
    	while (Q.isNotEmpty()){
    		val u = Q.poll()

    		for (i in adj[u.v]){
    			if (u.weight + i.weight < d[i.v]){
    				d[i.v] = u.weight + i.weight
    				Q.add(Vertex(i.v, d[i.v]))
    			}
    		}
    	}

    	repeat(a[2]){
    		val query = rd.readInt()
    		if (d[query] == Int.MAX_VALUE) println("Impossible") else println(d[query])
    	}
    	a = rd.readInts()
    	if (a[0] + a[1] + a[2] + a[3] != 0) println()
    }
}




