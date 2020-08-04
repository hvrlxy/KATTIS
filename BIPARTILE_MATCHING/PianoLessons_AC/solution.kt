import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
    For this problem, duplicate all vertex so there are v-in and v-out. Hence, we have a bipartile 
    graph: The left set should represent the in vertices, while the right set should represent 
    the out vertices.
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val N = s[0] //students
    val M = s[1] //time slots

    val numVertices = max(M + 1, N)

    val graph = Array<BooleanArray>(numVertices){BooleanArray(numVertices)}

    for (i in 0 until N){
    	val aline = rd.readInts()
    	for (j in 1 until aline.size){
    		graph[aline[j]][i] = true
    	}
    }

    fun dfs(rGraph: Array<BooleanArray>, u: Int, d: BooleanArray, match: IntArray): Boolean{
        for (v in 0 until numVertices){
            if (rGraph[u][v] && !d[v]){
                d[v] = true
                if (match[v] < 0 || dfs(rGraph, match[v], d, match)){
                    match[v] = u
                    return true
                }
            }
        }
        return false
    }

    fun MBMatching(graph: Array<BooleanArray>): Int{
        val match = IntArray(numVertices){-1}

        var result = 0
        for (u in 0 until numVertices){
            val d = BooleanArray(numVertices)

            if (dfs(graph, u, d, match)) result ++
        }
        //println(match.joinToString())
        return result
    }

    println(MBMatching(graph))
}