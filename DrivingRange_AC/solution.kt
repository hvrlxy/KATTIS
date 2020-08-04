import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()

	var aSet = DisjointSet(a[0])

	val edgeList = mutableListOf<Triple<Int, Int, Int>>()

	repeat(a[1]){
		val b = rd.readInts()
		edgeList.add(Triple(b[0], b[1], b[2]))
	}

	edgeList.sortBy{ it.third }

	var V = 0

	var result = Int.MIN_VALUE

	for (v in edgeList){
		if (V == a[0] - 1) break
		if (aSet.find(v.first) != aSet.find(v.second)){
			V ++
			aSet.union(v.first, v.second)
			if (v.third > result) result = v.third
		}
	}

	if (V < a[0] - 1) println("IMPOSSIBLE") else println(result)
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