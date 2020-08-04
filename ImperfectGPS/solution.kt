import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val array = rd.readInts()
    val positionsList = mutableListOf<Triple<Int, Int, Int>>()

    fun dist (x1: Int, y1: Int, x2: Int, y2: Int): Double = sqrt((x1 - x2).toDouble() * (x1 - x2) + (y1 - y2).toDouble() * (y1 - y2))
    fun distDouble (x1: Double, y1: Double, x2: Double, y2: Double): Double = sqrt((x1 - x2).toDouble() * (x1 - x2) + (y1 - y2) * (y1 - y2))

    var realDistance = 0.0
    repeat(array[0]){
        val a = rd.readInts()
        positionsList.add(Triple(a[0], a[1], a[2]))
    }

    for (i in 0 until array[0] - 1){
    	//println(realDistance)
        realDistance += dist(positionsList[i].first, positionsList[i].second, positionsList[i + 1].first, positionsList[i + 1].second)
    }

    //println(realDistance)

    val GPSDistanceList = mutableListOf<Triple<Double, Double, Int>>()
    for (i in 0 until positionsList[array[0] - 1].third step array[1]){
        for (j in 0 until array[0]){
            if (positionsList[j].third > i){
                val k = (i - positionsList[j - 1].third).toDouble() / (positionsList[j].third - positionsList[j - 1].third)
                val x = positionsList[j - 1].first + k * (positionsList[j].first - positionsList[j - 1].first)
                val y = positionsList[j - 1].second + k * (positionsList[j].second - positionsList[j - 1].second)
                GPSDistanceList.add(Triple(x, y, i))
                break
            }
        }
    }
    GPSDistanceList.add(Triple(positionsList[array[0] - 1].first.toDouble(), positionsList[array[0] - 1].second.toDouble(), positionsList[array[0] - 1].third))

    var GPSDistance = 0.0
    for (i in 0 until GPSDistanceList.size - 1){
        GPSDistance += distDouble(GPSDistanceList[i].first, GPSDistanceList[i].second, GPSDistanceList[i + 1].first, GPSDistanceList[i + 1].second)
    }

    //println(GPSDistance)
    //println(realDistance)

    if (abs(GPSDistance - realDistance) < 1e-8) println("0.0")
    else println(abs(GPSDistance - realDistance) / realDistance * 100)
}