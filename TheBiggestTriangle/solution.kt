import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numLines = rd.readInt()

    val linesArray = Array<IntArray>(numLines){rd.readInts().toIntArray()}

    fun slope(a: IntArray): Double? {
    	if (a[2] - a[0] == 0) return null
    	return (a[3] - a[1]).toDouble() / (a[2] - a[0])
    }

    fun findIntersection(a: IntArray, b: IntArray): Pair<Double, Double>?{
    	val slopeA = slope(a)
    	val slopeB = slope(b)

    	if (slopeA == slopeB || (slopeA != null && slopeB != null && abs(slopeA - slopeB) <= 1e-8)) {
    		return null

    	}

    	else if(slopeA == null){
    		val bB = b[1] - slopeB!! * b[0]
    		return (a[0].toDouble() to slopeB * a[0] + bB)
    	}

    	else if (slopeB == null){
    		val bA = a[1] - slopeA * a[0] 
    		val x = a[0].toDouble()
    		val y = x * slopeA + bA
    		return (x to y)
    	}

    	else{
    		val bA = a[1] - slopeA * a[0] 
    		val bB = b[1] - slopeB * b[0]

    		val x = (bB - bA) / (slopeA - slopeB)
    		val y = x * slopeA + bA
    		return (x to y)
    	}
    }

    fun findArea(a: Pair<Double, Double>, b: Pair<Double, Double>, c: Pair<Double, Double>): Double{
    	fun dist(a: Pair<Double, Double>, b: Pair<Double, Double>): Double = sqrt((a.first - b.first).pow(2.0) + (a.second - b.second).pow(2.0))
    	return (dist(a,b) + dist(b,c) + dist(a,c))
    }

    fun isTriangle(a: Pair<Double, Double>, b: Pair<Double, Double>, c: Pair<Double, Double>): Boolean{
    	val area = abs(a.first * (b.second - c.second) + b.first * (c.second - a.second) + c.first * (a.second - b.second)) / 2.0
    	if (area > 1e-8) return true else return false
    }

    var result : Double? = null

    for (i in 0 until numLines - 2){
    	for (j in i + 1 until numLines - 1){
    		for (k in j + 1 until numLines){
    			val isect1 : Pair<Double, Double>? = findIntersection(linesArray[i], linesArray[j])
    			val isect2 : Pair<Double, Double>? = findIntersection(linesArray[j], linesArray[k])
    			val isect3 : Pair<Double, Double>? = findIntersection(linesArray[i], linesArray[k])

    			if (isect3 != null && isect2 != null && isect1 != null && isTriangle(isect1, isect2, isect3)){
    				val area = findArea(isect1, isect2, isect3)
    				if (result == null || (area > 1e-8 && area > result)) {
    					result = area
    				}
    			}
    		}
    	}
    }

    if (result == null || result <= 1e-8) println("no triangle") else println(result)
}