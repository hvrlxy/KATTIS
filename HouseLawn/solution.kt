import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(",") // list of strings
private fun BufferedReader.readInts() = readLn().split(" ").map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val size = a[0]
	val aList = mutableListOf<Pair<String, Int>>()

	repeat(a[1]){
		val lawnMover = rd.readStrings()

		//val name = lawnMover[0]

		val m = size.toDouble() / (lawnMover[2].toInt() * lawnMover[3].toInt())

		if ((lawnMover[3].toInt() + lawnMover[4].toInt()) * m <= 10080.0){
			aList.add(lawnMover[0] to lawnMover[1].toInt())
		}
	}
	if (aList.isEmpty()) return println("no such mower")
	aList.sortBy{it.second}
	for (i in aList){
		if (i.second == aList[0].second) println(i.first) else return
	}
}