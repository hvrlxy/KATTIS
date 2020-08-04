import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCards = rd.readInt()

	val cardsArray = rd.readInts().toIntArray()

	val sortedArray = cardsArray.sorted().toIntArray()

	//find the starting position
	var startIdx = Int.MAX_VALUE
	var endIdx = -1

	for (i in 0 until numCards){
		if (cardsArray[i] != sortedArray[i] && i < startIdx){
			startIdx = i
		}
		if ((cardsArray[i] != sortedArray[i] && i > endIdx)){
			endIdx = i
		}
	}

	//if the list is sorted, then there are nothing else to do
	if (startIdx == Int.MAX_VALUE) return println("$numCards $numCards")

	var newArray = IntArray(numCards){0}
	for (i in 0 until numCards){
		newArray[i] = cardsArray[i]
	}
	var count = 0
	for (i in startIdx .. endIdx){
		newArray[i] = cardsArray[endIdx - count]
		count ++
	}

	for (i in 0 until numCards){
		if (newArray[i] != sortedArray[i]) return println("impossible")
	}
	println("${startIdx + 1} ${endIdx + 1}")
}