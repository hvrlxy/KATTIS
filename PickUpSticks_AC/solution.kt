//a simple version of chess tournament

import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    val indegreeArray = IntArray(a[0]){0}

    repeat(a[1]){
    	val a1 = rd.readInts()
    	adj[a1[0] - 1].add(a1[1] - 1)
    	indegreeArray[a1[1] - 1] ++
    }

    val T = IntArray(a[0]){Int.MAX_VALUE}
    val V = IntArray(a[0]){Int.MAX_VALUE}

    fun topSort(): Boolean{
    	var count = 0
    	val Q = java.util.ArrayDeque<Int>()
    	for (i in 0 until a[0]){
    		if (indegreeArray[i] == 0) Q.add(i)
    	}

    	if (Q.isEmpty()) return false

    	while (Q.isNotEmpty()){
    		val v = Q.getFirst()
    		Q.removeFirst()
    		T[v] = count
    		V[count] = v + 1
    		count ++
    		for (w in adj[v]){
    			indegreeArray[w] --
    			if (T[w] != Int.MAX_VALUE) {
    				return false
    			}
    			else if (indegreeArray[w] == 0) Q.add(w)
    		}
    	}

    	for (i in 0 until T.size){
    		if (T[i] == Int.MAX_VALUE) return false
    	}
    	return true
    }

    if (topSort()) println(V.joinToString(separator = "\n")) else println("IMPOSSIBLE")
}