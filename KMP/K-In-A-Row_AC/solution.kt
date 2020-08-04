import java.io.*
import java.util.LinkedList
import java.util.Queue

/*
	I use java queue in this implementation for speed, but feel free to use ArrayDeque 
	or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

val rd = BufferedReader(InputStreamReader(System.`in`))

fun main(){

    val numGames = rd.readInt()
    var Hansel = 0
    var Gretel = 0

    repeat(numGames){
    	val s = rd.readInts()

    	val w = s[0]
    	val h = s[1]
    	val K = s[2]

    	val b = Board(w,h,K)

    	if (b.isHanselWin()) Hansel ++
    	else if (b.isGretelWin()) Gretel ++
    }
    println("$Hansel:$Gretel")
}

class Board(val w: Int, val h: Int, val K: Int){
	val c = Array<CharArray>(h){CharArray(w){' '}}

	init{
		for (i in 0 until h){
			val aline = rd.readLn()
			for (j in 0 until w){
				c[i][j] = aline[j]
			}
		}
	}

	fun kmp(t: String, p: String): Boolean{
		val n = t.length
		val m = p.length

		if (m > n) return false
		val lps = lps(p)

		var j = 0
		var i = 0
		while (i < n){
			if (p[j] == t[i]){
				i ++
				j ++
			}
			if (j == m){
				return true
			}

			else if (i < n && p[j] != t[i]){
				if (j != 0) j = lps[j - 1] else i ++
			}
		}
		return false
	}

	fun lps(p: String): IntArray{
		val m = p.length
		val lps = IntArray(m){0}

		var i = 1
		var l = 0
		while (i < m){
			if (p[i] == p[l]){
				l ++
				lps[i] = l
				i ++
			}
			else{
				if (l != 0) l = lps[l - 1]
				else{
					lps[i] = l
					i ++
				}
			}
		}
		return lps
	}

	fun isHanselWin(): Boolean{
		for (i in 0 until h){
			if (checkRow(i, "x")) return true
			if (checkDiagonalR(i, "x")) return true
			if (checkDiagonalR1(i, "x")) return true
		}
		for (i in 0 until w){
			if (checkColumn(i, "x")) return true
			if (checkDiagonalC(i, "x")) return true
			if (checkDiagonalC1(i, "x")) return true
		}
		return false
	}

	fun isGretelWin(): Boolean{
		for (i in 0 until h){
			if (checkRow(i, "o")) return true
			if (checkDiagonalR(i, "o")) return true
			if (checkDiagonalR1(i, "o")) return true
		}
		for (i in 0 until w){
			if (checkColumn(i, "o")) return true
			if (checkDiagonalC(i, "o")) return true
			if (checkDiagonalC1(i, "o")) return true
		}
		return false
	}

	fun checkRow(r: Int, char: String): Boolean{
		val t = c[r].joinToString(separator = "")
		if (kmp(t, char.repeat(K))) return true else return false
	}

	fun checkColumn(col: Int, char: String): Boolean{
		val t = StringBuilder()
		for (i in 0 until h){
			t.append(c[i][col].toString())
		}
		if (kmp(t.toString(), char.repeat(K))) return true else return false
	}

	fun checkDiagonalR(d: Int, char: String): Boolean{
		var cur = (d to 0)
		val t = StringBuilder()
		while(cur.first < h && cur.second < w){
			t.append(c[cur.first][cur.second].toString())
			cur = (cur.first + 1 to cur.second + 1)
		}

		if (kmp(t.toString(), char.repeat(K))) return true else return false
	}

	fun checkDiagonalC(d: Int, char: String): Boolean{
		var cur = (0 to d)
		val t = StringBuilder()
		while(cur.first < h && cur.second < w){
			t.append(c[cur.first][cur.second].toString())
			cur = (cur.first + 1 to cur.second + 1)
		}

		if (kmp(t.toString(), char.repeat(K))) return true else return false
	}

	fun checkDiagonalC1(d: Int, char: String): Boolean{
		var cur = (0 to d)
		val t = StringBuilder()
		while(cur.first < h && cur.second < w && cur.first >= 0 && cur.second >= 0){
			t.append(c[cur.first][cur.second].toString())
			cur = (cur.first + 1 to cur.second - 1)
		}
		
		if (kmp(t.toString(), char.repeat(K))) return true else return false
	}

	fun checkDiagonalR1(d: Int, char: String): Boolean{
		var cur = (d to w - 1)
		val t = StringBuilder()
		while(cur.first < h && cur.second < w && cur.first >= 0 && cur.second >= 0){
			t.append(c[cur.first][cur.second].toString())
			cur = (cur.first + 1 to cur.second - 1)
		}

		if (kmp(t.toString(), char.repeat(K))) return true else return false
	}
}