import java.io.*
import java.util.LinkedList
import java.util.Queue

/*
	A complicated way to state the problem of maximum independent set in a bipartile graph.
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toMutableList() // list of ints

data class Voter(val love: String, val hate: String)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
    	val s = rd.readInts()

    	val numVertices = s[2]
    	val graph = Array<BooleanArray>(numVertices){BooleanArray(numVertices)}

    	val C = mutableListOf<Voter>()
    	val D = mutableListOf<Voter>()

    	repeat(s[2]){
    		val aline = rd.readStrings()
    		val v = Voter(aline[0], aline[1])

    		if (v.love[0] == 'C') {
    			C.add(v) 
    			val count = C.size - 1
    			for (u in 0 until D.size){
    				if (D[u].love == v.hate) graph[count][u] = true
    				if (D[u].hate == v.love) graph[count][u] = true
    			}
    		}
    		else {
    			D.add(v)
    			val count = D.size - 1
    			for (u in 0 until C.size){
    				if (C[u].love == v.hate) graph[u][count] = true
    				if (C[u].hate == v.love) graph[u][count] = true
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

	    println(numVertices - MBMatching(graph))
    }

}