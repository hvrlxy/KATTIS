import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val dp = Array<Array<IntArray>>(151){Array<IntArray>(251){IntArray(51){-1}}}

    var total = 0

    fun mem(c: Int, f: Int, t: Int): Int{
    	if (c < 0) return 0

    	if (dp[c][f][t] != -1) return dp[c][f][t]

    	val o = total - f * 5 - t * 10

    	if (c == 0){
    		dp[c][f][t] = 0
    		return dp[c][f][t]
    	}
    	
    	dp[c][f][t] = 8 * c
    	
    	// 5 5 -> 1 1
    	if (f >= 2){
        	dp[c][f][t] = min(dp[c][f][t], mem(c - 1, f - 2, t) + 2)
    	}
    	// 10 1 1 1 -> 5
    	if (t >= 1 && o >= 3){
    		dp[c][f][t] = min(dp[c][f][t], mem(c - 1, f + 1, t - 1) + 4)
    	}
    	// 10 -> 1 1
    	if (t >= 1){
    		dp[c][f][t] = min(dp[c][f][t], mem(c - 1, f, t - 1) + 1)
    	}
    	// 5 1 1 1 
    	if (f >= 1 && o >= 3){
    		dp[c][f][t] = min(dp[c][f][t], mem(c - 1, f - 1, t) + 4)
    	}
    	if (o >= 8){
    		dp[c][f][t] = min(dp[c][f][t], mem(c - 1, f, t) + 8)
    	}

    	return dp[c][f][t]
    }
    val numCase = rd.readInt()
    repeat(numCase){
    	val aline = rd.readInts()
    	total = aline[1] + aline[2] * 5 + aline[3] * 10
    	println(mem(aline[0], aline[2], aline[3]))
    }
}