import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

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

    val numVertices = max(s[0], s[1])

    val graph = Array<BooleanArray>(numVertices){BooleanArray(numVertices)}

    val aMap = mutableMapOf<String, MutableList<Int>>()
    for (i in 0 until s[0]){
    	val aline = rd.readStrings()

    	for (b in 2 until aline.size){
    		if (aline[b] !in aMap) aMap[aline[b]] = mutableListOf(i)
    		else aMap[aline[b]]!!.add(i)
    	}
    }

    for (j in 0 until s[1]){
    	val aline = rd.readStrings()

    	for (b in 2 until aline.size){
    		val alist = aMap[aline[b]]!!

    		for (i in alist){
    			graph[i][j] = true
    		}
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