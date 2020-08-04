import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numBonds = rd.readInt()

    val m = Array<DoubleArray>(numBonds){DoubleArray(numBonds){0.0}}

    for (i in 0 until numBonds){
    	val aline = rd.readInts()
    	for (j in 0 until numBonds){
    		m[i][j] = aline[j].toDouble()/100.0
    	}
    }

    val d = BooleanArray(numBonds){true}
    var result = 0.0
    fun solve(i: Int, p: Double){
    	if (i == numBonds){
    		if (p > result) result = p
    		return
    	}
    	for (j in 0 until numBonds){
    		if (d[j]){
    			d[j] = false
    			solve(i + 1, p * m[i][j])
    			d[j] = true
    		}
    	}
    }

    solve(0, 1.0)
    println(result * 100)
}