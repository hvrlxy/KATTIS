import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() }

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    repeat(numCase){
    	val a = rd.readInts()

    	val locations = mutableListOf<Pair<Double, Double>>()

    	repeat(a[1]){
    		val a1 = rd.readDoubles()
    		locations.add(a1[0] to a1[1])
    	}

    	fun dist(x: Pair<Double, Double>, y: Pair<Double, Double>): Double{
            return (sqrt((x.first - y.first).pow(2.0) + (x.second - y.second).pow(2.0)))
        }

        var distList = mutableListOf<Triple<Int, Int, Double>>()

        for (i in 0 until a[1]){
            for (j in i + 1 until a[1]){
                distList.add(Triple(i, j, dist(locations[i], locations[j])))
            }
        }

        val aSet = DisjointSet(a[1])
        var numComponents = a[1] - a[0]

        distList.sortBy{ it.third }
        var result = 0.0

        while (numComponents > 0){
        	if (aSet.find(distList[0].second) != aSet.find(distList[0].first)){
        		aSet.union(distList[0].first, distList[0].second)
        		result = distList[0].third
        		numComponents -- 
        	}
        	distList.removeAt(0)
        }
        println("%.2f".format(result))
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