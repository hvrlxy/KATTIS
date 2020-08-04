import java.io.*
import java.util.PriorityQueue
import kotlin.math.*

// have to use long in this problem

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Edge (val u: Int, val v: Int, val w: Int): Comparable<Edge>{
	override fun compareTo(other: Edge): Int = this.w.compareTo(other.w)
}

val INF = 1000000000

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    repeat(numCase){
    	val s = rd.readInts()

    	val N = s[0]
    	val M = s[1]
    	val L = s[2].toLong()
    	val S = s[3]

    	val sList = rd.readInts().toMutableList()

    	val remaining = (N - S).toLong()

    	val distList = PriorityQueue<Edge>()

    	repeat(M){
    		val a = rd.readInts()

    		distList.add(Edge(a[0], a[1], a[2]))
    	}

    	for (i in sList){
    		distList.add(Edge(0, i, 0))
    	}

    	println(MSTcalc(distList, N + 1) + L * remaining)
    }
}

fun MSTcalc(distList: PriorityQueue<Edge>, N: Int): Long{
	val aSet = DisjointSet(N)

	var cost = 0L
	var numEdges = 0
	while (numEdges < N - 1){
		val e = distList.poll()
		if (aSet.find(e.v) != aSet.find(e.u)){
			aSet.union(e.v, e.u)
			cost += e.w
			numEdges ++
		}
	}

	return cost
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