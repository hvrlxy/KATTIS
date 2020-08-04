import java.io.*
import java.util.LinkedList
import java.util.Queue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s1 = rd.readInts()
    val n = s1[0]
    val m = s1[1]
    val s = s1[2]
    val t = s1[3]

    val graph = Array<IntArray>(n){IntArray(n){0}}

    data class Edge (val x: Int, val y: Int)
    val edgeList = mutableListOf<Edge>()

    repeat(m){
    	val aline = rd.readInts()
    	graph[aline[0]][aline[1]] = aline[2]
    	edgeList.add(Edge(aline[0], aline[1]))
    }

    val finalList = StringBuilder()
    var edges = 0

    var parent = IntArray(n){-1} //store the path return by the bfs function

    fun bfs(rGraph: Array<IntArray>, s: Int, t: Int): Boolean{
    	/*
    		return true if there is an augmented path from s to t, where
    		s is the source and t is the sink of the graph. The bfs function
    		also stores the path found in the parent array. This bfs function
    		works on the residual graph, not the original graph.
    	*/
    	parent = IntArray(n){-1}

    	val d = BooleanArray(n)

    	val Q : Queue<Int> = LinkedList<Int>()
    	Q.add(s)
    	d[s] = true

    	while (Q.isNotEmpty()){
    		val v = Q.poll()
    		
    		for (u in 0 until n){
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

    	val residual = Array<IntArray>(n){IntArray(n){0}}

    	for (e in edgeList){
    		residual[e.x][e.y] = graph[e.x][e.y] - residual[e.x][e.y]
    	}

	    var result = 0 // this variable stores the final result

	    while (bfs(residual,s,t)){
	    	// while there are still augmented path from s to t
	    	var pathFlow = Int.MAX_VALUE

	    	var currentVertex = t
	    	while (currentVertex != s){
	    		// Find the edge with minimum capacity aInt the augmented path
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

	    for (e in edgeList){
    		graph[e.x][e.y] = graph[e.x][e.y] - residual[e.x][e.y]
    		if (graph[e.x][e.y] > 0) {
    			finalList.append("${e.x} ${e.y} ${graph[e.x][e.y]}\n")
    			edges ++
    		}
    	}
	    
	    return result
    }


    val f = maxFlow(s,t)
    println("$n $f $edges")
    print(finalList)
}