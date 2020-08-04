import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numBoats = rd.readInt()

	val boatLists = IntArray(numBoats){rd.readInt()}

	var i = 0
	var result = 0
	while (i < numBoats){
		var totalBoats = 1
		val timeOpen = boatLists[i] + 1740
		for (j in i + 1 until numBoats){
			if (boatLists[j] > timeOpen) break
			else totalBoats ++
		}
		result += 120 + (20 * totalBoats)
		i += totalBoats
	}
	println(result)
}