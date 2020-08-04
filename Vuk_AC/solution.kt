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

    val treeList = mutableListOf<Pair<Int, Int>>()

    fun isValid (v: Pair<Int, Int>): Boolean{
    	if (v.first < 0 || v.first >= h || v.second < 0 || v.second >= w) return false
    	return true
    }

    val minArray = Array<IntArray>(h){IntArray(w){Int.MAX_VALUE}}
    var V = (0 to 0)
    var J = (0 to 0)

    for (i in 0 until h){
    	val aline = rd.readLine()
    	for (j in 0 until w){
    		if (aline[j] == '+') {
    			treeList.add(i to j)
    			minArray[i][j] = 0
    		}
    		if (aline[j] == 'V') V = (i to j)
    		if (aline[j] == 'J') J = (i to j)
    	}
    }


    // find minimum distance to a tree by using bfs
    val Q : Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()

    Q.addAll(treeList)
    while (Q.isNotEmpty()){
    	val u = Q.poll()

    	val v1 = (u.first + 1 to u.second)
    	val v2 = (u.first - 1 to u.second)
    	val v3 = (u.first to u.second + 1)
    	val v4 = (u.first to u.second - 1)

    	if (isValid(v1) && minArray[v1.first][v1.second] == Int.MAX_VALUE){
    		minArray[v1.first][v1.second] = minArray[u.first][u.second] + 1
    		Q.add(v1)
    	}

    	if (isValid(v2) && minArray[v2.first][v2.second] == Int.MAX_VALUE){
    		minArray[v2.first][v2.second] = minArray[u.first][u.second] + 1
    		Q.add(v2)
    	}

    	if (isValid(v3) && minArray[v3.first][v3.second] == Int.MAX_VALUE){
    		minArray[v3.first][v3.second] = minArray[u.first][u.second] + 1
    		Q.add(v3)
    	}

    	if (isValid(v4) && minArray[v4.first][v4.second] == Int.MAX_VALUE){
    		minArray[v4.first][v4.second] = minArray[u.first][u.second] + 1
    		Q.add(v4)
    	}
    }

    val d = Array<BooleanArray>(h){BooleanArray(w)}

    data class Point(val x: Int, val y: Int, val d: Int): Comparable<Point>{
    	override fun compareTo(other: Point): Int = other.d.compareTo(this.d)
    }

    val P = PriorityQueue<Point>()
    P.add(Point(V.first, V.second, minArray[V.first][V.second]))

    val result = Array<IntArray>(h){IntArray(w){Int.MAX_VALUE}}
    result[V.first][V.second] = minArray[V.first][V.second]
    while (P.isNotEmpty()){
    	val u = P.poll()
    	d[u.x][u.y] = true

    	if (u.x == J.first && u.y == J.second) return println(result[J.first][J.second])

    	if (isValid(u.x + 1 to u.y) && !d[u.x + 1][u.y]){
    		P.add(Point(u.x + 1, u.y, minArray[u.x + 1][u.y]))
    		d[u.x + 1][u.y] = true
    		result[u.x + 1][u.y] = min(minArray[u.x + 1][u.y], result[u.x][u.y])
    	}

    	if (isValid(u.x - 1 to u.y) && !d[u.x - 1][u.y]){
    		P.add(Point(u.x - 1, u.y, minArray[u.x - 1][u.y]))
    		d[u.x - 1][u.y] = true
    		result[u.x - 1][u.y] = min(minArray[u.x - 1][u.y], result[u.x][u.y])
    	}

    	if (isValid(u.x to u.y + 1) && !d[u.x][u.y + 1]){
    		P.add(Point(u.x, u.y + 1, minArray[u.x][u.y + 1]))
    		d[u.x][u.y + 1] = true
    		result[u.x][u.y + 1] = min(minArray[u.x][u.y + 1], result[u.x][u.y])
    	}

    	if (isValid(u.x to u.y - 1) && !d[u.x][u.y - 1]){
    		P.add(Point(u.x, u.y - 1, minArray[u.x][u.y - 1]))
    		d[u.x][u.y - 1] = true
    		result[u.x][u.y - 1] = min(minArray[u.x][u.y - 1], result[u.x][u.y])
    	}
    }

    println(result[J.first][J.second])
}