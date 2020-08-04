fun main(){
	val stats = readLine()!!.split(" ").map{ it.toInt() }

	val R = stats[0]
	val W = stats[1]
	val d = stats[2]

	val M = 1000000007

	fun nChooseK(n:Int, k:Int):Long{ 
		if (k > n || k < 0) return 0L
		var result = 1L
		if (k <= n/2L) { 
			for (i in 1L .. k.toLong()){ 
				result = result * (n - i + 1) / i % M
			} 
		} 
		else{ 
			result = nChooseK(n, n - k) 
		} 
		return result % M 
	}

	val wArray = LongArray(R + 1){nChooseK(W - 1, it)}
	//println(wArray.joinToString())

	var result = 0L

	for (c in R / d .. R){
		var redColumns = 0L
		for (k in 0 .. c){
			if (k % 2 == 0) redColumns += (nChooseK(c,k) * nChooseK(R - 1 - k * d, c - 1)) % M
			else redColumns -= (nChooseK(c,k) * nChooseK(R - 1 - k * d, c - 1)) % M
			redColumns = redColumns % M
		}
		println(redColumns)

		var whiteColumns = 0L
		if (c - 2 >= 0) whiteColumns = (wArray[c - 2] + 2 * wArray[c - 1] + wArray[c]) % M
		else if (c - 1 >= 0) whiteColumns = (2 * wArray[c - 1] + wArray[c]) % M
		else whiteColumns x = wArray[c] % M

		result += redColumns * whiteColumns % M
	}

	println(result % M)
}