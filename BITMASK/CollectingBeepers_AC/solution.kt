import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun dist(a: Point, b: Point): Int = abs(a.x - b.x) + abs(a.y - b.y)

val INF = 1000000000

data class Point(val x: Int, val y: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
    	val size = rd.readInts()

    	val pList = mutableListOf<Point>()
    	val starting = rd.readInts()
    	pList.add(Point(starting[0], starting[1]))

    	val numPoint = rd.readInt()
    	repeat(numPoint){
    		val aline = rd.readInts()
    		pList.add(Point(aline[0], aline[1]))
    	}

    	val total = 1 shl (numPoint + 1)

    	val dp = Array<IntArray>(total){IntArray(numPoint + 1){INF}}

    	dp[0][0] = 0 //base case

    	for (m in 1 until total){
    		for (i in 0 .. numPoint){

    			for (j in 0 .. numPoint){
	    			if (m and (1 shl j) != 0){
	    				dp[m][i] = min(dp[m][i], dp[m and (1 shl j).inv()][j] + dist(pList[i], pList[j]))
	    			}
	    		}
    		}
    	}

    	println(dp[total - 1][0])
    }
}