import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val lands = mutableListOf<Pair<Int, Int>>()
	val clouds = mutableListOf<Pair<Int, Int>>()

	fun dfs(v: Pair<Int, Int>){
		if (v !in lands && v !in clouds) return
		else if (v in lands) lands.remove(v)
		else if (v in clouds) clouds.remove(v)

		dfs(v.first to v.second + 1)
		dfs(v.first to v.second - 1)
		dfs(v.first - 1 to v.second)
		dfs(v.first + 1 to v.second)
	}

	val a = rd.readInts()
	for (i in 0 until a[0]){
		val b = rd.readLn()
		for (j in 0 until a[1]){
			if (b[j] == 'L') lands.add(i to j)
			else if (b[j] == 'C') clouds.add(i to j)
		}
	}

	var result = 0
	while (lands.isNotEmpty()){
		result ++
		dfs(lands[0])
	}
	println(result)
}