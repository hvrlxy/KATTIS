//similar to DA-sort

//import java.util.Scanner
import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main() {
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val cases = rd.readInt()
	repeat(cases){
		var j = 1
		val numDVDs = rd.readInt()
		val a = rd.readInts()
		for (i in 0 until numDVDs){
			if (a[i] == j) j++
		}
		println(numDVDs - j + 1)
	}
}
