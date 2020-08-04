import kotlin.math.*
import java.io.*
import java.util.ArrayDeque
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 1000000000.0

data class Edge (val v: Int, val weight: Double) // this one store the edge

data class Vertex (val v: Int, val weight: Double): Comparable<Vertex>{
	// this data structure tells us the min distance of a vertex at current time
	override fun compareTo(other: Vertex) = this.weight.compareTo(other.weight)
}

fun dijkstra (graph: Array<MutableList<Edge>>, s: Int): IntArray{
	val numVertices = graph.size
	val d = DoubleArray(numVertices){INF}
	val p = IntArray(numVertices){-1} // parents //distance
	d[s] = 0.0
	p[s] = s

	val Q = PriorityQueue<Vertex>()
	Q.add(Vertex(s, 0.0))

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

	return p
}

data class Point(val x: Int, val y: Int, val z: Int)

fun euclidean (a: Point, b: Point): Double = sqrt((a.x - b.x).toDouble() * (a.x - b.x)
	+ (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z))

fun printPath(p: IntArray, s: Int, t: Int){
	//println(p.joinToString())
	val alist = ArrayDeque<Int>()

	alist.addLast(t)
	var v = alist.getFirst()
	while (v != p[v]){
		v = p[v]
		alist.addFirst(v)
	}

	println(alist.joinToString(separator = " "))
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val places = s[0]
    val connections = s[1]

    val graph = Array<MutableList<Edge>>(places){mutableListOf<Edge>()}

    val coordinate = Array<Point>(places){Point(0,0,0)}

    for (i in 0 until places){
    	val a = rd.readInts()
    	coordinate[i] = Point(a[1], a[2], a[0] * 5)
    }

    repeat(connections){
    	val aline = rd.readStrings()

    	if (aline[2] == "walking" || aline[2] == "stairs"){
    		val w = euclidean(coordinate[aline[0].toInt()], coordinate[aline[1].toInt()])
    		graph[aline[0].toInt()].add(Edge(aline[1].toInt(), w))
    		graph[aline[1].toInt()].add(Edge(aline[0].toInt(), w))	
    	}
    	else if (aline[2] == "lift"){
    		graph[aline[0].toInt()].add(Edge(aline[1].toInt(), 1.0))
    		graph[aline[1].toInt()].add(Edge(aline[0].toInt(), 1.0))
    	}
    	else{
    		val w = euclidean(coordinate[aline[0].toInt()], coordinate[aline[1].toInt()])
    		graph[aline[0].toInt()].add(Edge(aline[1].toInt(), 1.0))
    		graph[aline[1].toInt()].add(Edge(aline[0].toInt(), w * 3.0))	
    	}
    }

    val Q = rd.readInt()

    repeat(Q){
    	val query = rd.readInts()

    	printPath(dijkstra(graph, query[0]), query[0], query[1])
    }
}