import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ").toMutableList() // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val r = s[0]
    val c = s[1]
    val N = s[2]

    val table = Array<MutableList<String>>(r){mutableListOf<String>()}

    val redWins = "R".repeat(N)
    val blueWins = "B".repeat(N)

    //read in the table

    for (i in 0 until r){
    	table[i] = rd.readStrings()
    }

    //create words by rows
    for (i in 0 until r){
    	val w = table[i].joinToString(separator = "")
    	if (kmp(w, redWins)) return println("RED WINS")
    	else if (kmp(w, blueWins)) return println("BLUE WINS")
    }

    //create words by column

    for (i in 0 until c){
    	val w = StringBuilder()
    	for (j in 0 until r){
    		w.append(table[j][i])
    	}

    	if (kmp(w.toString(), redWins)) return println("RED WINS")
    	else if (kmp(w.toString(), blueWins)) return println("BLUE WINS")
    }

    //create words by diagonal right
    for (i in 0 until c){
    	val w = StringBuilder()
    	var i = i
    	var j = 0
    	while (i < c && j < r ){
    		w.append(table[j][i])
    		i ++
    		j ++
    	}
    	if (kmp(w.toString(), redWins)) return println("RED WINS")
    	else if (kmp(w.toString(), blueWins)) return println("BLUE WINS")
    }

    for (i in 0 until r){
    	var w = StringBuilder()
    	var i = i
    	var j = 0
    	while (i < r && j < c){
    		w.append(table[i][j])
    		i++
    		j++
    	}
    	if (kmp(w.toString(), redWins)) return println("RED WINS")
    	else if (kmp(w.toString(), blueWins)) return println("BLUE WINS")
    }

    //create words by diagonal left
    for (i in 0 until c){
    	val w = StringBuilder()
    	var i = i
    	var j = 0
    	while (i >= 0 && j < r ){
    		w.append(table[j][i])
    		i --
    		j ++
    	}
    	if (kmp(w.toString(), redWins)) return println("RED WINS")
    	else if (kmp(w.toString(), blueWins)) return println("BLUE WINS")
    }

    for (i in 0 until r){
    	var w = StringBuilder()
    	var i = i
    	var j = c - 1
    	while (j >= 0 && i < r){
    		w.append(table[i][j])
    		i++
    		j--
    	}
    	if (kmp(w.toString(), redWins)) return println("RED WINS")
    	else if (kmp(w.toString(), blueWins)) return println("BLUE WINS")
    }

    return println("NONE")
}

fun kmp(t: String, p: String): Boolean{
	val n = t.length
	val m = p.length
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