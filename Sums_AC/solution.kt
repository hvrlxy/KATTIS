import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val twoList = mutableListOf<Int>()

fun divisor(n: Int): MutableList<Int>{
	val result = mutableListOf<Int>()

	val sq = sqrt(n.toDouble()).toInt()

	for (i in 2 .. sq + 1){
		if (n % i == 0) {
			result.add(i)
			result.add(n / i)
		}
	}

	result.sort()

	return result
}

fun solve(n: Int): Pair<Int, Int>{
	//return Pair(low, high), else Pair(-1, -1) if no solution exists

	if (n in twoList) return (-1 to -1)

	val dList = divisor(2 * n)

	for (d in dList){
		val twicek = (2 * n / d + 1 - d)
		if (twicek % 2 == 1) continue
		else{
			val k = twicek / 2
			return (k to k + d - 1)
		}
	}

	return (-1 to -1)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    for (i in 0 until 31){
    	twoList.add(1 shl i)
    }

    val result = StringBuilder()

    repeat(numCase){
    	val n = rd.readInt()
    	val range = solve(n)

    	if (range.first == -1) println("IMPOSSIBLE")
    	else{
	    	val alist = mutableListOf<Int>()

	    	for (i in range.first .. range.second){
	    		alist.add(i)
	    	}
	    	result.append("$n = " + alist.joinToString(separator = " + ") + "\n")
	    }
    }

    print(result)
}