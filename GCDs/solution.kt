import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

/* Look at the constraint of the problem: a <= 100 -> Precomputation of gcd(a,b)
for all a, b <= 100 */

fun gcd(a: Int, b: Int): Int{
    if (a == 0) return b else return gcd(b % a, a)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val array = IntArray(rd.readInt()){rd.readInt()}

    val gcdSet = BooleanArray(101)

    val table = Array<IntArray>(101){IntArray(101){0}}

    for (i in 0 .. 100){
    	for (j in i .. 100){
    		table[i][j] = gcd(i,j)
    		table[j][i] = table[i][j]
    	}
    }

    var count = 0

    for (i in 0 until array.size){
    	var dp = IntArray(array.size){0}
    	for (j in i until array.size){
    		if (i == j){
    			dp[j] = array[j]
    		}
    		else{
    			dp[j] = table[dp[j - 1]][array[j]]
    		}
    		if (!gcdSet[dp[j]]){
    			gcdSet[dp[j]] = true
    			count++
    		}
    	}
    }

    println(count)
}