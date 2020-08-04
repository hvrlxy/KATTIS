import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		rd.readLn()
		val numSteps = rd.readInt()

		val aMap = mutableMapOf<Pair, MutableList<Pair>>()
		var currentPos = (0 to 0)
		repeat(numSteps){
			var direction = rd.readLn()
			if(currentPos !in aMap){
				if (direction = "S"){
					aMap[currentPos] = mutableListOf<Pair>()
					aMap[currentPos]!!.add(currentPos.first + 1 to currentPos.second)
					currentPos = (currentPos.first + 1 to currentPos.second)
				}
				if (direction = "N"){
					aMap[currentPos] = mutableListOf<Pair>()
					aMap[currentPos]!!.add(currentPos.first - 1 to currentPos.second)
					currentPos = (currentPos.first - 1 to currentPos.second)
				}
				if (direction = "E"){
					aMap[currentPos] = mutableListOf<Pair>()
					aMap[currentPos]!!.add(currentPos.first to currentPos.second + 1)
					currentPos = (currentPos.first to currentPos.second + 1)
				}
				if (direction = "W"){
					aMap[currentPos] = mutableListOf<Pair>()
					aMap[currentPos]!!.add(currentPos.first to currentPos.second - 1)
					currentPos = (currentPos.first to currentPos.second - 1)
				}
			}

		}
	}
}