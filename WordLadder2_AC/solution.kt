//bi-directional bfs

import java.io.*
//import java.util.PriorityQueue
import java.util.ArrayDeque

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val dictSize = rd.readInt()
	val wordList = mutableListOf<String>()
	val adj = Array<MutableList<Int>>(dictSize){mutableListOf<Int>()}

	fun isEdge(a: String, b: String): Boolean{
		var isEdge = 0
		for (c in 0 until a.length){
			if (a[c] != b[c]) isEdge ++
			if(isEdge > 1) return false
		}
		return true
	}

	for (i in 0 until dictSize){
		val aline = rd.readLn()
		wordList.add(aline)
		for (j in 0 until i){
			if (isEdge(wordList[i], wordList[j])){
				adj[i].add(j)
				adj[j].add(i)
			}
		}
	}

	// we need another BFS from the destination and contract them

	val D = IntArray(dictSize){Int.MAX_VALUE}
	D[1] = 0
	val Q1 = ArrayDeque<Int>()
	Q1.add(1)
	while (Q1.isNotEmpty()){
		val v = Q1.getFirst()
		Q1.removeFirst()
		for (u in adj[v]){
			if (D[u] > D[v] + 1){
				D[u] = D[v] + 1
				Q1.add(u)
			}
		}
	}

	data class Word (val s: String, val d: Int)

	val reachable = mutableListOf<Word>()

	for (i in 0 until dictSize){
		if (D[i] < Int.MAX_VALUE){
			reachable.add(Word(wordList[i], D[i]))
		}
	}

	reachable.sortBy{it.d}

	var minDistance = Int.MAX_VALUE

	var possibleResult = "ZZZZZZZZ"

	val d = IntArray(dictSize){Int.MAX_VALUE}
	d[0] = 0

	fun isPossible(a: String, i: Int){
		for (j in 0 until reachable.size){
			val endWord = reachable[j].s
			val alist = mutableListOf<String>()
			var diff = 0
			for (c in 0 until a.length){
				if (a[c] != endWord[c]){
					alist.add(endWord.replaceRange(c,c + 1, a.substring(c, c + 1)))
					diff ++
				}
			}
			//println("$a, $endWord")
			//println(alist)
			if (diff != 2) continue
			else{
				if (d[i] + 2 + reachable[j].d < minDistance){
					alist.sort()
					possibleResult = alist[0]
					minDistance = d[i] + 2 + reachable[j].d
					return
				}
				if (d[i] + 2 + reachable[j].d == minDistance) {
					alist.add(possibleResult)
					alist.sort()
					possibleResult = alist[0]
					return
				}
			}
		}
	}

	val Q = ArrayDeque<Int>()
	Q.add(0)

	while (Q.isNotEmpty()){
		val v = Q.getFirst()
		isPossible(wordList[v],v) // you must insert it here in case the starting word is compatible to the ending word
		Q.removeFirst()
		for (u in adj[v]){
			if (d[u] > d[v] + 1) {
				d[u] = d[v] + 1
				Q.add(u)
			}
		}
	}

	if (minDistance < d[1]){
		println(possibleResult)
		println(minDistance)
	}
	else{
		if (d[1] < Int.MAX_VALUE){
			println("0\n${d[1]}")
		}
		else println("0\n-1")
	}
}







