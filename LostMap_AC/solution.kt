import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numRoads = rd.readInt()
	val distance = mutableListOf<Triple<Int, Int, Int>>()

	val aSet = DisjointSet(numRoads)

	for (i in 1 .. numRoads){
		val array = rd.readInts()
		for (j in i until numRoads){
			distance.add(Triple(i, j + 1, array[j]))
		}
	}

	distance.sortBy{it.third}

	for (i in 0 until distance.size){
		if (aSet.find(distance[i].first - 1) != aSet.find(distance[i].second - 1)){
			aSet.union(distance[i].first - 1, distance[i].second - 1)
			println("${distance[i].first} ${distance[i].second}")
		}
	}
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