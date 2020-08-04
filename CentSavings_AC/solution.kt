import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun round (n: Int): Int{
	if (n % 10 < 5) return n - n% 10 else return n + 10 - n % 10
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a1 = rd.readInts()
    val n = a1[0]
    val d = a1[1]
    val a = rd.readInts()

    val dp = Array<IntArray>(n){IntArray(d + 1){0}}
    val prefixSum = IntArray(n){0}
    for (i in 0 until n){
    	if (i == 0) prefixSum[i] = a[i]
    	else prefixSum[i] = prefixSum[i - 1] + a[i]
    }

    for (i in 0 until n){
    	for (j in 0 .. d){
    		if (i == 0) dp[i][j] = a[i]
    		else if (j == 0) dp[i][j] = prefixSum[i]
    		else {
    			dp[i][j] = min(round(dp[i - 1][j - 1]) + a[i], dp[i - 1][j] + a[i])
    		}
    	}
    }
    println(round(dp[n - 1][d]))
}
