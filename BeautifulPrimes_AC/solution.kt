import kotlin.math.*	

fun main(){
	val numCase = readLine()!!.toInt()
	repeat(numCase){
		val n = readLine()!!.toInt()
		var digits = n * log10(7.0).toInt() + 1
		var count = n
		while (digits != n){
			count --
			digits = (count * log10(7.0) + (n - count) * log10(11.0)).toInt() + 1
		}
		val aString = StringBuilder("${"7 ".repeat(count)}${"11 ".repeat(n - count)}")
		println(aString.deleteCharAt(aString.lastIndex))
	}
}