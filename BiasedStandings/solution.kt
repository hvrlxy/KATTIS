import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readLong() = readLn().toLong() // single Long
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Longs

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCases = rd.readLong().toInt()

	repeat(numCases){
		rd.readLn()
		val numTeams = rd.readLong().toInt()
		val aList = mutableListOf<Long>()
		repeat(numTeams){
			val a = rd.readStrings()
			aList.add(a[a.size - 1].toLong())
		}

		aList.sort()
		val array = LongArray(numTeams){(it + 1).toLong()}
		var result = 0L
		for (i in 0 until numTeams){
			result += kotlin.math.abs(array[i].toLong() - aList[i].toLong())
		}
		println(result)
	}
}