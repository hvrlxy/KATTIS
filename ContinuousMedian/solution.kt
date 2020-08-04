//import java.util.Scanner
import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	val r = StringBuilder()
	repeat(rd.readInt()){
		val n = rd.readInt()
		val a1 = rd.readInts()
		val a = mutableListOf<Long>()

		fun findMedian(a: MutableList<Long>): Long{
			if (a.size % 2 == 1) return (a[(a.size + 1)/2 - 1]) else return ((a[a.size / 2] + a[a.size / 2 - 1]) / 2)
		}
		var result = 0L
		var count = 0
		while (count < n){
			a.add(a1[count])
			a.sort()
			result += findMedian(a)
			count ++
		}

		r.append("$result\n")
	}
	print(r)
}