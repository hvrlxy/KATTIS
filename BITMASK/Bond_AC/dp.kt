import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun twoPower(k: Int): Int{
	if (k == 0) return 1
	val half = twoPower(k / 2)
	if (k % 2 == 0) return half * half else return half * half * 2
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numBonds = rd.readInt()

    val matrix = Array<DoubleArray>(numBonds){DoubleArray(numBonds){0.0}}

    for (i in 0 until numBonds){
    	val aline = rd.readInts()
    	for (j in 0 until numBonds){
    		matrix[i][j] = aline[j].toDouble()/100.0
    	}
    }

    val total = twoPower(numBonds)

    /* 
    	Let m be the integer that represents the bitmask of a subset.
    	Let i be the current mission that we are at (assuming that all 
    	mission from 0 .. i - 1 is already assigned)
    	Let dp[m][i] be the success probability of assigning the first i missions
    	to according to the configuration m
    */

    val dp = Array<DoubleArray>(total){DoubleArray(numBonds){-1.0}}

    fun mem(m: Int, i: Int): Double{
    	if (i < 0) return 1.0
    	if (m == 0 ) {
    		dp[m][i] = 1.0
    		return dp[m][i]
    	}

    	if (dp[m][i] >= 0.0) {
    		return dp[m][i]
    	}
    	//recurrence
    	for (j in 0 until numBonds){
    		//println("$m ${1 shl j}")
    		//println(m and (1 shl j))
    		if (m and (1 shl j) != 0){
    			dp[m][i] = max(dp[m][i], mem(m and (1 shl j).inv(), i - 1) * matrix[j][i])
    			//println(dp[m][i])
    		}
    		
    	}

    	return dp[m][i]
    }

    println(100 * mem(total - 1, numBonds - 1))

    // for (i in 0 until total){
    // 	println(dp[i].joinToString())
    // }
}