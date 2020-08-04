import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun powerOfTen(k: Int): Long{
	if (k == 0) return 1L
	else{
		val half = powerOfTen(k / 2)
		if (k % 2 == 0) return half * half else return half * half * 10L
	}
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    //dp[i] records the sum of all digits in the interval [0 .. 10^i - 1]
    val dp = LongArray(16){0L}
    dp[1] = 45
    for (i in 2 until 16){
    	dp[i] = 45 * powerOfTen(i - 1) + dp[i - 1] * 10L
    }

    //println(dp.joinToString())

    // tillN calculate the sum of all digits in the interval [0 .. n] for n <= 9
    val tillN = LongArray(10){0L}
    for (i in 1 until 10){
    	tillN[i] += tillN[i - 1] + i
    }

    fun sum (n: Long): Long{
    	if (n <= 0L) return 0L 
    	else if (n < 10L) return tillN[n.toInt()]
    	val level = n.toString().length - 1
    	val tenth = powerOfTen(level)

    	val k = (n / tenth).toInt()
    	val p = dp[level] * k + (tillN[k] - k) * tenth
    	//println(p)

    	return p + k * (n % tenth + 1) + sum(n % tenth)
    }

    val numCase = rd.readInt()

    repeat(numCase){
    	val a = rd.readInts()

    	println(sum(a[1]) - sum(a[0] - 1))
    }
}