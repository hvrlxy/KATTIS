import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	repeat(rd.readInt()){
		val numCustomers = rd.readInt()

		val aList = mutableListOf<Long>()
		repeat(numCustomers){
			val a = rd.readInts().toMutableList()
			aList.add(a.sum() - a[0])
		}

		aList.sort()

		var totalWaitingTime = 0L
		var currentTime = 0L
		for (i in aList){
			totalWaitingTime += currentTime + i
			currentTime += i
		}

		println(totalWaitingTime.toDouble() / numCustomers)
	}
}