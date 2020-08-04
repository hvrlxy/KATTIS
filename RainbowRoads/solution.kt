import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
    I use java queue in this implementation for speed, but feel free to use ArrayDeque 
    or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

data class Edge (val v: Int, val c: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()

    val isGood = BooleanArray(n + 1){true}

    val adj = Array<MutableList<Edge>>(n + 1){mutableListOf<Edge>()}
    val graph = Array<MutableList<Int>>(n + 1){mutableListOf<Int>()}

    repeat(n - 1){
    	val s = rd.readInts()

    	adj[s[0]].add(Edge(s[1], s[2]))
    	adj[s[1]].add(Edge(s[0], s[2]))

    	graph[s[0]].add(s[1])
    	graph[s[1]].add(s[0])
    }

    var d = BooleanArray(n + 1)

    fun dfs(v: Int, g: Array<MutableList<Int>>){
    	if (d[v] || !isGood[v]) return else d[v] = true
    	isGood[v] = false
    	for (w in g[v]){
    		dfs(w,g)
    	}	
    }

    for (i in 1 .. n){
    	adj[i].sortBy{it.c}
    	val l = adj[i].size
    	for (j in 1 until l){
    		if (adj[i][j].c == adj[i][j - 1].c){
    			d = BooleanArray(n + 1)

    			val g = Array<MutableList<Int>>(n + 1){mutableListOf<Int>()}
    			
    			for (i in 1 .. n){
    				g[i].addAll(graph[i])
    			}
    			g[adj[i][j].v].remove(i)
    			g[adj[i][j - 1].v].remove(i)

    			dfs(adj[i][j].v, g)
    			dfs(adj[i][j - 1].v, g)
    		}
    	}
    }
    val result = StringBuilder()

    val a = isGood.count{it == true} - 1
    result.append("$a\n")
    for (i in 1 .. n){
    	if (isGood[i]) result.append("$i\n")
    }
    print(result)
}