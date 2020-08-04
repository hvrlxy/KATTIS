import java.io.*
import kotlin.math.*
import java.math.BigInteger

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toBigInteger() } // list of ints


fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val n = rd.readInt()

    val a = rd.readInts()

    val sumTable = Array<BigInteger>(n){"0".toBigInteger()}
    val productTable = Array<BigInteger>(n){"1".toBigInteger()}


    var MAX_SUM = "0".toBigInteger()
    for (i in 0 until n){
    	MAX_SUM += a[i]
    }

    var count = 0

    for (i in 0 until n){
    	for (j in i until n){
    		if (i == j) {
    			sumTable[j] = a[i]
    			productTable[j] = a[i]
    		}
    		else{
    			sumTable[j] = sumTable[j - 1] + a[j]
    			productTable[j] = productTable[j - 1] * a[j]
    			if (productTable[j] > MAX_SUM) break

    			if (sumTable[j] == productTable[j]) count ++
    		}
    	}
    }

    println(count)
}