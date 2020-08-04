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

    val numCase = rd.readInt()
    repeat(numCase){
    	val numPlayer = rd.readInt()

    	val scoreArray = IntArray(numPlayer){0}
    	val gameLeft = IntArray(numPlayer){0}

    	val adj = Array<MutableList<Int>>(numPlayer){mutableListOf<Int>()}

    	val aMap = mutableMapOf<Int, Pair<Int, Int>>()
    	var gameCount = 0

    	for (i in 0 until numPlayer){
    		val aline = rd.readLn()
    		for (j in 0 until numPlayer){
    			if (aline[j] == '1') scoreArray[i] += 2
    			if (aline[j] == 'd') scoreArray[i] ++
    			if (aline[j] == '.'){
    				adj[i].add(j)
    				gameLeft[i] ++
    				if (i < j){
    					aMap[gameCount] = (i to j)
    					gameCount++
    				}
    			}
    		}
    	}

    	val graph = Array<IntArray>(gameCount + numPlayer + 2){IntArray(gameCount + numPlayer + 2){0}}

    	val s = gameCount + numPlayer
    	val t = gameCount + numPlayer + 1

    	// create edge of capacity 2 from s to all games
    	for (i in 0 until gameCount){
    		graph[s][i] = 2
    	}

    	// create edge of capacity 2 from games to both players
    	for (i in 0 until gameCount){
    		val p = aMap[i]!!
    		graph[i][gameCount + p.first] = 2
    		graph[i][gameCount + p.second] = 2
    	}

    	val numVertices = gameCount + numPlayer + 2

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

	    fun printGraph(){
	    	for (i in 0 until numVertices){
	    		println(graph[i].joinToString())
	    	}
	    }

	    fun tryPlayer(i: Int): Boolean{
	    	val maxScore = scoreArray[i] + gameLeft[i] * 2 //maximu scores that can still be obtained

	    	//re-set the graph edges
	    	for (g in 0 until gameCount){
	    		val p = aMap[g]!!

	    		if (p.first == i) graph[g][p.second + gameCount] = 0
	    		if (p.second == i) graph[g][p.first + gameCount] = 0
	    	}

	    	for (v in gameCount until gameCount + numPlayer){
	    		val m = maxScore - scoreArray[v - gameCount]
	    		if (m < 0) return false
	    		graph[v][t] = m
	    	}

	    	val f = maxFlow(s,t)
	    	//printGraph()
	    	//println("player: $i - flow: $f")

	    	//set the graph back
	    	for (g in 0 until gameCount){
	    		val p = aMap[g]!!

	    		if (p.first == i) graph[g][p.second + gameCount] = 2
	    		if (p.second == i) graph[g][p.first + gameCount] = 2
	    	}

	    	//printGraph()
	    	//println("after re-set")

	    	if (f == gameCount * 2) return true
	    	return false
	    }

	    //try each player
	    val possibleWinners = StringBuilder()
	    for (i in 0 until numPlayer){
	    	if (tryPlayer(i)) possibleWinners.append("${i + 1} ")
	    }
	    if (possibleWinners.length > 0) println(possibleWinners.deleteCharAt(possibleWinners.length - 1))
	    else println()
    }
}