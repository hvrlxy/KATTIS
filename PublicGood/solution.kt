//I dont even know why this algorithm works

import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val numConstructions = a[0]
	val walkways = a[1]

	val adj = Array<MutableList<Int>>(numConstructions){mutableListOf<Int>()}

	repeat(walkways){
		val b = rd.readInts()
		adj[b[0] - 1].add(b[1] - 1)
		adj[b[1] - 1].add(b[0] - 1)
	}

	for (i in 0 until numConstructions){
		if (adj[i].size == 0) return println("Impossible")
	}

	val d = IntArray(numConstructions){0}
	fun dfs(v: Int){
		if (d[v] == 0) d[v] = 1
		for (w in adj[v]){
			if (d[w] == 0){
				d[w] = d[v] * -1
				dfs(w)
			}
		}
	}

	for (i in 0 until numConstructions){
		if (d[i] == 0) dfs(i)
	}

	val result = StringBuilder()
	for (i in 0 until numConstructions){
		if (d[i] == 1) result.append("pub ") else result.append("house ")
	}
	println(result.deleteCharAt(result.length - 1))
}