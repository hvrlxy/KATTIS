import java.io.*
import java.math.BigInteger
import kotlin.math.sqrt

private fun BufferedReader.readLn() = readLine()!! // string line

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readLn().toInt()

    fun isBinary(a: String): Boolean{
    	val charList = listOf('0', '1')

    	for (i in a){
    		if (i !in charList) return false
    	}

    	return true
    }

    fun isOctal(a: String): Boolean{
    	val charList = listOf('0', '1', '2', '3', '4', '5', '6', '7')

    	for (i in a){
    		if (i !in charList) return false
    	}

    	return true
    }

    fun isDecimal(a: String): Boolean{
    	val charList = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    	for (i in a){
    		if (i !in charList) return false
    	}

    	return true
    }

    fun isPrime(n: Long): Boolean{
    	if (n < 2L) return false
    	for (i in 2 .. sqrt(n.toDouble()).toInt()){
    		if (n % i == 0L) return false
    	}

    	return true
    }

    fun gcd(a: Int, b: Int): Int{
    	if (a == 0) return b
    	else return gcd(b % a, a)
    }

    repeat(numCase){
    	val aString = rd.readLn()
    	val aSet = mutableListOf<BigInteger>()
    	aSet.add(BigInteger(aString, 16))

    	if (isDecimal(aString)) aSet.add(BigInteger(aString, 10))

    	if (isOctal(aString)) aSet.add(BigInteger(aString, 8))

    	if (isBinary(aString)) aSet.add(BigInteger(aString, 2))

    	//println(aSet)

    	var numPrime = 0
    	for (i in aSet){
    		val n = i.toLong()

    		if (isPrime(n)) numPrime++
    	}

    	if (numPrime == 0) println("0/1") else println("${numPrime / gcd(numPrime, aSet.size)}/${aSet.size / gcd(numPrime, aSet.size)}")
    }
}