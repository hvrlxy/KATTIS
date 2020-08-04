import java.io.*
import kotlin.math.*
import java.util.LinkedList
import java.util.Queue
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

data class Edge (val v: Int, val d: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()
    val N = a[0]
    val M = a[1]
    val T = a[2]

    val adj = Array<MutableList<Edge>>(N){mutableListOf<Edge>()}

    repeat(M){
    	val a = rd.readInts()

    	adj[a[0]].add(Edge(a[1], a[2]))
    	adj[a[1]].add(Edge(a[0], a[2]))
    }

    val a = rd.readInts()
    val s = a[0]
    val t = a[1]

    val sList = rd.readInts()
    val distSpider = IntArray(N){Int.MAX_VALUE}

    val Q : Queue<Int> = LinkedList<Int>()

    for (i in 1 .. sList[0]){
    	distSpider[sList[i]] = 0
    	Q.add(sList[i])
    }

    // a bfs to calculate minDist for all intersections
    while (Q.isNotEmpty()){
    	val v = Q.poll()
    	for (e in adj[v]){
    		if (distSpider[e.v] < distSpider[v] + e.d){
    			distSpider[e.v] = distSpider[v] + e.d
    			Q.add(e.v)
    		}
    	}
    }

    val d = BooleanArray(N)
    d[s] = true
    val D = IntArray(N){distSpider[it]}
    fun dfs(v: Int, currentT: Int){
        for (e in adj[v]){
            if (!d[e.v] && currentT + e.d <= T){
                d[e.v] = true
                if (distSpider[e.v] >= D[v] && D[v] >= D[e.v]) D[e.v] = D[v]
                dfs(e.v, currentT + e.d)
                d[e.v] = false
            }
        }
    }

    dfs(s, 0)

    println(D[t])
}