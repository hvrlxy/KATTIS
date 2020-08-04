import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toDouble() } // list of ints

val INF = 1000000000.0
data class Point(val x: Double, val y: Double)

fun dist(a: Point, b: Point): Double = sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aline = rd.readInts()
    val p0 = Point(aline[0], aline[1])

    val numSpots = rd.readInt()

    val pList = mutableListOf<Point>()
    repeat(numSpots){
    	aline = rd.readInts()

    	pList.add(Point(aline[0], aline[1]))
    }

    //calculate dist from every point to p0
    val dist0 = DoubleArray(numSpots){dist(p0, pList[it])}

    //calculate distance between pairs of spots
    val w = Array<DoubleArray>(numSpots){DoubleArray(numSpots){0.0}}

    for (i in 0 until numSpots){
    	for (j in 0 until numSpots){
    		if (i == j) continue
    		w[i][j] = dist(pList[i], pList[j])
    	}
    }


    // println(dist0.joinToString())
    // for (i in 0 until numSpots){
    // 	println(w[i].joinToString())
    // }

    val total = 1 shl numSpots
    val dp = Array<Array<DoubleArray>>(total){Array<DoubleArray>(numSpots){DoubleArray(2){INF}}}

    /* 
    dp[m][i][k]: m denotes the subset of the already visited spots.
    i is the current spots we are at, and k denotes the number of chicken the fox
    carried after arriving at spot i.
    k = 1 : have one chicken left after hiding one at spot i
    k = 0 : run out of chicken and have to go back to the starting point
	
	base case:
	for (i in 0 until numSpots){
		for (k in 0 until 2){
			dp[0][i][k] = dist0[i]
		}
	}

    recurrence:

    dp[m][i][k] = min(dp[m and (1 shl j).inv()][j][1] + w[i][j], dp[m][i][k]) - if k == 0
    dp[m][i][k] = min(dp[m and (1 shl j).inv()][j][0] + dist0[i] + dist0[j], dp[m][i][k])
    */

    for (i in 0 until numSpots){
		for (k in 0 until 2){
			dp[0][i][k] = dist0[i]
		}
	}

    for (m in 1 until total){
    	for (i in 0 until numSpots){
    		for (k in 0 until 2){

    			for (j in 0 until numSpots){
    				if (m and (1 shl j) != 0){

    					if (k == 0){
    						dp[m][i][k] = minOf(dp[m and (1 shl j).inv()][j][1] + w[i][j],
    							dp[m and (1 shl j).inv()][j][0] + dist0[i] + dist0[j],
    							dp[m][i][k])
    					}
    					else if (k == 1){
    						dp[m][i][k] = minOf(dp[m and (1 shl j).inv()][j][0] + dist0[i] + dist0[j],
    							dp[m and (1 shl j).inv()][j][1] + dist0[i] + dist0[j],
    							dp[m][i][k])
    					}

    				}
    			}
    		}
    	}
    }

    var result = INF

    for (i in 0 until numSpots){
		if (dp[(total - 1) and (1 shl i).inv()][i][0] < result) result = dp[(total - 1) and (1 shl i).inv()][i][0]
		
	}

	println("%.7f".format(result))
}