import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Point (val x: Int, val y: Int)

fun ccw(a: Point, b: Point, c: Point) =
	//if point c is to the right of the segment ab
    ((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) >= 0 

fun crossProduct(pi: Point, pj: Point): Int{
	return pi.x * pj.y - pj.x * pi.y
}

fun direction(pi: Point, pj: Point, pk: Point): Int{
	return crossProduct(Point(pk.x - pi.x, pk.y - pi.y), Point(pj.x - pi.x, pj.y - pi.y))
}

fun onSegment(pi: Point, pj: Point, pk: Point): Boolean{
	if ((min(pi.x, pj.x) <= pk.x && pk.x <= max(pi.x, pj.x))
		&& (min(pi.y, pj.y) <= pk.y && pk.y <= max(pi.y, pj.y))) return true
	return false
}

fun isIntersect(p1: Point, p2: Point, p3: Point, p4: Point): Boolean{
	val d1 = direction(p3,p4,p1)
	val d2 = direction(p3,p4,p2)
	val d3 = direction(p1,p2,p3)
	val d4 = direction(p1,p2,p4)

	if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) 
		&& ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) return true

	else if ((d1 == 0 && onSegment(p3,p4,p1))
		|| (d2 == 0 && onSegment(p3,p4,p2))
		|| (d3 == 0 && onSegment(p1,p2,p3))
		|| (d4 == 0 && onSegment(p1,p2,p4))) return true

	return false
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	repeat(numCase){
		val aline = rd.readInts()

		val x1 = aline[0]
		val y1 = aline[1]
		val x2 = aline[2]
		val y2 = aline[3]
		val x3 = aline[4]
		val y3 = aline[5]
		val x4 = aline[6]
		val y4 = aline[7]

		val p1 = Point(x1, y1)
		val p2 = Point(x2, y2)
		val p3 = Point(x3, y3)
		val p4 = Point(x4, y4)

		if (!isIntersect(p1,p2,p3,p4)) println("none")
		else{

			val d1 = direction(p3,p4,p1)
			val d2 = direction(p3,p4,p2)
			val d3 = direction(p1,p2,p3)
			val d4 = direction(p1,p2,p4)

			val pointSet = mutableSetOf<Point>()
			if (d1 == 0 && onSegment(p3,p4,p1)) pointSet.add(p1)
			if (d2 == 0 && onSegment(p3,p4,p2)) pointSet.add(p2)
			if (d3 == 0 && onSegment(p1,p2,p3)) pointSet.add(p3)
			if (d4 == 0 && onSegment(p1,p2,p4)) pointSet.add(p4)

			val pointList = pointSet.toMutableList()

			if (pointList.size >= 2){
				pointList.sortBy{it.y}
				pointList.sortBy{it.x}

				val x1 = "%.2f".format(pointList[0].x.toFloat())
				val y1 = "%.2f".format(pointList[0].y.toFloat())
				val x2 = "%.2f".format(pointList[1].x.toFloat())
				val y2 = "%.2f".format(pointList[1].y.toFloat())
				println ("$x1 $y1 $x2 $y2")
			}

			else if (pointList.size == 1){
				val x = "%.2f".format(pointList[0].x.toFloat())
				val y = "%.2f".format(pointList[0].y.toFloat())
				println ("$x $y")
			}

			else{
				val alpha = ((x4 - x2) * (y4 - y3) + (y4 - y2) * (x3 - x4)).toDouble() / ((x1 - x2) * (y4 - y3) - (y1 - y2) * (x4 - x3))
				//println(alpha)
				val x = "%.2f".format(alpha * x1 + (1.0 - alpha) * x2)
				val y = "%.2f".format(alpha * y1 + (1.0 - alpha) * y2)
				println("$x $y")
			}
		}
	}
}