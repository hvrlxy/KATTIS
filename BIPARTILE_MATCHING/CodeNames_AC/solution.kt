import java.io.*
import java.util.LinkedList
import java.util.Queue

/*
    The problem of finding maximum independent set in a bipartile graph
    According to Konign theorem, maximum independent set in a bipartile graph
    equals to the number of vertex minus minimim vertex cover, which is the maximum
    bipartile matching.
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()
    val adj = Array<MutableList<Int>>(n){mutableListOf<Int>()}
    val wordList = mutableListOf<String>()

    fun isEdge(a: String, b: String): Boolean{
    	var diff = 0 
    	for (i in 0 until a.length){
    		if (a[i] != b[i]) diff ++
    	}
    	if (diff == 2) return true else return false
    }

    for (i in 0 until n){
    	val aline = rd.readLn()
    	wordList.add(aline)
    	for (j in 0 until i){
    		if (isEdge(aline, wordList[j])){
    			adj[i].add(j)
    			adj[j].add(i)
    		}
    	}
    }
    //println(adj.joinToString(separator = "\n"))

    // first we need to identify the bipartile graph components
    val L = mutableListOf<Int>()
    val R = mutableListOf<Int>()
    val d = IntArray(n){-1}

    fun dfs(v: Int, p: Int){
        //println(v)
        //println(d.joinToString())
        if (d[v] == -1){
            d[v] = 1 - d[p]
        }

        if (d[v] == 0) L.add(v) else R.add(v)
        for (w in adj[v]){
            if (d[w] == -1) {
                dfs(w, v)
            }
        }
    }

    for (v in 0 until n){
        if (d[v] == -1){
            d[v] = 0
            dfs(v,v) 
        }
    }

    // create the flow graph
    val graph = Array<IntArray>(n + 2){IntArray(n + 2){0}}

    // create edge from L to R
    for (v in L){
        for (w in adj[v]){
            if (w in R) graph[v][w] = 1
        }
    }

    //create edges from s to L
    for (v in L){
        graph[n][v] = 1
    }

    // create edge from R to t
    for (v in R){
        graph[v][n + 1] = 1
    }

    var parent = IntArray(n + 2){-1} //store the path return by the bfs function

    fun bfs(rGraph: Array<IntArray>, s: Int, t: Int): Boolean{
        /*
            return true if there is an augmented path from s to t, where
            s is the source and t is the sink of the graph. The bfs function
            also stores the path found in the parent array. This bfs function
            works on the residual graph, not the original graph.
        */
        parent = IntArray(n + 2){-1}

        val d = BooleanArray(n + 2)

        val Q : Queue<Int> = LinkedList<Int>()
        Q.add(s)
        d[s] = true

        while (Q.isNotEmpty()){
            val v = Q.poll()

            for (u in 0 until n + 2){
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

        val residual = Array<IntArray>(n + 2){IntArray(n + 2){0}}

        for (i in 0 until n + 2){
            for (j in 0 until n + 2){
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

    val f = maxFlow(n, n + 1)
    println(n - f)
}


