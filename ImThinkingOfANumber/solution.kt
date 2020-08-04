import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var numInstructions = rd.readInt()

    fun gcd(a: Int, b: Int): Int{
    	var a = a
    	var b = b

    	while (b > 0){
    		a = b
    		b = a % b
    	}
    	return a
    }

    while (numInstructions != 0){
    	var lowerBound = 0
    	var upperbound = Int.MAX_VALUE
    	var divisor = 1
    	repeat(numInstructions){
    		val instruction = rd.readStrings()
    		if (instruction[0] == "less" && instruction[2].toInt() < upperbound){
    			upperbound = instruction[2].toInt()
    		}
    		else if (instruction[0] == "greater" && instruction[2].toInt() > lowerBound){
    			lowerBound = instruction[2].toInt()
    		}
    		else if (instruction[0] == "divisible" && divisor % instruction[2].toInt() != 0){
    			divisor *= gcd(divisor, instruction[2].toInt())
    		}
    	}

    	if (upperbound == Int.MAX_VALUE) println("infinite")
    	else{
    		var result = mutableListOf<Int>()
    		for (i in lowerBound + 1 until upperbound){
    			if (i % divisor == 0) result.add(i)
    		}
    		if (result.size == 0) println("none")
    		else println(result.joinToString(separator = " "))
    	}
    	numInstructions = rd.readInt()
    }
}