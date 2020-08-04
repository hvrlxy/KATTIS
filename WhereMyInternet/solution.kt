import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

	for (i in 0 until a[1]){
		val b = rd.readInts()

		adj[b[0] - 1].add(b[1] - 1)
		adj[b[1] - 1].add(b[0] - 1)
	}

	val isConnected = BooleanArray(a[0]){false}

	fun dfs(v: Int){
		if (!isConnected[v]) isConnected[v] = true
		for (w in adj[v]) {
			if (isConnected[w] == false) dfs(w)
		}
	}

	dfs(0)

	val result = StringBuilder()
	for (i in 0 until a[0]){
		if (!isConnected[i]) result.append("${i + 1}\n")
	}

	if (result.isEmpty()) println("Connected")
	else print(result)
}