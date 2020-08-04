fun main(){
	var n = readLine()!!.toInt()
	val sequence = mutableListOf<Int>()
	sequence.add(n)
	
	while (n != 1){
		for (i in 2 .. n){
			if (n%i == 0){
				n /= i
				sequence.add(n)
				//println(n)
				break
			}
		}
	}

	val result = StringBuilder()
	//result.append("$n")
	for (i in 0 until sequence.size - 1){
		result.append("${sequence[i]} ")
	}
	result.append("${sequence[sequence.size - 1]}")
	println(result
}
