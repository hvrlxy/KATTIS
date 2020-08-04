import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

//be careful with leading zeros

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    fun gcd(a: Long, b: Long): Long{
    	//calculate the gcd
    	if (b == 0L) return a else return gcd(b, a % b)
    }

    data class Fraction (val e: Long, val d: Long){
    	override fun toString(): String = "${this.e}/${this.d}"
    }

    fun powerOfTen(d: Int): Long{
    	var r = 1L
    	for (i in 0 until d){
    		r *= 10
    	}
    	return r
    }

    fun findFraction(a: String, b: String): Fraction{
    	//println("a: $a, b: $b")

    	if (b == ""){
    		var d = powerOfTen(a.length) - 1
    		val gcd = gcd(a.toLong(), d)
    		return Fraction(a.toLong() / gcd,d / gcd)
    	}
    	var e = a.toLong() - b.toLong()
    	var d = powerOfTen(a.length) - powerOfTen(b.length)

    	val gcd = gcd(e,d)
    	return Fraction(e / gcd,d / gcd)
    }

    var aline = readLine()!!
    while (aline != "0"){
    	val str = aline.substring(2, aline.length - 3)
    	var frac = Fraction(0L, Long.MAX_VALUE)
    	for (i in 1 .. str.length){
    		val f = findFraction(str, str.substring(0, str.length - i))
    		if (f.d < frac.d) frac = f
    	}

    	println(frac)
    	aline = readLine()!!
    }
}