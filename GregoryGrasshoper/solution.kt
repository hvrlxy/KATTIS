import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val stats = rd.readInts()

		if (stats[0] == 2){
			if (abs(stats[5] - stats[3]) % 2 != 0) println("impossible")
			else{
				val k = abs(stats[5] - stats[3]) % 2
				if (k % 2 == 0 && stats[2] == stats[4]) println(abs(stats[5] - stats[3]) / 2)
				else if (k % 2 != 0 && stats[2] != stats[4]) println(abs(stats[5] - stats[3]) / 2)
				else println("impossible")
			}
		}
		if (stats[1] == 2){
			if (abs(stats[4] - stats[2]) % 2 != 0) println("impossible")
			else{
				val k = abs(stats[4] - stats[2]) % 2
				if (k % 2 == 0 && stats[3] == stats[5]) println(abs(stats[4] - stats[2]) / 2)
				else if (k % 2 != 0 && stats[3] != stats[5]) println(abs(stats[4] - stats[2]) / 2)
				else println("impossible")
			}
		}
		if (stats[0] == 1 || stats[1] == 1){
			if (stats[2] == stats[4] && stats[3] == stats[5]) println("0") else println("impossible")
		}

		else{
			val Q = mutableListOf<Pair<Int, Int>>()
			val dist = Array<IntArray>(stats[0]){IntArray(stats[1]){Int.MAX_VALUE}}
			dist[stats[2]][stats[3]] = 0
			
		}
	}
}