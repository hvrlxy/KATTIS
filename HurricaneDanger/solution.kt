// compute distance between a point and an infinite line

import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

data class Point(val x: Long, val y: Long)

data class Fraction(val n: Long, val d: Long): Comparable<Fraction>{
	override fun compareTo(other: Fraction) = (this.n.toLong() * other.d).compareTo(this.d.toLong() * other.n)

	override fun toString(): String = "($n $d)"
}

fun pointToLineSquared(p0: Point, p1: Point, p2: Point): Fraction{
	// this function calculate the distance between point p0 and the line passes through p1 and p2

	val n = ((p2.y - p1.y) * p0.x - (p2.x - p1.x) * p0.y + p2.x * p1.y - p2.y * p1.x) * ((p2.y - p1.y) * p0.x - (p2.x - p1.x) * p0.y + p2.x * p1.y - p2.y * p1.x)
	val d = (p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x)
	return Fraction(n,d)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    // val f1 = Fraction(0,98)
    // val f2 = Fraction(49,98)

    // println(f1 > f2)

    val numCase = rd.readInt()
    repeat(numCase){
    	val a = rd.readInts()
    	var resultList = mutableListOf<String>()

    	val p1 = Point(a[0], a[1])
    	val p2 = Point(a[2], a[3])

    	val numCities = rd.readInt()

    	var maxD = Fraction(10000000000000L, 1L)
    	repeat(numCities){
    		val a1 = rd.readStrings()
    		val p0 = Point(a1[1].toLong(), a1[2].toLong())
    		val d = pointToLineSquared(p0, p1, p2)
    		//println("$d $maxD")

    		if (d < maxD) {
    			maxD = d
    			resultList = mutableListOf<String>(a1[0])
    		}
    		else if (d == maxD) resultList.add(a1[0])
    		//println(resultList)
    	}

    	println(resultList.joinToString(separator = " "))
    }
}





