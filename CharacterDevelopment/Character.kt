//import java.math.BigInteger

fun main(){
	val j = readLine()!!.toInt()

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

	if ((j == 1) || (j == 0)){
		println(0)
		return
	}

	var result = 0L

	for (i in 2 .. j){
		result = result.plus(nChooseK(j, i))
		//println("from $j people choose $i people is ${nChooseK(j,i)}")
	}

	println(result)
}