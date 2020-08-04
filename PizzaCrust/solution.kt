fun main(){
	val a = readLine()!!.split(" ").map { it.toInt() }

	fun findArea(r: Int): Double = kotlin.math.PI * r * r

	println(findArea(a[0] - a[1]) / findArea(a[0]) * 100)
}