fun main(){
	val numBooks = readLine()!!.toInt()

	val array = mutableListOf<Int>()
	for (i in 0 until numBooks){
		array.add(readLine()!!.toInt())
	}

	array.sortDescending()
	//array.reversed()

	//println(array)

	var i = 1
	while (i * 3 - 1 < array.size){
		array[i * 3 - 1] = 0
		i ++
	}
	println(array.sum())
}