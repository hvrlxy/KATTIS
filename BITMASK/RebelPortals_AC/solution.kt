import java.io.*
import kotlin.math.*

// must use bottom-up, memoization takes too long

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 1000000000.0
data class Planet (val x: Int, val y: Int, val z: Int)

fun dist (a: Planet, b: Planet): Double = sqrt((a.x - b.x).toDouble() * (a.x - b.x) + (a.y - b.y).toDouble() * (a.y - b.y) + (a.z - b.z).toDouble() * (a.z - b.z))

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()

    val pList = mutableListOf<Planet>()

    val w = Array<DoubleArray>(n){DoubleArray(n){0.0}}

    repeat(n){
    	val aline = rd.readInts()
    	pList.add(Planet(aline[0], aline[1], aline[2]))
    }

    // calculate the distance between pairs of planets
    for (i in 0 until n){
    	for (j in i + 1 until n){
    		w[i][j] = dist(pList[i], pList[j])
    		w[j][i] = w[i][j]
    	}
    }

    val total = 1 shl n

    val dp = Array<Array<DoubleArray>>(total){Array<DoubleArray>(n){DoubleArray(2){INF}}}
    dp[0][0][1] = 0.0 // the starting position

    for (m in 1 until total){
    	for (j in 0 until n){
    		for (k in 0 until 2){
    			for (i in 0 until n){
		    		if (m and (1 shl i) != 0){
		    			// if the i planet is visited

		    			if (k == 0){
		    				// if we use the portals
		    				dp[m][j][k] = min(dp[m][j][k], dp[m and (1 shl i).inv()][i][1] + 0)
		    			}

		    			dp[m][j][k] = minOf(dp[m][j][k], dp[m and (1 shl i).inv()][i][1] + w[i][j],
		    				dp[m and (1 shl i).inv()][i][0] + w[i][j])
		    		}
		    	}
    		}
    	}
    }

    println(dp[total - 1][0][1])
}