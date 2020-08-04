import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	var h = a[0]
	if (h == 0) return println(0)
	val n = a[1]
	val m = a[2]

	val matrix = mutableSetOf<Pair<Int, Int>>()

	for (i in 0 until n){
		val aString = rd.readLn()
		for (j in 0 until m * 2 step 2){
			if (i % 2 == 0){
				if (aString[j] == '.'){
					matrix.add(i to j)
				}
			}
			else if (aString[j + 1] == '.'){
				matrix.add(i to j + 1)
			}
		}
	}

	var count = 0

	fun dfs(first: Int, second: Int){
		if ((first to second) in matrix) matrix.remove(first to second) else return
		count ++
		dfs(first + 1, second + 1)
		dfs(first + 1, second - 1)
		dfs(first - 1, second + 1)
		dfs(first - 1, second - 1)
		dfs(first, second + 2)
		dfs(first, second - 2)
	}

	val aList = mutableListOf<Int>()
	while (matrix.isNotEmpty()){
		count = 0
		dfs(matrix.elementAt(0).first, matrix.elementAt(0).second)
		aList.add(count)
	}

	aList.sortDescending()
	var result = 0
	while (h > 0){
		h -= aList[result]
		result++
	}
	println(result)
}

