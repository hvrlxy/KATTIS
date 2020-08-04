import java.io.*
import java.util.LinkedList
import java.util.Queue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val numVertices = s[0] + 1
    val m = s[1]

    val s1 = rd.readInts()

    val t = s1[0]
    val chocolate = s1[1]
    val vanilla = s1[2]

    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}

    repeat(m){
        val s2 = rd.readInts()
        graph[s2[0]][s2[1]] += s2[2] * 2
        graph[s2[1]][s2[0]] += s2[2] * 2
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

    fun binarySearch(MIN_CAPACITY: Int, MAX_CAPACITY: Int): Int{
    	if (MIN_CAPACITY + 1 >= MAX_CAPACITY) return MIN_CAPACITY
    	val guessFlow = (MAX_CAPACITY + MIN_CAPACITY) / 2

    	graph[0][chocolate] = guessFlow
    	graph[0][vanilla] = guessFlow

    	val f = maxFlow(0, t)
    	//println("$MIN_CAPACITY $MAX_CAPACITY $f")
    	if (f == guessFlow * 2){
    		return binarySearch(guessFlow, MAX_CAPACITY)
    	}
    	else{
    		return binarySearch(MIN_CAPACITY, guessFlow)
    	}
    }

    val max = graph[chocolate].sum() + graph[vanilla].sum()
    //println(max)
    println(binarySearch(0, max))
}