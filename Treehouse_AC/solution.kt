
import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    val aSet = DisjointSet(a[0])

    val locationList = mutableListOf<Pair<Double, Double>>()
    repeat(a[0]){
    	val a1 = rd.readDoubles()
    	locationList.add(a1[0] to a1[1])
    }

    val distanceList = mutableListOf<Triple<Int, Int, Double>>()
    fun dist (x1: Double, y1: Double, x2: Double, y2: Double): Double = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))

    repeat(a[2]){
    	val a2 = rd.readInts()
    	distanceList.add(Triple(a2[0] - 1, a2[1] - 1, 0.0))
    }

    for (i in 0 until a[1] - 1){
    	distanceList.add(Triple(i, i + 1, 0.0))
    }

    for (i in 0 until a[0]){
    	for (j in i + 1 until a[0]){
    		distanceList.add(Triple(i,j,dist(locationList[i].first, locationList[i].second, locationList[j].first, locationList[j].second)))
    	}
    }

    var result = 0.0
    distanceList.sortBy{it.third}
    var count = a[0] - 1

    while (count > 0){
    	val d = distanceList[0]
    	if (aSet.find(d.first) != aSet.find(d.second)){
    		result += d.third
    		aSet.union(d.first, d.second)
    		count --
    	}
    	distanceList.removeAt(0)
    }

    println(result)
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