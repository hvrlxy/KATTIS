import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var numRect = rd.readInt()
	while (numRect > 0){
		val rectList = Array<List<Int>>(numRect){rd.readInts()}

		rectList.sortBy{it[0]}
		val x0 = rectList[0][0]
		rectList.sortBy{it[1]}
		val y0 = rectList[0][1]
		rectList.sortBy{it[2]}
		val x1 = rectList[numRect - 1][2]
		rectList.sortBy{it[3]}
		val y1 = rectList[numRect - 1][3]


		fun isIn(x: Int, y: Int): Boolean{
			for (r in rectList){
				if (x >= r[0] && x < r[2] && y >=r[1] && y < r[3]) return true
			}
			return false
		}
		var result = 0

		for (x in x0 until x1){
			for (y in y0 until y1){
				if (isIn(x,y)) {
					result ++
				}
			}
		}
		numRect = rd.readInt()
		println(result)
	}
}