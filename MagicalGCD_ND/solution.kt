import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() }.toList() // list of ints

fun gcd(x: Long, y: Long): Long{
	if (x == 0L) return y
	return gcd(y.rem(x), x)
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	repeat(numCase){
		val n = rd.readInt()

		val numList = rd.readInts()

		var result = 0L

		val opt = LongArray(n){0L}
		for (i in 0 until n){
			opt[i] = numList[i]
			if (numList[i] > result) result = numList[i]
			for (j in i + 1 until n){
				opt[j] = gcd(opt[j - 1], numList[j])
				val magicalGCD = opt[j] * (j - i + 1)
		 		if (magicalGCD > result) result = magicalGCD
			}
		}
		println(result)
	}
}