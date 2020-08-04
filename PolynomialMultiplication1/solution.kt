import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	var result = StringBuilder()
	repeat(rd.readInt()){
		val degree1 = rd.readInt()

		val p1 = rd.readInts().toMutableList()

		val degree2 = rd.readInt()
		result.append("${degree1 + degree2}\n")
		val p2 = rd.readInts().toMutableList()

		p1.reverse()
		p2.reverse()

		val newDegree = degree2 + degree1

		val resultP = IntArray(newDegree + 1){0}
		for (i in 0 .. degree1){
			for (j in 0 .. degree2){
				resultP[newDegree - i - j] += p1[i] * p2[j]
			}
		}
		result.append("${resultP.joinToString(separator = " ")}\n")
	}
	print(result)
}