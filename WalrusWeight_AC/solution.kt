import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numWeights = rd.readInt()

    val a = mutableListOf<Int>()
    var total = 0
    repeat(numWeights){
    	val w = rd.readInt()
    	total += w
    	a.add(w)
    }
 

    fun closest(a: Int, b: Int): Int{
    	// this function takes 2 values and return the one closer to 1000
    	val adiff = abs(1000 - a)
    	val bdiff = abs(1000 - b)

    	if (adiff < bdiff) return a
    	else if (adiff > bdiff) return b
    	else{
    		return max(a,b)
    	}
    }

    val minOpt = Array<IntArray>(numWeights){IntArray(1000 + 1){0}}
    val maxOpt = Array<IntArray>(numWeights){IntArray(1001){total}}

    /*
    	Let minOpt[i][j] = sum of the biggest subset which has sum smaller than j of the subaray a[0 .. i]
    	Hence, minOpt[i][j] = max(minOpt[i - 1][j], minOpt[i][j - a[i]] + a[i])

    	Let maxOpt[i][j] = sum of the smallest subset of the subarray a[0 .. i] with sum bigger than j

    */

    for (i in 0 until numWeights){
    	for (j in 0 .. 1000){
    		if (j == a[i]) minOpt[i][j] = j
    		else if (i == 0){
    			if (j >= a[i]) minOpt[i][j] = a[0]
    		}
    		else{
    			minOpt[i][j] = max(minOpt[i][j], minOpt[i - 1][j])
    			if (j >= a[i]){
    				minOpt[i][j] = max(minOpt[i][j], minOpt[i - 1][j - a[i]] + a[i])
    			} 
    		}
    	}
    }


    for (i in 0 until numWeights){
    	for (j in 0 .. 1000){
    		if (j <= a[i]) {maxOpt[i][j] = min(a[i], maxOpt[i][j])}
    		if (i == 0){
    			continue
    		}
    		else{
    			maxOpt[i][j] = min(maxOpt[i - 1][j], maxOpt[i][j])
    			if (j >= a[i]){
    				maxOpt[i][j] = min(maxOpt[i][j], maxOpt[i - 1][j - a[i]] + a[i])
    			}
    		}
    	}
    }

    println(closest(minOpt[numWeights - 1][1000], maxOpt[numWeights - 1][1000]))
}