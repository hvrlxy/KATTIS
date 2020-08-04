/* 
	Problem statement: Coloring a graph with m colors using backtracking (NP-complete problem)
	Time complexity: O(m^V)

	Input: The first line is 3 integers indicates the number of vertices, the number of edges
	and the number of colors allowed. The remaining lines indicate there is an edge from
	vertex a to vertex b.
*/

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
		printColor(color)
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
    val numVertices = readLine()!!.toInt()

    val graph = Array<MutableList<Int>>(numVertices){mutableListOf<Int>()}
    val m = 4

    var a = readLine()
    while (a != null){
    	val aline = a.split("-").map{it.toInt()}

    	if (aline[1] - 1 !in graph[aline[0] - 1]) graph[aline[0] - 1].add(aline[1] - 1)
    	if (aline[0] - 1 !in graph[aline[1] - 1]) graph[aline[1] - 1].add(aline[0] - 1)

    	a = readLine()
    }

    graphColoring(graph, m)
}