import java.io.*
import java.util.LinkedList
import java.util.Queue

/*
    Bipartite matching can be implement using boolean array.
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()
    val graph = Array<BooleanArray>(n){BooleanArray(n)}

    fun dfs(rGraph: Array<BooleanArray>, u: Int, d: BooleanArray, match: IntArray): Boolean{
    	for (v in 0 until n){
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

    fun MBM(graph: Array<BooleanArray>): Int{
    	val match = IntArray(n){-1}

    	var result = 0
    	for (u in 0 until n){
    		val d = BooleanArray(n)

    		if (dfs(graph, u, d, match)) result ++
    	}
    	return result
    }

    
}