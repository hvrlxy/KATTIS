import java.io.*
import kotlin.math.pow
import kotlin.math.roundToInt

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()

	fun findReverse(n: Int): Int{
		var result = 0
		var n1 = n
		val log10 = kotlin.math.log10(n1.toFloat())
		var count = 0
		if (log10.roundToInt() - log10 > 0) count = log10.roundToInt() - 1 else count = log10.toInt()
		//println("$n $count")
		while (n1 != 0){
			val r = n1 % 10
			if (r == 0 || r == 1 || r == 2 || r == 8 || r == 5) result +=  r * 10.0.pow(count).toInt()
			else if (r == 3 || r == 4 || r == 7) return Int.MAX_VALUE
			else if (r == 6) result += 9 * 10.0.pow(count).toInt()
			else if (r == 9) result += 6 * 10.0.pow(count).toInt()
			n1 /= 10
			count --
		}
		return result
	}

	val numList = rd.readInts().toMutableList()
	val reverseList = numList.map{findReverse(it)}

	for (i in 0 until numList.size){
		for (j in i+1 until numList.size){
			if (numList[i] + reverseList[j] == a[1]) return println("YES")
			if (numList[i] + numList[j] == a[1]) return println("YES")
			if (reverseList[i] + reverseList[j] == a[1]) return println("YES")
		}
	} 
	println("NO")
}