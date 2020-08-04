import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()

    var result = 1L

    var fivePower = 5
    var numFive = 0
    while (n >= fivePower){
    	numFive += n / fivePower
    	fivePower *= 5
    }

    var numTwo = numFive

    for (i in 1 .. n){
    	var v = i
    	while (v % 5 == 0 && numFive > 0){
    		v /= 5
    		numFive --
    	}
    	while (v % 2 == 0 && numTwo  > 0){
    		v /= 2
    		numTwo --
    	}

    	result = (result * v) % 1000
    }

    if (n > 7) println("${"0".repeat(3 - result.toString().length)}$result") else println(result)
}