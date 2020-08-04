import kotlin.math.pow

fun main(){
	val s = readLine()!!.split(" ").map{it.toLong()}

	val A = s[0]
	val B = s[1]
	val S = s[2].toInt()

	val l = B.toString().length

	val g = Array<IntArray>(l + 1){IntArray(S + 1)}


	for (i in 1 .. l){
		for (j in 0 .. S){
			if (j == 0 && i == 1) g[i][j] = 1
			else if (j == 0) g[i][j] = 0
			else if (i == 1 && j < 10) g[i][j] = 1
			else if (i == 1 && j >= 10) g[i][j] = 0
			else{
				for (k in 0 .. 9){
					if (j > k) g[i][j] += (g[i - 1][j - k])
				}
			}
		}
	}

	// for (i in 0 .. l){
	// 	println(g[i].joinToString())
	// }

	fun f (N: Long, s: Int): Int{
		var result = 0
		val stringN = N.toString()
		val D = stringN[0].toString().toInt()
		val L = stringN.length

		if (s < 0) return 0
		if (N < 10L && s < 10 && N >= s) return 1
		else if (s < 10 && N < s) return 0
		else if (N < 10L && s >= 10) return 0
		//println("N: $N, s: $s, D: $D")

		for (i in 0 .. D - 1){
			if (s >= i) {
				result += g[L - 1][s - i]
				//println("g[${L - 1}][${s - i}]: ${g[L - 1][s - i]}")
			}
		}

		val newN = N - D * (10.0.pow(L.toDouble() - 1)).toInt()
		result += f(newN, s - D)

		println("$N $s $result")
		return result
	}

	println("${f(B, S) - f(A - 1, S)}")
}