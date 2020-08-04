import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/* The locally property suggests that each components of the graph is a complete bipartile graph */

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val m = s[0]
    val n = s[1]
    val k = s[2]

    val graph = Array<MutableList<Int>>(m + n){mutableListOf<Int>()}
    for (i in 0 until m){
    	val aline = rd.readInts()
    	for (j in 0 until n){
    		if (aline[j] == 0) {
    			graph[i].add(m + j)
    			graph[m + j].add(i)
    		}
    	}
    }

    val d = BooleanArray(m + n){false}

    fun dfs(v: Int){
    	if (!d[v]) d[v] = true else return
    	for (w in graph[v]) dfs(w)
    }

   	for (i in m until d.size){
   		if (graph[i].isEmpty()) return println("no")
   	}

    var count = 0
    for (i in m until d.size){
    	if (!d[i]) {
    		dfs(i)
    		count ++
    	}
    }

    if (count <= k) return println("yes") else return println("no")
}