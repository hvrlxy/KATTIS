import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Planet (val i: Int, val x: Int, val y: Int, val z: Int){
	fun dist(other: Planet): Int = minOf(abs(this.x - other.x), abs(this.y - other.y), abs(this.z - other.z))
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPlanets = rd.readInt()
    val planetList = mutableListOf<Planet>()
    val distanceList = mutableListOf<Triple<Int, Int, Int>>()
    var count = 0
    repeat(numPlanets){
    	val a = rd.readInts()

    	planetList.add(Planet(count, a[0], a[1], a[2]))
    	count++
    }

    //find immediate neighbor in the x-axis
    planetList.sortBy{it.x}
    for (i in 0 until numPlanets - 1){
   		distanceList.add(Triple(planetList[i].i, planetList[i + 1].i, planetList[i].dist(planetList[i + 1])))
    }

    //find immediate neighbor in the y-axis
    planetList.sortBy{it.y}
    for (i in 0 until numPlanets - 1){
   		distanceList.add(Triple(planetList[i].i, planetList[i + 1].i, planetList[i].dist(planetList[i + 1])))
    }

    //find immediate neighbor in the z-axis
    planetList.sortBy{it.z}
    for (i in 0 until numPlanets - 1){
   		distanceList.add(Triple(planetList[i].i, planetList[i + 1].i, planetList[i].dist(planetList[i + 1])))
    }

    distanceList.sortBy{it.third}

    var cost = 0
    var edges = 0
    val aSet = DisjointSet(numPlanets)

    for (i in 0 until distanceList.size){
    	if (edges == numPlanets - 1) break
    	if (aSet.find(distanceList[i].first) != aSet.find(distanceList[i].second)){
    		aSet.union(distanceList[i].first, distanceList[i].second)
    		cost += distanceList[i].third
    		edges ++
    	}
    }
    println(cost)
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