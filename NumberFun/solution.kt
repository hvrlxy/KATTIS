fun main(){
	val numCase = readLine()!!.toInt()
	repeat(numCase){
		val a = readLine()!!.split(" ").map{ it.toInt() }
		if (a[0] + a[1] == a[2] || a[0] * a[1] == a[2] || a[0] - a[1] == a[2] || a[0].toFloat() / a[1].toFloat() == a[2].toFloat()) println("Possible")
		else if (a[1] + a[0] == a[2] || a[1] * a[0] == a[2] || a[1] - a[0] == a[2] || a[1].toFloat() / a[0].toFloat() == a[2].toFloat()) println("Possible")
		else println("Impossible")
	}
}