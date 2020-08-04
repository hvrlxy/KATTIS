import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numPeople = rd.readInt()

	val a1 = rd.readInts()
	val array = Array<Pair<Long,Long>>(numPeople){1L + it to a1[it]}.toMutableList()

	var counter : Long = 1L

	while (array.size >1){
		counter = (array[counter.toInt()].second - 1L) % array.size
		array.removeAt(counter.toInt())
	}
	println(array[0].first)
}