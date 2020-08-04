import java.io.*
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toIntArray() // list of ints

data class Edge (val u: Int, val w: Double)

data class Vertex (val v: Int, val w: Double): Comparable<Vertex>{
	override fun compareTo(other: Vertex) = other.w.compareTo(this.w)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()
    while (a[0] + a[1] != 0){
    	val adj = Array<MutableList<Edge>>(a[0]){mutableListOf<Edge>()}

    	repeat(a[1]){
    		val aline = rd.readStrings()
    		adj[aline[0].toInt()].add(Edge(aline[1].toInt(), aline[2].toDouble()))
    		adj[aline[1].toInt()].add(Edge(aline[0].toInt(), aline[2].toDouble()))
    	}

    	val d = DoubleArray(a[0]){0.0}
    	d[0] = 1.0

    	val Q = PriorityQueue<Vertex>()
    	Q.add(Edge(0, 1.0))
    	//val S = mutableSetOf<Int>()

    	while (Q.isNotEmpty()){
    		val u = Q.poll()
    		//S.add(u.v)
    		for (i in adj[u.v]){
    			if (u.w * i.w > d[i.u]){
    				d[i.u] = u.w * i.w
    				Q.add(Vertex(i.u, d[i.u]))
    			}
    		}
    	}

    	println(d[d.size - 1])
    	a = rd.readInts()
    }
}