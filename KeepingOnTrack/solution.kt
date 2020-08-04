import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numEdges = rd.readInt()

	val adj = Array<MutableList<Int>>(numEdges + 1){ mutableListOf<Int>() }
	repeat(numEdges){
		val a = rd.readInts()
		adj[a[0]].add(a[1])
		adj[a[1]].add(a[0])
	}

	var maxDestroyed = Int.MIN_VALUE
	var minDestroyed = Int.MAX_VALUE
	val d = BooleanArray(numEdges + 1) 
	val size = IntArray(numEdges + 1){1}

	fun dfs(v: Int){
		d[v] = true
		val sizeList = mutableListOf<Int>()
		for (w in adj[v]){
			if (!d[w]){
				dfs(w)
				size[v] += size[w]
				sizeList.add(size[w])
			}
		}

		sizeList.add(numEdges - size[v] + 1)
		if (sizeList.size <= 1) return
		sizeList.sort()

		var sum = sizeList.sum()
		var max = 0
		for (i in 0 until sizeList.size - 1){
			sum -= sizeList[i]
			max += sizeList[i] * sum 
		}

		if (max > maxDestroyed) {
			maxDestroyed = max
			minDestroyed = max - (sizeList[sizeList.size - 1] * sizeList[sizeList.size - 2])
		}
	}

	dfs(0)

	println("$maxDestroyed ${minDestroyed}")
}