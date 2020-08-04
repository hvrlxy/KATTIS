import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var a = rd.readInts()

	val adj = Array<MutableList<Int>>(a[0] + 1){mutableListOf<Int>()}
	repeat(a[1]){
		val a1 = rd.readInts()
		adj[a1[0]].add(a1[1])
		adj[a1[1]].add(a1[0])
	}

	val disc = IntArray(a[0] + 1){ Int.MAX_VALUE }
	disc[a[2]] = 0

	val Q = mutableListOf<Int>()
	Q.add(a[2])
	while (Q.isNotEmpty()){
		val v = Q[0]
		Q.removeAt(0)
		for (w in adj[v]){
			if (disc[w] == Int.MAX_VALUE){
				disc[w] = disc[v] + 1
				Q.add(w)
			}
		}
	}
	var result = 0
	for (i in 1 until a[0]){
		if (disc[i] <= a[3]) result ++
	}
	println(result)
}