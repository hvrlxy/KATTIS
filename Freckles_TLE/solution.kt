import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toFloat() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var printResult = StringBuilder()
	repeat(rd.readInt()){
		rd.readLn()
		var numLocations = rd.readInt()

		val locations = mutableListOf<Pair<Float, Float>>()

		val aSet = DisjointSet(numLocations)
		var result = 0.0f

		var distList = mutableListOf<Triple<Int, Int, Float>>()

		fun dist(x: Pair<Float, Float>, y: Pair<Float, Float>): Float = sqrt((x.first - y.first) * (x.first - y.first) + (x.second - y.second) * (x.second - y.second))

		repeat(numLocations){
			val a = rd.readInts()
			locations.add(a[0] to a[1])
			for (i in 0 until locations.size - 1){
				distList.add(Triple(i, locations.size - 1, dist(locations[i], locations[locations.size - 1])))
			}
		}

		distList.sortBy{ it.third }

		for (i in distList){
        	if (aSet.find(i.second) != aSet.find(i.first)){
        		aSet.union(i.first, i.second)
        		result += i.third
        		numLocations --
        		if (numLocations == 1 ) break
        	}
        }

		printResult.append("%.2f".format(result))
		printResult.append("\n")
	}
	print(printResult)
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