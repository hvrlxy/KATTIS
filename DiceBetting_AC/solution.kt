fun main(){
	val a = readLine()!!.split(" ").map{it.toInt()}

	val n = a[0]
	val s = a[1]
	val k = a[2]

	if (n < k) return println(0.0)

	val P = Array<DoubleArray>(s + 1){DoubleArray(n + 1){0.0}}

	//println("$n $s $k")

	fun baseCase(j: Int): Double{
		var result = 1.0
		for (i in s downTo (s - j + 1)){
			result *= (i.toDouble()) / s
		}
		return result
	}

	for (i in 1 .. s){
		P[i][i] = baseCase(i)
	}

	for (i in 1 .. s){
		for (j in i + 1 .. n){
			P[i][j] = (i.toDouble() / s) * P[i][j - 1] + (s - i + 1) / s.toDouble() * P[i - 1][j - 1]
		}
	}

	var result = 0.0

	for (i in k .. s){
		result += P[i][n]
	}

	println(result)
}