import java.io.*
private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	repeat(numCase){
		val a = rd.readStrings()
		if (a.size == 1 || a[1] == "") println()
		else{ 
			val numSlips = a[0].toInt()

			var length = a[1].length
			var isBottom = false
			var top = 0
			var bottom = 0

			for (i in 0 until numSlips){
				if (length/4 == 0) break
				if (isBottom) bottom += length/4 else top += length/4
				length -= length/4
				isBottom = !isBottom
			}

			println(a[1].substring(top, a[1].length - bottom))
		}
	}
}