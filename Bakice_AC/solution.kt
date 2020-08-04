import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

data class Point(val x: Int, val y: Int)

data class Distance (val i: Int, val j: Int, val d: Int)

fun euclidDistance (a: Point, b: Point): Int = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val Llist = mutableListOf<Point>()
    val Xlist = mutableListOf<Point>()

    for (i in 0 until s[0]){
    	val aline = rd.readLn()
    	for (j in 0 until s[1]){
    		if (aline[j] == 'L') Llist.add(Point(i,j))
    		else if (aline[j] == 'X') Xlist.add(Point(i,j))
    	}
    }

    var numPassengers = Xlist.size
    var numSeats = Llist.size
    val dList = mutableListOf<Distance>()

    for (i in 0 until numPassengers){
    	for (j in 0 until numSeats){
    		dList.add(Distance(i, j, euclidDistance(Xlist[i], Llist[j])))
    	}
    }

    dList.sortBy{it.d}

    val seatD = IntArray(numSeats){-1}
    val passEmpty = BooleanArray(numPassengers){true}
    val explosion = BooleanArray(numSeats){false}

    var result = 0
    for (p in dList){
    	if (!passEmpty[p.i]) continue

    	if (seatD[p.j] == p.d && !explosion[p.j]){
    		result ++
    		passEmpty[p.i] = false
    		explosion[p.j] = true
    	}
    	else if (explosion[p.j] && seatD[p.j] == p.d){
    		passEmpty[p.i] = false
    	}
    	else if (seatD[p.j] == -1){
    		seatD[p.j] = p.d
    		passEmpty[p.i] = false
    	}
    }
    println(result)
}