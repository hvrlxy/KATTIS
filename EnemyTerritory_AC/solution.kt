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

data class Point(val x : Int, val y: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    
	fun solve(){
	    val s1 = rd.readInts()
	    //println(s1)

	    val N = s1[0] //num enemy bases
	    val X = s1[1] // rows
	    val Y = s1[2] // columns

	    val minTable = Array<IntArray>(X){IntArray(Y){Int.MAX_VALUE}}

	    val routeTable = Array<IntArray>(X){IntArray(Y){Int.MAX_VALUE}}

	    val aline = rd.readInts()
	    val s = Point(aline[0], aline[1])
	    val t = Point(aline[2], aline[3])

	    //find the minimum distance from a square to an enemy base using bfs

	    val Q : Queue<Point> = LinkedList<Point>()
	    
    	repeat(N){
	    	val a = rd.readInts()

	    	minTable[a[0]][a[1]] = 0
	    	Q.add(Point(a[0], a[1]))
	    }
	    

	    fun isValid (a: Point): Boolean = !(a.x < 0 || a.y < 0 || a.x >= X || a.y >= Y)

	    while (Q.isNotEmpty()){
	    	val v = Q.poll()

	    	val u1 = Point(v.x + 1, v.y)
	    	val u2 = Point(v.x - 1, v.y)
	    	val u3 = Point(v.x, v.y + 1)
	    	val u4 = Point(v.x, v.y - 1)

	    	if (isValid(u1) && minTable[u1.x][u1.y] == Int.MAX_VALUE){
	    		minTable[u1.x][u1.y] = minTable[v.x][v.y] + 1
	    		Q.add(u1)
	    	}
	    	if (isValid(u2) && minTable[u2.x][u2.y] == Int.MAX_VALUE){
	    		minTable[u2.x][u2.y] = minTable[v.x][v.y] + 1
	    		Q.add(u2)
	    	}
	    	if (isValid(u3) && minTable[u3.x][u3.y] == Int.MAX_VALUE){
	    		minTable[u3.x][u3.y] = minTable[v.x][v.y] + 1
	    		Q.add(u3)
	    	}
	    	if (isValid(u4) && minTable[u4.x][u4.y] == Int.MAX_VALUE){
	    		minTable[u4.x][u4.y] = minTable[v.x][v.y] + 1
	    		Q.add(u4)
	    	}
	    }

	    // find the minimum dist from enemy from s to t

	    val d = Array<BooleanArray>(X){BooleanArray(Y)}

	    val result = Array<IntArray>(X){IntArray(Y){Int.MAX_VALUE}}

	    data class Destination(val x: Int, val y: Int, val w: Int): Comparable<Destination>{
	    	override fun compareTo(other: Destination): Int = other.w.compareTo(this.w)
	    }

	    var P = PriorityQueue<Destination>()

	    P.add(Destination(s.x, s.y, minTable[s.x][s.y]))
	    result[s.x][s.y] = minTable[s.x][s.y]

	    while (P.isNotEmpty()){
	    	val u = P.poll()

	    	d[u.x][u.y] = true

	    	if (isValid(Point(u.x + 1, u.y)) && !d[u.x + 1][u.y]){
	            P.add(Destination(u.x + 1, u.y, minTable[u.x + 1][u.y]))
	            d[u.x + 1][u.y] = true
	            result[u.x + 1][u.y] = min(minTable[u.x + 1][u.y], result[u.x][u.y])
	        }

	        if (isValid(Point(u.x - 1, u.y)) && !d[u.x - 1][u.y]){
	            P.add(Destination(u.x - 1, u.y, minTable[u.x - 1][u.y]))
	            d[u.x - 1][u.y] = true
	            result[u.x - 1][u.y] = min(minTable[u.x - 1][u.y], result[u.x][u.y])
	        }

	        if (isValid(Point(u.x, u.y + 1)) && !d[u.x][u.y + 1]){
	            P.add(Destination(u.x, u.y + 1, minTable[u.x][u.y + 1]))
	            d[u.x][u.y + 1] = true
	            result[u.x][u.y + 1] = min(minTable[u.x][u.y + 1], result[u.x][u.y])
	        }

	        if (isValid(Point(u.x, u.y - 1)) && !d[u.x][u.y - 1]){
	            P.add(Destination(u.x, u.y - 1, minTable[u.x][u.y - 1]))
	            d[u.x][u.y - 1] = true
	            result[u.x][u.y - 1] = min(minTable[u.x][u.y - 1], result[u.x][u.y])
	        }
	    }

	    //do another bfs to search for the shortest path?

	    routeTable[s.x][s.y] = 0

	    Q.add(s)

	    while (Q.isNotEmpty()){
	    	val u = Q.poll()

	    	val u1 = Point(u.x + 1, u.y)
	    	val u2 = Point(u.x - 1, u.y)
	    	val u3 = Point(u.x, u.y + 1)
	    	val u4 = Point(u.x, u.y - 1)

	    	//println("$u $u1 ${routeTable[u1.x][u1.y] < routeTable[u.x][u.y] + 1}")
	    	if (isValid(u1) 
	    		&& result[u1.x][u1.y] <= result[u.x][u.y] 
	    		&& routeTable[u1.x][u1.y] > routeTable[u.x][u.y] + 1){
	    		//println("a")
	    		routeTable[u1.x][u1.y] = routeTable[u.x][u.y] + 1
	    		Q.add(u1)
	    	}
	    	if (isValid(u2) 
	    		&& result[u2.x][u2.y] <= result[u.x][u.y]
	    		&& routeTable[u2.x][u2.y] > routeTable[u.x][u.y] + 1){
	    		routeTable[u2.x][u2.y] = routeTable[u.x][u.y] + 1
	    		Q.add(u2)
	    	}
	    	if (isValid(u3) 
	    		&& result[u3.x][u3.y] <= result[u.x][u.y]
	    		&& routeTable[u3.x][u3.y] > routeTable[u.x][u.y] + 1){
	    		routeTable[u3.x][u3.y] = routeTable[u.x][u.y] + 1
	    		Q.add(u3)
	    	}
	    	if (isValid(u4) 
	    		&& result[u4.x][u4.y] <= result[u.x][u.y]
	    		&& routeTable[u4.x][u4.y] > routeTable[u.x][u.y] + 1){
	    		routeTable[u4.x][u4.y] = routeTable[u.x][u.y] + 1
	    		Q.add(u4)
	    	}
	    }



	    println("${result[t.x][t.y]} ${routeTable[t.x][t.y]} $s $t")
	}

	repeat(numCase){
    	solve()
    }
}
