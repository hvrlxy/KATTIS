import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPoints = rd.readInt()

    data class Point(val x: Int, val y: Int)

    val xMap = IntArray(100001){0}
    val yMap = IntArray(100001){0}

    val pointList = mutableListOf<Point>()

    repeat(numPoints){
    	val aline = rd.readInts()

    	pointList.add(Point(aline[0], aline[1]))

    	xMap[aline[0]] ++
    	yMap[aline[1]] ++
    }

    var result = 0L

    for (p in pointList){
    	result += (xMap[p.x] - 1).toLong() * (yMap[p.y] - 1).toLong() //be careful with this part
    }
    println(result)
}