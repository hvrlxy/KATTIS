import java.io.*
import kotlin.math.sqrt

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toLong() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    fun isPrime(n: Long): Boolean{
    	for (i in 2 .. sqrt(n.toDouble()).toInt()){
    		if (n % i == 0L) return false
    	}
    	return true
    }

    fun smallestPrime(n: Long): Long{
    	var result = n + 1L
    	while (!isPrime(result)){
    		result ++
    	}
    	return result
    }

    var size = rd.readInt()
    while (size != 0L){
    	val doubleSize = 2*size
    	if (isPrime(size)) println(smallestPrime(doubleSize))
    	else println("${smallestPrime(doubleSize)} ($size is not prime)")
    	size = rd.readInt()
    }
}