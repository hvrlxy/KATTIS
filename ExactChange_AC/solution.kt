import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
    	val target = rd.readInt()
    	val numBills = rd.readInt()
    	val a = IntArray(numBills){rd.readInt()}

    	val total = a.sum()

    	val maxOpt = Array<IntArray>(numBills){IntArray(target + 1){total}}
    	val billOpt = Array<IntArray>(numBills){IntArray(target + 1){numBills}}

    	for (i in 0 until numBills){
	    	for (j in 0 .. target){
	    		if (j <= a[i]) {
	    			if (a[i] <= maxOpt[i][j]){
	    				maxOpt[i][j] = a[i]
	    				billOpt[i][j] = 1
	    			}
	    		}
	    		if (i == 0) continue
	    		else{
	    			if (maxOpt[i - 1][j] < maxOpt[i][j]){
	    				maxOpt[i][j] = maxOpt[i - 1][j]
	    				billOpt[i][j] = billOpt[i - 1][j]
	    			}
	    			else if (maxOpt[i - 1][j] == maxOpt[i][j] && billOpt[i - 1][j] < billOpt[i][j]){
	    				billOpt[i][j] = billOpt[i - 1][j]
	    			}
	    			if (j >= a[i]){
	    				if (maxOpt[i - 1][j - a[i]] + a[i] < maxOpt[i][j]){
	    					maxOpt[i][j] = maxOpt[i - 1][j - a[i]] + a[i]
	    					billOpt[i][j] = billOpt[i - 1][j - a[i]] + 1
	    				}
	    				else if (maxOpt[i - 1][j - a[i]] + a[i] == maxOpt[i][j] && billOpt[i - 1][j - a[i]] + 1 < billOpt[i][j]){
	    					billOpt[i][j] = billOpt[i - 1][j - a[i]] + 1 
	    				}
	    			}
	    		}
	    	}
	    }
	    println("${maxOpt[numBills - 1][target]} ${billOpt[numBills - 1][target]}")
    }

}