import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val row = a[0]
	val column = a[1]

	val vertexList = mutableListOf<Pair<Int, Int>>()
	val matrix = Array<CharArray>(row){CharArray(column){' '}}

	for (i in 0 until row){
		val aString = rd.readLn()
		for (j in 0 until column){
			matrix[i][j] = aString[j]
			if (aString[j] == '.'){
				vertexList.add(i to j)
			}
		}
	}

	var result = mutableListOf<Pair<Int, Int>>()
	for (p in vertexList){
		if ((p.first + p.second) % 2 == 1){
			result.add(p)
		}
		else{
			if ((p.first + 1 to p.second) !in vertexList &&
				(p.first - 1 to p.second) !in vertexList &&
				(p.first to p.second + 1) !in vertexList &&
				(p.first to p.second - 1) !in vertexList){
				result.add(p)
			}
		} 
	}

	for (pair in result){
		matrix[pair.first][pair.second] = 'E'
	}

	for (i in 0 until row){
		println(matrix[i].joinToString(separator=""))
	}
}