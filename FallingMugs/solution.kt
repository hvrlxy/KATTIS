import kotlin.math.sqrt

fun main(){
	val num = readLine()!!.toInt()

	if (num == 1){
		println("0 1")
		return
	}
	val array = find(num)
	//println(array.joinToString())

	if (array[0] == -1){
		println("impossible")
		return
	}

	else{
		val x = (array[1] - array[0]) / 2
		val y = array[1] - x
		println("$x $y")
	}
}

fun find(x: Int): IntArray{
	//val result = IntArray(2){-1} 
	for (i in 1 .. x/2){
		if ((x % i == 0) && (((i % 2 == 0) && (x/i % 2 == 0)) || ((i % 2 == 1) && (x / i % 2 == 1)) )){
			val result = intArrayOf(i, x / i)
			result.sort()
			return result
		}
	}

	return (intArrayOf(-1, -1))
}