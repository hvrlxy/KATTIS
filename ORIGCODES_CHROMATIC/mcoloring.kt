import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

/* 
	Problem statement: Coloring a graph with m colors using backtracking (NP-complete problem)
	Time complexity: O(m^V)

	Input: The first line is 3 integers indicates the number of vertices, the number of edges
	and the number of colors allowed. The remaining lines indicate there is an edge from
	vertex a to vertex b.
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
		println(color.joinToString(separator = " "))
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

fun graphColoring(graph: Array<MutableList<Int>>, m: Int){
	val color = IntArray(graph.size){0}

	if (!coloring(graph, m, 0, color)) println("No solutions exist.")
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

    val s = rd.readInts()
    val numVertices = s[0]
    val numEdges = s[1]
    val m = s[2]

    val graph = Array<MutableList<Int>>(numVertices){mutableListOf<Int>()}

    repeat(numEdges){
    	val a = rd.readInts()
    	graph[a[0]].add(a[1])
    	graph[a[1]].add(a[0])
    }

    graphColoring(graph, m)
}