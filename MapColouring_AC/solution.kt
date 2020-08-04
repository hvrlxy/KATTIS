import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

/* 
	Problem statement: Coloring a graph with m colors using backtracking (NP-complete problem)
	Time complexity: O(m^V)
*/

val INF = 100000000

fun isSafe(graph: Array<MutableList<Int>>, v: Int, c: Int, color: IntArray): Boolean{
	// check if color c is safe for vertex v

	for (u in graph[v]){
		if (color[u] == c) return false
	}
	return true
}

fun coloring(graph: Array<MutableList<Int>>, m: Int, v: Int, color: IntArray): Boolean{
	// the function return true if the graph can be colored with m color, false otherwise
	// the current vertex we are considering is v
	val color = color

	//if all vertices have been assigned colors
	if (v == graph.size) {
		return true
	}

	//consider v
	for (c in 1 .. m){
		if (isSafe(graph, v, c, color)){
			color[v] = c

			if (coloring(graph, m, v + 1, color)) return true

			color[v] = 0
		}
	}

	return false
}

fun graphColoring(graph: Array<MutableList<Int>>, m: Int): Boolean{
	val color = IntArray(graph.size){0}

	return coloring(graph, m, 0, color)
}

fun printGraph(graph: Array<MutableList<Int>>){
	for (i in 0 until graph.size){
		println("$i: ${graph[i].joinToString(separator = " ")}")
	}
}

fun printColor(color: IntArray){
	for (i in 0 until color.size){
		println("${i + 1} ${color[i]}")
	}
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
    	val s = rd.readInts()
    	val numEdges = s[1]
    	val numVertices = s[0]

	    val graph = Array<MutableList<Int>>(numVertices){mutableListOf<Int>()}

	    repeat(numEdges){
	    	val a = rd.readInts()
	    	graph[a[0]].add(a[1])
	    	graph[a[1]].add(a[0])
	    }

	    var isValid = true
	    for (m in 1 .. 4){
	    	if (graphColoring(graph, m)){
	    		println(m)
	    		isValid = false
	    		break
	    	}
	    }
	    if (isValid) println("many")
    }
}