import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
    I use java queue in this implementation for speed, but feel free to use ArrayDeque 
    or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val stats = rd.readStrings()

    data class Floe (val x: Int, val y: Int, val n: Int, val m: Int)

    fun dist(f1: Floe, f2: Floe): Double = sqrt((f1.x - f2.x).toDouble() * (f1.x - f2.x) + (f1.y - f2.y).toDouble() * (f1.y - f2.y))

    val numVertices = stats[0].toInt() * 2 + 1

    val d = stats[1].toDouble()
    val n = stats[0].toInt()
    val s = numVertices - 1

    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    var numPenguin = 0

    val floeList = Array<Floe>(n){Floe(0,0,0,0)}

    for (i in 0 until n){
    	val aline = rd.readInts()
    	floeList[i] = Floe(aline[0], aline[1], aline[2], aline[3])

    	// create edge from the source to all floe
    	graph[s][i] = floeList[i].n
    	numPenguin += floeList[i].n

    	val w = i + n
    	//create edge between vin and vout
    	graph[i][w] = floeList[i].m
    }

    //create edges between floes
    for (i in 0 until n){
    	val v = i + n
    	for (j in 0 until n){
    		if (i == j) continue
    		if (dist(floeList[i], floeList[j]) < d){
    			graph[v][j] = Int.MAX_VALUE
    		}
    	}
    }

    fun printGraph(){
    	for (i in 0 until numVertices){
    		println(graph[i].joinToString())
    	}
    }

    //printGraph()


    var parent = IntArray(numVertices){-1} //store the path return by the bfs function

    fun bfs(rGraph: Array<IntArray>, s: Int, t: Int): Boolean{
    	/*
    		return true if there is an augmented path from s to t, where
    		s is the source and t is the sink of the graph. The bfs function
    		also stores the path found in the parent array. This bfs function
    		works on the residual graph, not the original graph.
    	*/
    	parent = IntArray(numVertices){-1}

    	val d = BooleanArray(numVertices)

    	val Q : Queue<Int> = LinkedList<Int>()
    	Q.add(s)
    	d[s] = true

    	while (Q.isNotEmpty()){
    		val v = Q.poll()

    		for (u in 0 until numVertices){
    			if (rGraph[v][u] > 0 && !d[u]){
    				parent[u] = v
    				Q.add(u)
    				d[u] = true
    			}
    		}
    	}

    	return d[t]
    }

    fun maxFlow(s: Int, t: Int): Int{

    	/*
    		First initialize the residual network with capacity as indicates in 
    		the original graph
    	*/

    	val residual = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    	for (i in 0 until numVertices){
	    	for (j in 0 until numVertices){
	    		residual[i][j] = graph[i][j]
	    	}
	    }

	    var result = 0 // this variable stores the final result

	    while (bfs(residual,s,t)){
	    	// while there are still augmented path from s to t
	    	var pathFlow = Int.MAX_VALUE

	    	var currentVertex = t
	    	while (currentVertex != s){
	    		// Find the edge with minimum capacity along the augmented path
	    		val u = parent[currentVertex]
	    		pathFlow = kotlin.math.min(pathFlow, residual[u][currentVertex])
	    		currentVertex = u
	    	}

	    	currentVertex = t
	    	while(currentVertex != s){
	    		// update the residual network

	    		val u = parent[currentVertex]
	    		residual[currentVertex][u] += pathFlow
	    		residual[u][currentVertex] -= pathFlow

	    		currentVertex = u
	    	}

	    	result += pathFlow
	    }
	    return result
    }

    fun solve(): MutableList<Int>{
    	val result = mutableListOf<Int>()
    	for (t in 0 until n){
    		val f = maxFlow(s, t)
    		//println(f)
    		if (f == numPenguin) result.add(t)
    	}
    	return result
    }

    val ans = solve()
    if (ans.isEmpty()) println(-1) else println(ans.joinToString(separator = " "))
}