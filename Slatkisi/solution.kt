import kotlin.math.*

fun main(){
	val a = readLine()!!.split(" ").map{ it.toInt() }

	val numCandies = a[0]
	val a1 = (numCandies.toDouble()/(10F.pow(a[1])))
	println(a1.roundToInt() * 10F.pow(a[1]).toInt())
}