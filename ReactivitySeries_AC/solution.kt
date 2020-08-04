/*Idea: topological sorting, hamiltonian path. 
Theorem: the graph that has only one way to topological sort it is a graph that contains a hamiltonian path
Use mutableSet in the adjacency list for better efficiency.
*/

import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts() //metals and experiments

    val adj = Array<MutableSet<Int>>(a[0]){mutableSetOf<Int>()}

    repeat(a[1]){
        val a1 = rd.readInts()
        adj[a1[0]].add(a1[1])
    }

    var count = a[0] - 1
    val T = IntArray(a[0]){Int.MAX_VALUE}
    val V = IntArray(a[0]){Int.MAX_VALUE}

    fun dfs(v: Int){
        for (w in adj[v]){
            if (T[w] == Int.MAX_VALUE) dfs(w)
        }
        T[v] = count
        V[count] = v
        count --
    }

    for (i in 0 until a[0]){
        if (T[i] == Int.MAX_VALUE) dfs(i)
    }

    var backToLab = false

    for (i in 0 until a[0] - 1){
        if (V[i + 1] !in adj[V[i]]) {
            backToLab = true
            break
        }
    }

    if (backToLab) println("back to the lab") else println(V.joinToString(separator = " "))
}