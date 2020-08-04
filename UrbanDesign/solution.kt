import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readLong() = readLn().toLong() // single Long
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Longs

class Point(val x: Long, val y: Long) : Comparable<Point> {
 
    override fun compareTo(other: Point) = this.x.compareTo(other.x)
 
    override fun toString() = "$x $y"
}

class Line (val x: Point, val y: Point){
	override fun toString() = "$x $y"
}

fun ccw(a: Point, b: Point, c: Point) =
	//if point c is to the right of the segment ab
    ((b.y - a.y) * (c.x - a.x)) - ((b.x - a.x) * (c.y - a.y)) >= 0 

fun isSeparated(a: Point, b: Point, l: Line): Boolean{
	val directionA = ccw(l.x, l.y, a)
	val directionB = ccw(l.x, l.y, b)

	return !(directionA == directionB)
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numLines = rd.readLong().toInt()
	val lineList = mutableListOf<Line>()
	repeat(numLines){
		val aline = rd.readLongs()
		lineList.add(Line(Point(aline[0], aline[1]), Point(aline[2], aline[3])))
	}

	val numPoints = rd.readLong().toInt()
	repeat(numPoints){
		val aline = rd.readLongs()
		val a = Point(aline[0], aline[1])
		val b = Point(aline[2], aline[3])
		var numSeperate = 0

		for (l in lineList){
			if (isSeparated(a,b,l)){
				numSeperate ++
			}
		}
		if (numSeperate % 2 == 1) println("different") else println("same")
	}
}