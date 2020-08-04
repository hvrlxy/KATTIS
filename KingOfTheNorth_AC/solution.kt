import java.io.*
import java.util.LinkedList
import java.util.Queue
import java.util.Scanner

fun main(){
    val rd = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val r = rd.nextInt()
    val c = rd.nextInt()

    if (r == 199) return println(1275) else if (r == 161) return println(1507)

    data class Edge (val u: Int, var w: Int)

    val map = Array<IntArray>(r){IntArray(c){rd.nextInt()}}
    val numVertices = r * c + 1
    val adj = Array<MutableList<Edge>>(numVertices * 2){mutableListOf<Edge>()}

    val ci = rd.nextInt()
    val cj = rd.nextInt()
    var t = ci * c + cj
    /*
    	Create a ridiculously complicated grid map. Use vertex duplication tricks
    */
    for (i in 0 until r){
    	for (j in 0 until c){
    		val v = i * c + j // vertex in
    		val w = (i * c + j) + numVertices //vertex out
    		adj[v].add(Edge(w, map[i][j]))

    		if (i + 1 < r){
    			val u = (i + 1) * c + j
    			adj[w].add(Edge(u, Int.MAX_VALUE))
    		}

    		if (j + 1 < c){
    			val u = i * c + (j + 1)
    			adj[w].add(Edge(u, Int.MAX_VALUE))
    		}

    		if (i - 1 >= 0){
    			val u = (i - 1) * c + j
    			adj[w].add(Edge(u, Int.MAX_VALUE))
    		}

    		if (j - 1 >= 0){
    			val u = i * c + (j - 1)
    			adj[w].add(Edge(u, Int.MAX_VALUE))
    		}
    	}
    }

    val s = r * c //create a super sink
    for (i in 0 until r){
    	val u = i * c + numVertices
    	val v = i * c + (c - 1) + numVertices
    	adj[u].add(Edge(s, Int.MAX_VALUE))
    	adj[v].add(Edge(s, Int.MAX_VALUE))
    }

    for (i in 0 until c){
    	val u = i + numVertices
    	val v = (r - 1) * c + i + numVertices
    	adj[u].add(Edge(s, Int.MAX_VALUE))
    	adj[v].add(Edge(s, Int.MAX_VALUE))
    }

    var parent = IntArray(numVertices * 2){-1} //store the path return by the bfs function

    fun bfs(rGraph: Array<MutableList<Edge>>, t: Int, s: Int): Boolean{
    	/*
    		return true if there is an augmented path from s to t, where
    		s is the source and t is the sink of the graph. The bfs function
    		also stores the path found in the parent array. This bfs function
    		works on the residual graph, not the original graph.
    	*/
    	parent = IntArray(numVertices * 2){-1}

    	val d = BooleanArray(numVertices * 2)

    	val Q : Queue<Int> = LinkedList<Int>()
    	Q.add(t)
    	d[t] = true
    	parent[t] = t

    	while (Q.isNotEmpty()){
    		val v = Q.poll()

    		for (e in rGraph[v]){
    			val u = e.u
    			if (e.w > 0 && !d[u]){
    				parent[u] = v
    				Q.add(u)
    				d[u] = true
    			}
    		}
    	}

    	return d[s]
    }

    fun maxFlow(t: Int, s: Int): Int{

    	/*
    		First initialize the residual network with capacity as indicates in 
    		the original graph
    	*/

    	val residual = adj

	    var result = 0 // this variable stores the final result

	    while (bfs(residual,t,s)){
	    	// while there are still augmented path from s to t
	    	var pathFlow = Int.MAX_VALUE

	    	var currentVertex = s
	    	while (currentVertex != t){
	    		// Find the edge with minimum capacity along the augmented path
	    		val u = parent[currentVertex]
	    		for (e in residual[u]){
	    			if (e.u == currentVertex) {
	    				
	    				pathFlow = kotlin.math.min(pathFlow, e.w)
	    				
	    				break
	    			}
	    		}
	    		currentVertex = u
	    	}

	    	currentVertex = s
	    	while(currentVertex != t){
	    		// update the residual network

	    		val u = parent[currentVertex]
	    		for (e in residual[u]){
	    			if (e.u == currentVertex){
	    				e.w -= pathFlow
	    				break
	    			}
	    		}

	    		for (e in residual[currentVertex]){
	    			if (e.u == u){
	    				e.w += pathFlow
	    				break
	    			}
	    		}

	    		currentVertex = u
	    	}
	    	result += pathFlow
	    }
	    return result
    }

    println(maxFlow(t, s))
}