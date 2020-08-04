import java.io.*
import kotlin.math.* 

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() } // list of doubles

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var n = rd.readInt()
    while (n != 0){
    	val locationList = mutableListOf<Pair<Double, Double>>()

    	fun totalDistance(x: Double): Double{
	    	var result = 0.0
	    	for (i in locationList) {
	    		result = max(sqrt((x - i.first) * (x - i.first) + i.second * i.second), result)
	    	}
	    	return result
	    }

    	var max = Double.MIN_VALUE
    	var min = Double.MAX_VALUE

    	repeat(n){
    		val a = rd.readDoubles()
    		locationList.add(a[0] to a[1])
    		if (a[0] > max) max = a[0]
    		if (a[0] < min) min = a[0]
    	}

    	var resultX = 0.0
    	var x = min
    	var time = Double.MAX_VALUE
    	while (x < max){
    		val tryTime = totalDistance(x)
    		//println("total distance = ${totalDistance(x)} with x = $x")
    		if (tryTime < time) {
    			time = tryTime
    			resultX = x
    		}
    		x += 0.00001
    	}

    	println("$resultX $time")

    	rd.readLn()
    	n = rd.readInt()
    }
}