import kotlin.math.sqrt

fun main(){
	val numCandies = readLine()!!.toLong()

	val array = mutableListOf<Long>()

	for (i in 1L .. sqrt(numCandies.toDouble()).toLong()){
		if (numCandies % i == 0L){
			//println(i)
			array.add(i - 1L)
			if (numCandies/i - 1 !in array){
				array.add(numCandies/i - 1)
			}
		}
	}

	array.sort()

	var result = ""
	for (i in 0 until array.size){
		result += "${array[i]} "
	}

	println(result.slice(0 .. result.length - 2))
}