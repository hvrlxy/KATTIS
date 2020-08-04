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

    val s1 = rd.readInts()
    val c = s1[0]
    val r = s1[1]

    val numVertices = (c * r) * 2 + 1

    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    val t = (c * r) * 2 // sink
    var s = 0

    val cArray = Array<CharArray>(r){CharArray(c){' '}}
    for (i in 0 until r){
        val aline = rd.readLn()
        for (j in 0 until c){
            cArray[i][j] = aline[j]
            if (cArray[i][j] == 'B') s = i * c + j // source
        }
    }
    val char = rd.readInts()

    for (i in 0 until r){
        for (j in 0 until c){
            val v = i * c + j // vertex in
            val w = v + (c * r) // vertex out

            if (cArray[i][j] == 'B' || cArray[i][j] == '.') graph[v][w] = Int.MAX_VALUE
            else graph[v][w] = char[cArray[i][j] - 'a']

            if (v >= c){
                // up direction
                graph[w][v - c] = Int.MAX_VALUE
            }
            else graph[w][t] = Int.MAX_VALUE
            if (v < r * c - c){
                // down direction
                graph[w][v + c] = Int.MAX_VALUE
            }
            else graph[w][t] = Int.MAX_VALUE
            if (v % c > 0){
                //left direction
                graph[w][v - 1] = Int.MAX_VALUE
            }
            else graph[w][t] = Int.MAX_VALUE
            if (v % c < c - 1){
                // right direction
                graph[w][v + 1] = Int.MAX_VALUE
            }
            else graph[w][t] = Int.MAX_VALUE
        }
    }


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

            if (pathFlow == Int.MAX_VALUE) return Int.MAX_VALUE
	    	result += pathFlow
	    }
	    return result
    }

    val f = maxFlow(s, t)
    if (f == Int.MAX_VALUE) println(-1) else println(f)
}