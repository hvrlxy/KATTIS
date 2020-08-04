import java.io.*
import java.util.LinkedList
import java.util.Queue

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

    val numVertices = s1[0] + s1[3] * 2 + 2
    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    val rMap = mutableMapOf<String, Int>()
    val fMap = mutableMapOf<String, Int>()
    val sMap = mutableMapOf<String, Int>()

    var count = 1

    //read in the raw material states
    val rList = rd.readStrings()
    for (s in rList){
    	rMap[s] = count
    	graph[0][count] = 1
    	count ++
    }

    //read in the factories
    val fList = rd.readStrings()
    for (s in fList){
    	fMap[s] = count
    	graph[count][numVertices - 1] = 1
    	count ++
    }

    //read in the companies
    //println(s1[3])
    repeat(s1[3]){
    	val aline = rd.readStrings()
    	//println(aline)
    	val cin = count
    	count ++
    	val cout = count
    	graph[cin][cout] = 1


    	for (i in 1 until aline.size){
    		if (aline[i] in rMap.keys){
    			graph[rMap[aline[i]]!!][cin] = 1
    		}
    		else if (aline[i] in fMap.keys){
    			graph[cout][fMap[aline[i]]!!] = 1
    			
    		}
    		else if (aline[i] in sMap.keys){
    			graph[cout][sMap[aline[i]]!!] = 1
    			graph[sMap[aline[i]]!!][cin] = 1
    			
    		}
    		else{
    			count ++
    			sMap[aline[i]] = count
    			graph[cout][count] = 1
    			graph[count][cin] = 1
    			
    		}
    	}

    	count ++
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

	    	result += pathFlow
	    }
	    return result
    }

    println(maxFlow(0, numVertices - 1))
}