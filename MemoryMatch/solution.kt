import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCards = rd.readInt()
	val numMoves = rd.readInt()

	val aMap = mutableMapOf<String, String>()
	val resultSet = mutableSetOf<String>()
	var totalCardsUp = 0
	var totalPictursKnown = 0

	repeat(numMoves){
		val move = rd.readStrings()
		if (move[2] == move[3]) {
			if (move[2] !in aMap.keys) totalPictursKnown ++ 
			resultSet.remove(move[2])
			totalCardsUp += 2
		}
		else{
			if (move[2] in aMap.keys && move[0] != aMap[move[2]]!!) resultSet.add(move[2])
			else if (move[2] !in aMap.keys) {
				aMap[move[2]] = move[0]
				totalPictursKnown ++
			}

			if (move[3] in aMap.keys && move[1] != aMap[move[3]]!!) resultSet.add(move[3])
			else if (move[3] !in aMap.keys) {
				aMap[move[3]] = move[1]
				totalPictursKnown ++
			}
		}
	}

	if (totalPictursKnown == numCards / 2) return println(totalPictursKnown - totalCardsUp / 2)

	//println(resultSet)
	totalCardsUp += resultSet.size * 2
	if (totalCardsUp + 2 == numCards) println(resultSet.size + 1) else println(resultSet.size)
}