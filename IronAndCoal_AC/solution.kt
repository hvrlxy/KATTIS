import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

// ridiculously complicated problem: TLE, MLE, ...

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 10000000

/* 

The problem is the question of finding a minimal Steirner tree - which is an NP-hard problem.
However, we can reduce the problem to finding the minimal Steirner tree with 3 terminals, which
is a polynomial algorithm.

- Create a supernode C - connect every coal node to C with weight = 0
- Create a supernode O - conect every ore node to O with weight = 0
- Create a reversed graph G'

- CAlculate the shortest distance from s to all nodes in G
- Calcualte the shortest distance from O and C to all nodes in G'

Find the node with min(d[O][v] + d[C][v] + d[s][v])

*/

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val n = s[0] //total
    val m = s[1] //ore
    val k = s[2] //coal

    val S = 1		//source
    val O = 0		//supernode ore
    val C = n + 1	//supernode coal

    val graph = Array<MutableList<Int>>(n + 2){mutableListOf<Int>()}
    val rGraph = Array<MutableList<Int>>(n + 2){mutableListOf<Int>()}

    fun bfs (G: Array<MutableList<Int>>, s: Int): IntArray{
    	val numVertices = G.size

    	val d = BooleanArray(numVertices)
    	val result = IntArray(numVertices){INF}

    	val Q : Queue<Int> = LinkedList<Int>()
    	Q.add(s)
    	result[s] = 0
    	d[s] = true

    	while (Q.isNotEmpty()){
    		val v = Q.poll()
    		//println(v)

    		for (u in G[v]){
    			if (!d[u]){
    				if (v == s && (s == C || s == O)) result[u] = result[v]
    				else result[u] = result[v] + 1
    				Q.add(u)
    				d[u] = true
    			}
    		}
    	}

    	return result
    }

    // read the input

    val oList = rd.readInts()
    for (o in oList){
    	graph[o].add(O)
    	rGraph[O].add(o)
    }

    val cList = rd.readInts()
    for (c in cList){
    	graph[c].add(C)
    	rGraph[C].add(c)
    }

    for (i in 1 .. n){
    	val array = rd.readInts()

    	for (j in 1 until array.size){
    		graph[i].add(array[j])
    		rGraph[array[j]].add(i)
    	}
    }

    val fromS = bfs(graph, S)
    val fromC = bfs(rGraph, C)
    val fromO = bfs(rGraph, O)

    var result = INF
    for (i in 1 .. n){
    	result = min(fromS[i] + fromO[i] + fromC[i], result)
    }

    if (result == INF) println("impossible") else println(result)
}