import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readLong() = readLn().toLong() // single Long
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Longs

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var instruction = rd.readStrings()

    var average = 0.0
	var numShares = 0

	fun buy (x: Int, y: Double){
		average = (average * numShares + x * y) / (numShares + x)
		numShares += x
		// println(average)
		// println(numShares)
	}

	fun sell (x: Int, y: Double){
		//average = (average * numShares - x * y) / (numShares - x)
		numShares -= x
		// println(average)
		// println(numShares)
	}

	fun split(x: Int){
		numShares *= x
		average /= x
		// println(average)
		// println(numShares)
	}

	fun merge(x: Int){
		numShares = numShares / x
		average *= x
		// println(average)
		// println(numShares)
	}

	fun die (x: Double){
		if (x - average >= 0){
			println(numShares * (x - (x - average) * 0.3))
		}
		else println(numShares * x)
	}
    while (instruction != null){
    	if (instruction[0] == "buy") buy(instruction[1].toInt(), instruction[2].toDouble())
    	else if (instruction[0] == "sell") sell(instruction[1].toInt(), instruction[2].toDouble())
    	else if (instruction[0] == "split") split(instruction[1].toInt())
    	else if (instruction[0] == "merge") merge(instruction[1].toInt())
    	else if (instruction[0] == "die") {
    		die(instruction[1].toDouble())
    		return
    	}
    	instruction = rd.readStrings()
    }
}