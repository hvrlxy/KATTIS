import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

/* Since x and y are integers, and 2018 = sqrt(0^2 + 2018^2) or sqrt(1118^2 + 1680^2), we can use a hash table.
such that for each pair of points (x,y), search for (x +- 2018, y), (x, y +- 2018), ... */ 

data class Point (val x: Long, val y: Long)


fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPoint = rd.readInt()

    val pList = mutableSetOf<Point>()

    repeat(numPoint){
    	val a = rd.readInts()
    	pList.add(Point(a[0], a[1]))
    }

    var count = 0

    for (p in pList){
    	if (Point(p.x - 2018, p.y) in pList) count ++
    	if (Point(p.x + 2018, p.y) in pList) count ++
    	if (Point(p.x, p.y - 2018) in pList) count ++
    	if (Point(p.x, p.y + 2018) in pList) count ++
    	if (Point(p.x - 1118, p.y - 1680) in pList) count ++
    	if (Point(p.x + 1118, p.y - 1680) in pList) count ++
    	if (Point(p.x + 1118, p.y + 1680) in pList) count ++
    	if (Point(p.x - 1118, p.y + 1680) in pList) count ++
    	if (Point(p.x - 1680, p.y - 1118) in pList) count ++
    	if (Point(p.x + 1680, p.y - 1118) in pList) count ++
    	if (Point(p.x + 1680, p.y + 1118) in pList) count ++
    	if (Point(p.x - 1680, p.y + 1118) in pList) count ++
    }

    println(count / 2)
}