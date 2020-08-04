import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

// to determine whether a point is in a simple polygon, draw a line infinitely to the right. If the line cut the polygon an even number of time, then it is outside the polygon. However, becareful with the case when the line cut two edge at one point. That should only be counted as one intersection.

data class Point (val x: Int, val y: Int)

fun gcd(a: Int, b: Int): Int{
    if (b == 0) return a
    return gcd(b, a%b)
}

fun crossProduct(pi: Point, pj: Point): Int{
    return pi.x * pj.y - pj.x * pi.y
}

fun direction(pi: Point, pj: Point, pk: Point): Int{
    return crossProduct(Point(pk.x - pi.x, pk.y - pi.y), Point(pj.x - pi.x, pj.y - pi.y))
}

fun onSegment(pi: Point, pj: Point, pk: Point): Boolean{
    if (direction(pi,pj,pk) != 0) return false

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

    if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) 
        && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) return true

    else if ((d1 == 0 && onSegment(p3,p4,p1))
        || (d2 == 0 && onSegment(p3,p4,p2))
        || (d3 == 0 && onSegment(p1,p2,p3))
        || (d4 == 0 && onSegment(p1,p2,p4))) return true

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

        if (d1 == 0 && onSegment(p3,p4,p1)) result.add(p1)
        if (d2 == 0 && onSegment(p3,p4,p2)) result.add(p2)
        if (d3 == 0 && onSegment(p1,p2,p3)) result.add(p3)
        if (d4 == 0 && onSegment(p1,p2,p4)) result.add(p4)

        if (result.size == 0){
            val alpha = ((p4.x - p2.x) * (p4.y - p3.y) + (p4.y - p2.y) * (p3.x - p4.x)).toDouble() / ((p1.x - p2.x) * (p4.y - p3.y) - (p1.y - p2.y) * (p4.x - p3.x))
            val x = (alpha * p1.x + (1.0 - alpha) * p2.x).toInt()
            val y = (alpha * p1.y + (1.0 - alpha) * p2.y).toInt()
            result.add(Point(x,y))
        }
    }
    return result
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var polyPoints = rd.readInt()
    while (polyPoints > 0){
        val pointList = mutableListOf<Point>()
        repeat(polyPoints){
            val a = rd.readInts()
            pointList.add(Point(a[0], a[1]))
        }

        val numTests = rd.readInt()
        repeat(numTests){
            val a1 = rd.readInts()

            val p = Point(a1[0], a1[1])
            val testP = Point(10001, a1[1])
            var onSegment = false

            for (i in 0 until pointList.size){
                if (onSegment(pointList[i], pointList[(i + 1) % polyPoints], p)) {
                    onSegment = true
                    break
                }
            }

            if (onSegment) println("on")
            else{
                var isIn = false
                for (i in 0 until pointList.size){
                    val intersection = intersection(p, testP, pointList[i], pointList[(i + 1)% polyPoints])
                    if (intersection.size == 1){
                        if (pointList[i].y == intersection[0].y && pointList[i].x == intersection[0].x &&  pointList[(i + 1)% polyPoints].y <= intersection[0].y){
                            continue
                        }
                        if (pointList[(i + 1)% polyPoints].x == intersection[0].x && pointList[(i + 1)% polyPoints].y == intersection[0].y &&  pointList[i].y <= intersection[0].y){
                            continue
                        }
                        isIn = !isIn
                    }
                }

                if (isIn) println("in") else println("out")
            }
        }
        polyPoints = rd.readInt()
    }
}
