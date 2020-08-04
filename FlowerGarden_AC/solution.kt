import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

fun generatePrime(upperbound: Int): MutableList<Int>{
	val bArray = java.util.BitSet(upperbound+1)

    val primeSet = mutableListOf<Int>()
    bArray.flip(2, upperbound + 1)

    val sq = sqrt(upperbound.toDouble()).roundToInt()
    for (i in 2 .. sq){
        if (bArray[i]){
            var j = i
            while (i * j <= upperbound){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    for (i in 2 .. bArray.size()){
    	if (bArray[i]) primeSet.add(i)
    }

    return primeSet
}

data class Point(val x: Double, val y: Double)

fun dist (a: Point, b: Point): Double = sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val pList = generatePrime(20000)

    val numCase = rd.readInt()
    repeat(numCase){
    	val s = rd.readInts()

    	val N = s[0]
    	val D = s[1]

    	val pointList = mutableListOf<Point>()
    	pointList.add(Point(0.0,0.0))

    	repeat(N){
    		val a = rd.readStrings().map{it.toDouble()}
    		pointList.add(Point(a[0], a[1]))
    	}

    	var totalDistance = 0.0
    	var totalFlower = 0
    	for (i in 1 .. N){
    		val d = dist(pointList[i], pointList[i - 1])

    		if (totalDistance + d > D) break
    		else{
    			totalDistance += d
    			totalFlower ++
    		}
    	}

    	var result = 0
    	for (p in pList){
    		if (p <= totalFlower) result = p else break
    	}
    	println(result)
    }
}