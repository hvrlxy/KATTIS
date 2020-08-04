import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var a = rd.readInts()

	val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

	repeat(a[1]){
		a = rd.readInts()

		adj[a[0] - 1].add(a[1] - 1)
		adj[a[1] - 1].add(a[0] - 1)
	}

	val d = BooleanArray(adj.size){false}
	fun dfs(v: Int){
		d[v] = true
		for (u in adj[v]){
			for (w in adj[u]){
				if (d[w] == false) dfs(w)
			}
		}
	}

	var result = -1
	for (i in 0 until adj.size){
		if (d[i] == false){
			dfs(i)
			result ++
		}
	}
	println(result)
}