val array = listOf(1729, 4104, 13832, 20683, 32832, 39312, 40033, 46683, 64232, 65728, 110656, 110808, 134379, 149389, 165464, 171288, 195841, 216027, 216125, 262656, 314496, 320264, 327763, 373464, 402597)

fun main(){
	val limit = readLine()!!.toInt()

	if (limit < array[0]) return println("none")
	for (i in 0 until array.size){
		if (limit < array[i]) return println(array[i - 1])
	}
}