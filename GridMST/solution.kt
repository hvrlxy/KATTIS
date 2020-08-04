import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Point (val x: Int, val y: Int){
    override fun toString(): String = "$x $y"
}

fun ManhattanDist (a: Point, b: Point): Int = abs(a.x - b.x) + abs(a.y - b.y)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInt()

    val aSet = DisjointSet(a)

    val locationList = mutableListOf<Point>()
    val distanceList = mutableListOf<Triple<Int, Int, Int>>()

    repeat(a){
    	val a1 = rd.readInts()
    	locationList.add(Point(a1[0], a1[1]))
    }

    fun range(a: Point, b: Point): Boolean{
        var p1 : Point
        var p2 : Point
        if (a.x <= b.x) {
            p1 = a
            p2 = b
        }
        else{
            p1 = b
            p2 = a
        }

        var numPoint = 0 
        for (p in locationList){
            if (p == p1 || p == p2) continue
            if (p1.x <= p.x && p.x <= p2.x && p1.y <= p.y && p.y <= p2.y) numPoint++
        }
        if (numPoint > 0) return false else return true
    }

    for (i in 0 until locationList.size){
        for (j in i + 1 until locationList.size){
            if (i == j) continue
            if (range(locationList[i], locationList[j])){
                distanceList.add(Triple(i, j, ManhattanDist(locationList[i], locationList[j])))
            }
        }
    }

    //println(distanceList)

    var result = 0
    distanceList.sortBy{it.third}
    var count = a - 1

    for (p in 0 until distanceList.size){
        val d = distanceList[p]
        if (count == 0) break
        if (aSet.find(d.first) != aSet.find(d.second)){
            result += d.third
            aSet.union(d.first, d.second)
            count --
        }
    }

    println(result)
}

class DisjointSet (val size: Int){
    val rankArray = IntArray(size)
    val parentArray = IntArray(size){it}

    fun find (v: Int): Int{
        var v = v
        if (parentArray[v] == v) return v
        else{
            var w = find(parentArray[v])
            parentArray[v] = w
            return w
        }
    }

    fun union(v: Int, w: Int){
        var rootV = find(v)
        var rootW = find(w)

        if (rankArray[rootV] < rankArray[rootW]){
            parentArray[rootV] = rootW
        }

        else if(rankArray[rootW] < rankArray[rootV]) {
            parentArray[rootW] = rootV
        }

        else{
            rankArray[rootV] ++
            parentArray[rootW] = rootV
        }
    }
}