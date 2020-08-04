import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var string = rd.readLine()
	while (string != null){
		val a = string.split(" ")
		val row = a[0]
		val column = a[1]

		val aMap = mutableMapOf<Pair, Int>()
		val adj = mutableListOf<MutableList<Int>>()

		for (i in 0 until row){
			val aString = rd.readLn()
			for (j in 0 until column){
				if (aString[j])
			}
		}
	}

}