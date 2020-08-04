import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    data class Point (val x: Int, val y: Int)

    fun colinear(a: Point, b: Point, c: Point): Boolean = (((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) == 0)

    val numPoint = rd.readInt()
    if (numPoint <= 2) return println("success")

    val pointList = mutableListOf<Point>()

    repeat(numPoint){
    	val aline = rd.readInts()
    	pointList.add(Point(aline[0], aline[1]))
    }

    val p1 = 0
    val p2 = 1
    var p3 = -1

    // if p1 and p2 is in one of the lines
    var pList = mutableListOf<Int>()
    for (i in 2 until numPoint){
    	if (!colinear(pointList[p1], pointList[p2], pointList[i])) pList.add(i)
    }

    if (pList.size <= 2) return println("success")
    else{
    	var isValid = true
    	for (i in 2 until pList.size){
    		if (!colinear(pointList[pList[0]], pointList[pList[1]], pointList[pList[i]])) {
    			isValid = false
    			break
    		}
    	}
    	if (isValid) return println("success")
    }

    p3 = pList[0]
    //if p1 and p3 is in one of the line
    pList = mutableListOf(p2)

    for (i in 2 until numPoint){
    	if (!colinear(pointList[p1], pointList[p3], pointList[i])) pList.add(i)
    }

    if (pList.size <= 2) return println("success")
    else{
    	var isValid = true
    	for (i in 2 until pList.size){
    		if (!colinear(pointList[pList[0]], pointList[pList[1]], pointList[pList[i]])) {
    			isValid = false
    			break
    		}
    	}
    	if (isValid) return println("success")
    }

    //if p2 and p3 is in one of the line
    pList = mutableListOf(p1)

    for (i in 2 until numPoint){
    	if (!colinear(pointList[p2], pointList[p3], pointList[i])) pList.add(i)
    }

    if (pList.size <= 2) return println("success")
    else{
    	var isValid = true
    	for (i in 2 until pList.size){
    		if (!colinear(pointList[pList[0]], pointList[pList[1]], pointList[pList[i]])) {
    			isValid = false
    			break
    		}
    	}
    	if (isValid) return println("success")
    }

    return println("failure")

}