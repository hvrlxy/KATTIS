import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val N = rd.readInt()

    val numVertices = N + 2 
    val graph = Array<IntArray>(numVertices){IntArray(numVertices){0}}
    var total = 0

    for (i in 1 .. N){
    	val aline = rd.readInts()
        //println(aline)
        total += aline[0]

        //create edge from source to v-in
        graph[0][i] = aline[0]
        //create edge from v-out to sink
        graph[i][numVertices - 1] = aline[1]

        for (j in 3 until 3 + aline[2]){
            // create edge from v-out to 
            graph[aline[j]][i] = 1000000
        }
    }

    fun printGraph(){
        //for debug purpose
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

    println(total - maxFlow(0, numVertices - 1))

}