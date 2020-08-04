import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun min(alist: MutableList<Int>): Int{
	alist.sort()
	return alist[0]
}

fun canEscape(S: Int, x: Int, y: Int, dx: Int, dy: Int): Int{
	if (!isBlack(S, x, y)) return 0

	var result = Int.MAX_VALUE

	for (i in 1 until S){
		for (j in S + 1 until 2 * S){
			val s1 = modLinearEquation(dx, (i - x + 2 * S) % (2 * S), 2 * S)
			val s2 = modLinearEquation(dy, (j - y + 2 * S) % (2 * S), 2 * S)

			val alist = s1.intersect(s2).toMutableList()

			// if (i == 63 && j == 111){
			// 	println(s1)
			// 	println(s2)
			// }

			if (alist.isEmpty()) continue
			var d = min(alist)

			if (d > 0 && d < result){
				result = d
			}
		}
	}

	for (i in S + 1 until 2 * S){
		for (j in 1 until S){
			val s1 = modLinearEquation(dx, (i - x + 2 * S) % (2 * S), 2 * S)
			val s2 = modLinearEquation(dy, (j - y + 2 * S) % (2 * S), 2 * S)

			val alist = s1.intersect(s2).toMutableList()

			if (alist.isEmpty()) continue
			var d = min(alist)

			if (d > 0 && d < result){
				result = d
			}
		}
	}

	return result
}

fun isBlack(S: Int, x: Int, y: Int): Boolean = (x % (2 * S) <= S && y % (2 * S) <= S) || (x % (2 * S) >= S && y % (2 * S) >= S)

fun modLinearEquation (a: Int, b: Int, n: Int): MutableSet<Int>{
	/* This function solve the following linear equation:
				ax = b (mod n)
	*/
	val result = mutableSetOf<Int>()

	val d = extendedEuclid(a,n)
	if (b % d.first == 0){
		val x0 = d.second * (b / d.first) % n
		for (i in 0 until d.first){
			result.add((x0 + 1 * i * (n / d.first) + n) % n)
		}
	}
	return result
}

fun extendedEuclid (a: Int, b: Int): Triple<Int, Int, Int>{
	if (b == 0) return (Triple(a,1,0))
	val dPrime = extendedEuclid(b, a%b)
	return Triple(dPrime.first, dPrime.third, dPrime.second - dPrime.third * (a/b))
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var s = rd.readInts()

    val result = StringBuilder()

    while (s[0] + s[1] + s[2] + s[3] + s[4] != 0){
    	val S = s[0]
    	val x = s[1]
    	val y = s[2]
    	val dx = s[3]
    	val dy = s[4]

    	val d = canEscape(S, x, y, dx, dy)

    	if (d == Int.MAX_VALUE) result.append("The flea cannot escape from black squares.\n")
    	else {
    		//result.append(isBlack(S, x + d * dx, y + dy * d))
    		result.append("After $d jumps the flea lands at (${x + dx * d}, ${y + dy * d}).\n")
    	}
    	//return
    	s = rd.readInts()
    }
    print(result)
}