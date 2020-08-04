import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

class Point(val x: Long, val y: Long) : Comparable<Point> {
 
    override fun compareTo(other: Point) = this.x.compareTo(other.x)
 
    override fun toString() = "$x $y"
}
 
fun convexHull(p: MutableList<Point>): List<Point> {
    if (p.isEmpty()) return emptyList()
    if (p.size == 1) return p.toList()
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
    ((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) >= 0 

fun squaredDist(p1: Point, p2: Point): Long = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPoints = rd.readInt()
    val pointList = mutableListOf<Point>()

    repeat(numPoints){
    	val a = rd.readInts()
    	pointList.add(Point(a[0], a[1]))
    }

    val convexHull = convexHull(pointList)

    var max_D = 0L

    for (i in 0 until convexHull.size){
    	for (j in i + 1 until convexHull.size){
    		val d = squaredDist(convexHull[i], convexHull[j])
    		if (d > max_D) max_D = d
    	}
    }

    println(kotlin.math.sqrt(max_D.toDouble()))
}