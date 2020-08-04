import java.io.*
import java.util.LinkedList
import java.util.Queue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val a = Array<CharArray>(s[0]){CharArray(s[1]){' '}}

    for (i in 0 until s[0]){
    	val aline = rd.readLn()
    	for (j in 0 until s[1]){
    		a[i][j] = aline[j]
    	}
    }

    val board = Board(a, s[0], s[1])
    println(board.solve(rd.readLn()))
}

class Board(val a: Array<CharArray>, val h: Int, val w: Int){
	val d = Array<IntArray>(h){IntArray(w){Int.MAX_VALUE}}

	var startingPoint : Pair<Int, Int> = (0 to 0)
	var endingPoint : Pair<Int, Int> = (0 to 0)
	var pointSet = mutableSetOf<Pair<Int, Int>>()

	init{
		for (i in 0 until h){
	    	for (j in 0 until w){
	    		if (a[i][j] == '.') pointSet.add(i to j)
	    		else if (a[i][j] == 'S') startingPoint = (i to j)
	    		else if (a[i][j] == 'G') endingPoint = (i to j)
	    	}
	    }

	    pointSet.add(startingPoint)
	    pointSet.add(endingPoint)
	}

	fun printTable(){
		// for debugging
		for (i in 0 until h){
			println(d[i].joinToString())
		}
		println()
	}

	fun findShortestPath(){
		val seen = Array<BooleanArray>(h){BooleanArray(w){false}}
		val Q : Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
		Q.add(endingPoint)
		d[endingPoint.first][endingPoint.second] = 0
		seen[endingPoint.first][endingPoint.second] = true
		while (Q.isNotEmpty()){
			val v = Q.poll()
			seen[v.first][v.second] = true

			//printTable()

			if ((v.first + 1 to v.second) in pointSet && !seen[v.first + 1][v.second]){
				d[v.first + 1][v.second] = d[v.first][v.second] + 1
				Q.add(v.first + 1 to v.second)
			}
			if ((v.first - 1 to v.second) in pointSet && !seen[v.first - 1][v.second]){
				d[v.first - 1][v.second] = d[v.first][v.second] + 1
				Q.add(v.first - 1 to v.second)
			}
			if ((v.first to v.second + 1) in pointSet && !seen[v.first][v.second + 1]){
				d[v.first][v.second + 1] = d[v.first][v.second] + 1
				Q.add(v.first to v.second + 1)
			}
			if ((v.first to v.second - 1) in pointSet && !seen[v.first][v.second - 1]){
				d[v.first][v.second - 1] = d[v.first][v.second] + 1
				Q.add(v.first to v.second - 1)
			}
		}
	}

	fun solve(s: String): Int{
		findShortestPath()

		var currentPoint = startingPoint
		var result = d[currentPoint.first][currentPoint.second]
		for (i in 0 until s.length){
			if (s[i] == 'U' && (currentPoint.first - 1 to currentPoint.second) in pointSet){
				currentPoint = (currentPoint.first - 1 to currentPoint.second)
			}
			else if (s[i] == 'D' && (currentPoint.first + 1 to currentPoint.second) in pointSet){
				currentPoint = (currentPoint.first + 1 to currentPoint.second)
			}
			else if (s[i] == 'L' && (currentPoint.first to currentPoint.second - 1) in pointSet){
				currentPoint = (currentPoint.first to currentPoint.second - 1)
			}
			else if (s[i] == 'R' && (currentPoint.first to currentPoint.second + 1) in pointSet){
				currentPoint = (currentPoint.first to currentPoint.second + 1)
			}

			//println(currentPoint)

			if (d[currentPoint.first][currentPoint.second] < result){
				result = d[currentPoint.first][currentPoint.second]
			}
		}

		return result
	}
}







