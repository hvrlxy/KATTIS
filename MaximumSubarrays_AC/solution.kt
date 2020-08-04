import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val n = s[0]
    val k = s[1]
    val array = rd.readInts()

    val maxHere = Array<LongArray>(n){LongArray(k + 1){0L}}
    val max = Array<LongArray>(n){LongArray(k + 1){0L}}

    for (i in 0 until n){
    	for (j in 1 .. min(i + 1, k)){
    		if (i == 0 && j == 1) {
    			maxHere[i][j] = array[0].toLong()
    			max[i][j] = array[0].toLong()
    		}
    		else if (i + 1 == j){
    			maxHere[i][j] = maxHere[i - 1][j - 1] + array[i]
    			max[i][j] = max[i - 1][j - 1] + array[i]
    		}
    		else{
    			maxHere[i][j] = max(max[i - 1][j - 1] + array[i], maxHere[i - 1][j] + array[i])
    			max[i][j] = max(maxHere[i][j], max[i - 1][j])
    		}
    	}
    }

    println(max[n - 1][k])
}