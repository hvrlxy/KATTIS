 import java.io.*
import java.util.LinkedList
import java.util.Queue

/*
	Classic bipartite matching problem.
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val m = rd.readInt()
    val n = m * 3

    val lMap = mutableMapOf<Int, Pair<Long, Long>>()
    val rMap = mutableMapOf<Long, Int>()
    val r2Map = mutableMapOf<Int, Long>()

    var lCount = 0
    var rCount = 0

    val graph = Array<BooleanArray>(n){BooleanArray(m)}

    repeat(m){
    	val aline = rd.readInts()
    	lMap[lCount] = Pair(aline[0], aline[1])

    	val plus = aline[0] + aline[1]
    	if (plus !in rMap.keys){
    		rMap[plus] = rCount
    		r2Map[rCount] = plus
    		rCount ++
    	}

    	val minus = aline[0] - aline[1]
    	if (minus !in rMap.keys){
    		rMap[minus] = rCount
    		r2Map[rCount] = minus
    		rCount ++
    	}

    	val mult = aline[0] * aline[1]
    	if (mult !in rMap.keys){
    		rMap[mult] = rCount
    		r2Map[rCount] = mult
    		rCount ++
    	}

    	graph[rMap[plus]!!][lCount] = true
    	graph[rMap[minus]!!][lCount] = true
    	graph[rMap[mult]!!][lCount] = true

    	lCount ++
    }
    /*
    println(lMap)
    println(rMap)
    println(r2Map)

    for (i in 0 until n){
    	println(graph[i].joinToString())
    }
    */

    fun dfs(rGraph: Array<BooleanArray>, u: Int, d: BooleanArray, match: IntArray): Boolean{
        for (v in 0 until m){
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

    fun MBMatching(graph: Array<BooleanArray>){
        val match = IntArray(m){-1}

        var result = 0
        for (u in 0 until n){
            val d = BooleanArray(m)

            if (dfs(graph, u, d, match)) result ++
        }

        if (result < m) println("Impossible")
        else{
        	//println(match.joinToString())
            for (i in 0 until m){
            	val pair = lMap[i]!!

            	val op = r2Map[match[i]]!!

            	if (pair.first + pair.second == op){println("${pair.first} + ${pair.second} = $op")}
            	else if (pair.first - pair.second == op){println("${pair.first} - ${pair.second} = $op")}
            	else if (pair.first * pair.second == op){println("${pair.first} * ${pair.second} = $op")}
            }
        }
    }
    MBMatching(graph)
}