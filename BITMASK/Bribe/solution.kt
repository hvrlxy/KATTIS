import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun twoPower(k: Int): Int = 1 shl k

data class Henchmen (val m: Int, val p: Double)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    repeat(numCase){
    	val aline = rd.readInts()

    	val n = aline[0] //number of henchmen
    	val c = aline[1] //number of people need to convert
    	val m = aline[2] //number of money have

    	val menList = mutableListOf<Henchmen>()

    	repeat(n){
    		val aline = rd.readInts()
    		menList.add(Henchmen(aline[0], aline[1] / 100.0))
    	}

    	val total = twoPower(n)

    	fun money(M: Int): Int{
    		var result = 0
    		for (j in 0 until n){
    			if ((M and (1 shl j)) != 0) result += menList[j].m
    		}
    		return result
    	}

    	val dp = Array<DoubleArray>(total){DoubleArray(c + 1){-1.0}}
    	
    	fun mem (M: Int, C: Int): Double{
    		val I = money(M)
    		if (I > m) return 0.0
    		if (C == 0) {
    			dp[M][C] = 1.0
    			return dp[M][C]
    		}

    		if (M == 0 && C > 0) {
    			dp[M][C] = 0.0
    			return dp[M][C]
    		}

    		if (dp[M][C] >= 0.0) return dp[M][C]
    		
    		for (j in 0 until n){
    			if (M and (1 shl j) != 0){
    				val p = mem(M and (1 shl j).inv(), C - 1) * menList[j].p + mem(M and (1 shl j).inv(), C) * (1 - menList[j].p) // if fail
    				dp[M][C] = max(dp[M][C], p)
    			}
    		}
	    	

	    	return dp[M][C]
    	}
    	println(mem(0, c))

    	var bestP = 0.0
    	for (M in 0 until total){
    		if (mem(M, c) > bestP) bestP = dp[M][c]
    	}
    	println(bestP)
    }
}