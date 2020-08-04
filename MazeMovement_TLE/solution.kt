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

    //read the map in as adj matrix
    val numVertices = rd.readInt() + 2
    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    var max = 0
    var maxIdx = -1
    var minIdx = -1
    var min = Int.MAX_VALUE
    val MAX_CAPACITY = 1000000000

    fun gcd(a: Int, b: Int): Int{
        if (b == 0) return a else return gcd(b, a%b)
    }

    val roomNumber = IntArray(numVertices - 2){rd.readInt()}

    //add the edges
    for (i in 0 until roomNumber.size){
        if (roomNumber[i] < min){
            min = roomNumber[i]
            minIdx = i + 1
        }
        if (roomNumber[i] > max){
            max = roomNumber[i]
            maxIdx = i + 1
        }
        for (j in i + 1 until roomNumber.size){
            val gcd = gcd(roomNumber[i], roomNumber[j])
            if (gcd > 1){
                graph[i + 1][j + 1] = gcd
                graph[j + 1][i + 1] = gcd
            }
        }
    }

    //add edges from source and to sink
    graph[0][minIdx] = MAX_CAPACITY
    graph[maxIdx][numVertices - 1] = MAX_CAPACITY

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

    println(maxFlow(0, numVertices - 1))
}