import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
    For this problem, duplicate all vertex so there are v-in and v-out. Hence, we have a bipartile 
    graph: The left set should represent the in vertices, while the right set should represent 
    the out vertices.
*/

data class Point(val x: Int, val y: Int)

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

var numVertices = 0

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

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val C = rd.readInt()

    repeat(C){
    	rd.readLn()
    	val size = rd.readInts()
    	numVertices = max(size[0] + 1, size[1] + 1)
    	val interests = rd.readInt()

    	val graph = Array<BooleanArray>(numVertices){BooleanArray(numVertices)}

    	val pList = mutableListOf<Point>()
    	repeat(interests){
    		val p = rd.readInts()

    		graph[p[0]][p[1]] = true

    		for (point in pList){
    			if (point.x == p[0]){
    				for (v in min(point.y, p[1]) .. max(point.y, p[1])){
    					graph[point.x][v] = true
    				}
    			}

    			if (point.y == p[1]){
    				for (v in min(point.x, p[0]) .. max(point.x, p[0])){
    					graph[v][point.y] = true
    				}
    			}
    		}
    		pList.add(Point(p[0], p[1]))
    	}

    	val obstacles = rd.readInt()

    	repeat(obstacles){
    		val p = rd.readInts()
    		graph[p[0]][p[1]] = false
    	}

    	println(MBMatching(graph))
    }
}