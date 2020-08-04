import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var result = StringBuilder()

	repeat(rd.readInt()){
		val a = rd.readStrings()

		val x = a[2].toDouble()

		var bestFit = Double.MAX_VALUE

		var P = 0
		var Q = 0
		for (q in 1 .. a[1].toInt()){
			val p = (q * x).roundToInt()

			val pq = p.toDouble()/q.toDouble()

			if (abs(x - pq) < bestFit){
				//println("pq: $pq, abs(x - pq): ${abs(x - pq)}")
				P = p
				Q = q
				bestFit = abs(x - pq)
			}
		}

		result.append("${a[0]} $P/$Q\n")
	}
	print(result)
}