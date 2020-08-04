import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val denominator = rd.readInt()

    val c = rd.readInts()

    val dp = IntArray(c[denominator - 1] * 2 + 1){Int.MAX_VALUE}
    val greedy = IntArray(c[denominator - 1] * 2 + 1){0}

    for (i in c){
    	// base case
    	dp[0] = 0
    	dp[i] = 1
    	greedy[i] = 1
    }

    //Solve the dp approach first
    for (i in 2 until dp.size){
		for (j in c){
			if (i - j > 0) dp[i] = min(dp[i], dp[i - j] + 1)
		}
    }

    //Solve the greedy approach
    for (i in 1 until greedy.size){
    	for (j in denominator - 1 downTo 0){
    		if (i >= c[j]) {
    			greedy[i] = greedy[i - c[j]] + 1
    			break
    		}
    	}
    }

    for (i in 0 until greedy.size){
    	if (dp[i] != greedy[i]) return println("non-canonical")
    }
    println("canonical")
}