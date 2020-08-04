import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var endFile = false
	while (!endFile){
		var guessNum = rd.readInt()
		if (guessNum == 0) return
		var instruction = rd.readLn()
		var min = Int.MIN_VALUE
		var max = Int.MAX_VALUE
		while (instruction != "right on"){
			if (instruction == "too high" && guessNum < max) max = guessNum
			if (instruction == "too low" && guessNum > min) min = guessNum

			guessNum = rd.readInt()
			instruction = rd.readLn()
		}

		if (instruction == "right on"){
			if (guessNum > min && guessNum < max) println("Stan may be honest") else println("Stan is dishonest")
		}
	}
}