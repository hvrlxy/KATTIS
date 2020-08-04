import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // singleLong
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of longs
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }

/* The problem asks us to find an algorithm to test whether a point is inside
a semi-circle. We do it by testing if the triangle is obtuse or not */

val INF = 100000000

data class LineSegment(val p1: Point, val p2: Point)

data class Point (val x:Long, val y:Long)

fun crossProduct(pi: Point, pj: Point):Long{
	return pi.x * pj.y - pj.x * pi.y
}

fun direction(pi: Point, pj: Point, pk: Point): Long{
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

	else if ((d1 == 0L && onSegment(p3,p4,p1))
		|| (d2 == 0L && onSegment(p3,p4,p2))
		|| (d3 == 0L && onSegment(p1,p2,p3))
		|| (d4 == 0L && onSegment(p1,p2,p4))) return true

	return false
}

fun isIntersect(l1: LineSegment, l2: LineSegment): Boolean = isIntersect(l1.p1, l1.p2, l2.p1, l2.p2)

fun squareDist (p1: Point, p2: Point):Long = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)

fun inFront(x: Point, y: Point, z: Point): Boolean{
	// test whether point z is in front of point x and y
	// cosine of the angle between vector a and b = the dot product / magnitude.
	

    val newY = Point(y.x - x.x, y.y - x.y)
    val newZ = Point(z.x - x.x, z.y - x.y)


    return (newY.x * newZ.x + newY.y * newZ.y >= 0)
}

class SemiCircle(val p1: Point, val p2: Point){
	val radius = squareDist(p1, p2)
	fun isIn(p: Point): Boolean{
		// test if the point is inside the circle first
		if (squareDist(p, p1) > radius) return false

		// see if the angle created is less than 90
		if (inFront(p1, p2, p)) return true else return false
	}
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val W = s[0]
    val P = s[1]

    val a = rd.readLongs()

    val treasure = Point(a[0], a[1])

    val wallList = mutableListOf<LineSegment>()
    repeat(W){
    	val aline = rd.readLongs()
    	wallList.add(LineSegment(Point(aline[0], aline[1]), Point(aline[2], aline[3])))
    }

    val circleList = mutableListOf<SemiCircle>()
    repeat(P){
    	val aline = rd.readLongs()
    	circleList.add(SemiCircle(Point(aline[0], aline[1]), Point(aline[2], aline[3])))
    }

    //val valid = BooleanArray(P){true}

    for (c in 0 until P){
    	var isValid = true
    	// test if the treasure is within the semi-circle
    	if (!circleList[c].isIn(treasure)) {
    		isValid = false
    	}
    	// test if a wall is between the way
    	for (i in 0 until W){
    		if (isIntersect(LineSegment(treasure, circleList[c].p1), wallList[i])){

    			isValid = false
    			break
    		}
    	}
    	//test if there are any other pirates blocking the way
    	for (j in 0 until P){
    		if (j == c) continue
    		if (isIntersect(LineSegment(circleList[j].p1, circleList[j].p1), LineSegment(treasure, circleList[c].p1))) {
    			isValid = false
    			break
    		}
    	}
    	if (isValid) println("visible") else println("not visible")
    }
}