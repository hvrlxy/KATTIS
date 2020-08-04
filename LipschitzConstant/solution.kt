import java.io.*
import kotlin.math.abs

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toDouble() } // list of ints

data class Point(val x: Double, val y: Double): Comparable<Point>{
	override fun compareTo(other: Point): Int = this.x.compareTo(other.x)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPoints = rd.readInt()
    val pointList = mutableListOf<Point>()
    repeat(numPoints){
    	val a = rd.readInts()
    	pointList.add(Point(a[0],a[1]))
    }
    pointList.sort()
    var result = -1.0

    fun findL(a: Point, b: Point): Double = abs(a.y - b.y) / abs(a.x - b.x)

    for (i in 0 until numPoints - 1){
    	val L = findL(pointList[i], pointList[i + 1])
    	if (L > result) result = L
    }
    println(result)
}