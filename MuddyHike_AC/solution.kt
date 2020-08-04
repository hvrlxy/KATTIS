import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*
import java.util.PriorityQueue

/*
	I use java queue in this implementation for speed, but feel free to use ArrayDeque 
	or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val h = s[0]
    val w = s[1]

    fun isValid (v: Pair<Int, Int>): Boolean{
    	if (v.first < 0 || v.first >= h || v.second < 0 || v.second >= w) return false
    	return true
    }

    val minArray = Array<IntArray>(h){IntArray(w){Int.MAX_VALUE}}

    for (i in 0 until h){
    	val aline = rd.readInts()
    	for (j in 0 until w){
    		minArray[i][j] = aline[j]
    	}
    }

    val d = Array<BooleanArray>(h){BooleanArray(w)}

    data class Point(val x: Int, val y: Int, val d: Int): Comparable<Point>{
    	override fun compareTo(other: Point): Int = this.d.compareTo(other.d)
    }

    val P = PriorityQueue<Point>()

    val result = Array<IntArray>(h){IntArray(w){Int.MAX_VALUE}}
    for (i in 0 until h){
        result[i][0] = minArray[i][0]
        d[i][0] = true
        P.add(Point(i,0,minArray[i][0]))
    }


    while (P.isNotEmpty()){
    	val u = P.poll()
    	d[u.x][u.y] = true

        if (u.y == w - 1) return println(result[u.x][u.y])

    	if (isValid(u.x + 1 to u.y) && !d[u.x + 1][u.y]){
    		P.add(Point(u.x + 1, u.y, minArray[u.x + 1][u.y]))
    		d[u.x + 1][u.y] = true
    		result[u.x + 1][u.y] = max(minArray[u.x + 1][u.y], result[u.x][u.y])
    	}

    	if (isValid(u.x - 1 to u.y) && !d[u.x - 1][u.y]){
    		P.add(Point(u.x - 1, u.y, minArray[u.x - 1][u.y]))
    		d[u.x - 1][u.y] = true
    		result[u.x - 1][u.y] = max(minArray[u.x - 1][u.y], result[u.x][u.y])
    	}

    	if (isValid(u.x to u.y + 1) && !d[u.x][u.y + 1]){
    		P.add(Point(u.x, u.y + 1, minArray[u.x][u.y + 1]))
    		d[u.x][u.y + 1] = true
    		result[u.x][u.y + 1] = max(minArray[u.x][u.y + 1], result[u.x][u.y])
    	}

    	if (isValid(u.x to u.y - 1) && !d[u.x][u.y - 1]){
    		P.add(Point(u.x, u.y - 1, minArray[u.x][u.y - 1]))
    		d[u.x][u.y - 1] = true
    		result[u.x][u.y - 1] = max(minArray[u.x][u.y - 1], result[u.x][u.y])
    	}
    }

}