fun main(args: Array<String>){
	val n = args[0].toInt()
	val k = args[1].toInt()

	println(nChooseK(n,k))
}

fun nChooseK(n: Int, k: Int): Long{
	var result = 1L
	if (k <= n/2L) {
		for (i in 1L .. k.toLong()){
			result = result * (n - i + 1) / i
		}
	}
	else{
		result = nChooseK(n, n - k)
	}
	return result
}