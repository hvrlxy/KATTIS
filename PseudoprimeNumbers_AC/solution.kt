import java.io.*
import java.math.BigInteger
import kotlin.math.*

val MAXVALUE = 1000000000

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toBigInteger() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))
    
    var a = rd.readInts()
    while (a[0] + a[1] != "0".toBigInteger()){
        if (a[0].isProbablePrime(20)) println("no")
        else if (a[1].modPow(a[0], a[0]) == (a[1] % a[0])) println("yes")
        else println("no")

        //println("${a[1].modPow(a[0], a[0])} ${a[0] % a[1]}")
        a = rd.readInts()
    }
}