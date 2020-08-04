import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val board = Array<String>(8){rd.readLn()}
	val instruction = rd.readLn()

	var pos = (7 to 0)
	var direction = "R"

	fun turn(x: Char): Boolean{
		if (x == 'F'){
			if (direction == "R") if (board[pos.first][pos.])
		}
	}


	for (i in 0)
}