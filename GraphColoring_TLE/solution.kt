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
		//println(color.joinToString(separator = " "))
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

fun binarySearch(graph: Array<MutableList<Int>>, high: Int, low: Int): Int{
	if (high <= low + 1) return high

	val m = low + (high - low) / 2
	if (graphColoring(graph, m)) return binarySearch(graph, m, low)
	else return binarySearch(graph, high, m)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numVertices = rd.readInt()

    val graph = Array<MutableList<Int>>(numVertices){mutableListOf<Int>()}

    for (i in 0 until numVertices){
    	val a = rd.readInts()

    	for (j in a){
    		graph[i].add(j)
    	}
    }

    println(binarySearch(graph, numVertices, 1))
}