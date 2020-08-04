import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single Int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Ints

class Point(val x: Long, val y: Long) : Comparable<Point> {
 
    override fun compareTo(other: Point) = this.x.compareTo(other.x)
 
    override fun toString() = "$x $y"
}
 
fun convexHull(p: MutableList<Point>): MutableList<Point> {
    if (p.isEmpty()) return mutableListOf<Point>()
    if (p.size == 1) return p.toMutableList()
    p.sort()
    val h = mutableListOf<Point>()
 
    // lower hull
    for (pt in p) {
        while (h.size >= 2 && ccw(h[h.size - 2], h.last(), pt)) {
            h.removeAt(h.lastIndex)
        }
        h.add(pt)
    }
 
    //upper hull
    val t = h.size + 1
    for (i in p.size - 2 downTo 0) {
        val pt = p[i]
        while (h.size >= t && ccw(h[h.size - 2], h.last(), pt)) {
            h.removeAt(h.lastIndex)
        }
        h.add(pt)
    }
 
    h.removeAt(h.lastIndex)
    return h
}
 
/* ccw returns true if the three Points make a counter-clockwise turn */
fun ccw(a: Point, b: Point, c: Point) =
	//if point c is to the right of the segment ab
    ((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) >= 0 

fun gcd(a: Long, b: Long): Long{
    if (b == 0L) return a
    return gcd(b, a%b)
}

fun crossProduct(pi: Point, pj: Point): Long{
    return pi.x * pj.y - pj.x * pi.y
}

fun direction(pi: Point, pj: Point, pk: Point): Long{
    return crossProduct(Point(pk.x - pi.x, pk.y - pi.y), Point(pj.x - pi.x, pj.y - pi.y))
}

fun onSegment(pi: Point, pj: Point, pk: Point): Boolean{
    if (direction(pi,pj,pk) != 0L) return false

    //if the points are collinear, then proceed to see if pk is in between pi and pj
    if ((min(pi.x, pj.x) <= pk.x && pk.x <= max(pi.x, pj.x))
        && (min(pi.y, pj.y) <= pk.y && pk.y <= max(pi.y, pj.y))) {
        //println("$pi, $pj, $pk")
        return true
    }
    return false
}

fun isIntersect(p1: Point, p2: Point, p3: Point, p4: Point): Boolean{
    val d1 = direction(p3,p4,p1)
    val d2 = direction(p3,p4,p2)
    val d3 = direction(p1,p2,p3)
    val d4 = direction(p1,p2,p4)

    if (((d1 > 0L && d2 < 0L) || (d1 < 0L && d2 > 0L)) 
        && ((d3 > 0L && d4 < 0L) || (d3 < 0L && d4 > 0L))) return true

    else if ((d1 == 0L && onSegment(p3,p4,p1))
        || (d2 == 0L && onSegment(p3,p4,p2))
        || (d3 == 0L && onSegment(p1,p2,p3))
        || (d4 == 0L && onSegment(p1,p2,p4))) return true

    return false
}

fun intersection (p1: Point, p2: Point, p3: Point, p4: Point): MutableList<Point>{
    val result = mutableListOf<Point>()
    if (!isIntersect(p1,p2,p3,p4)) return result
    else{
        val d1 = direction(p3,p4,p1)
        val d2 = direction(p3,p4,p2)
        val d3 = direction(p1,p2,p3)
        val d4 = direction(p1,p2,p4)

        if (d1 == 0L && onSegment(p3,p4,p1)) result.add(p1)
        if (d2 == 0L && onSegment(p3,p4,p2)) result.add(p2)
        if (d3 == 0L && onSegment(p1,p2,p3)) result.add(p3)
        if (d4 == 0L && onSegment(p1,p2,p4)) result.add(p4)

        if (result.size == 0){
            val alpha = ((p4.x - p2.x) * (p4.y - p3.y) + (p4.y - p2.y) * (p3.x - p4.x)).toDouble() / ((p1.x - p2.x) * (p4.y - p3.y) - (p1.y - p2.y) * (p4.x - p3.x))
            val x = (alpha * p1.x + (1.0 - alpha) * p2.x).toLong()
            val y = (alpha * p1.y + (1.0 - alpha) * p2.y).toLong()
            result.add(Point(x,y))
        }
    }
    return result
}

fun isInsidePolygon(pointList: MutableList<Point>, p: Point): Boolean{
    val polyPoints = pointList.size
    val testP = Point(20000000000, p.y)
    var onSegment = false

    for (i in 0 until pointList.size){
        if (onSegment(pointList[i], pointList[(i + 1) % polyPoints], p)) {
            onSegment = true
            break
        }
    }

    if (onSegment) return true
    else{
        var isIn = false
        //println(p)
        for (i in 0 until pointList.size){
            val intersection = intersection(p, testP, pointList[i], pointList[(i + 1) % polyPoints])

            if (intersection.size == 1){
                if (pointList[i].y == intersection[0].y && pointList[i].x == intersection[0].x 
                    &&  pointList[(i + 1) % polyPoints].y <= intersection[0].y){
                    continue
                }
                if (pointList[(i + 1) % polyPoints].x == intersection[0].x && 
                    pointList[(i + 1) % polyPoints].y == intersection[0].y && 
                    pointList[i].y <= intersection[0].y){
                    //println("a")
                    continue
                }
                isIn = !isIn
            }
        }

        if (isIn) {
            return true 
        }
        else return false
    }
}


fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val L = rd.readInt()

    val pointList = mutableListOf<Point>()
    repeat(L){
        val a = rd.readLongs()

        pointList.add(Point(a[0], a[1]))
    }

    val convexHull = convexHull(pointList)
    //println(convexHull)

    val S = rd.readInt()
    var count = 0

    repeat(S){
        val a = rd.readLongs()
        val p = Point(a[0], a[1])

        if (isInsidePolygon(convexHull, p)) {
            count++
            //println(p)
        }
    }

    println(count)
}