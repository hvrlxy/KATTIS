fun main(){
	val num = readLine()!!.toInt()
	val array = readLine()!!.split(" ").map{ it.toInt() }.toMutableList()
	array.sort()

	var f = 1.0
	for (i in 1 .. num){
		if (array[i - 1].toDouble()/i.toDouble() > 1.0) return println("impossible")
		else if (array[i - 1]/i.toDouble() < f) f = array[i - 1]/i.toDouble()
	}
	println(f)
}