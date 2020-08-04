import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readLong() = readLn().toLong() // single Long
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Longs

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
	//if point c is to the right of the segment ab
    ((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) >= 0 

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var numPoints = rd.readLong()
    while (numPoints > 0){
    	val PointLists = mutableListOf<Point>()
    	val pArray = mutableSetOf<Pair<Long, Long>>()
    	for (i in 0 until numPoints){
    		val a = rd.readLongs()
    		if ((a[0] to a[1]) !in pArray) PointLists.add(Point(a[0], a[1]))
    		pArray.add(a[0] to a[1])
    	}

    	//println(PointLists)

    	val result = convexHull(PointLists)

    	println(result.size)
    	println(result.joinToString(separator = "\n"))
    	numPoints = rd.readLong()
    }
}