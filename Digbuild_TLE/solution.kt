fun main(){
	for (n in 1 .. 20){
		println("when n = $n, #configurations = ${findTorches(n.toLong())}")
	}
}


fun findTorches(n: Long): Int{
	val MOD = 1000000007

	val A = IntArray(2){1}
	val B = IntArray(2){1}
	val C = IntArray(2){1}
	val D = IntArray(2){1}
	val E = IntArray(2){1}

	var i = 2L
	while (i <= n){
		if (i % 2 == 0L){
			A[1] = ((((A[0] + B[0]) % MOD + C[0]) % MOD + D[0]) % MOD + E[0]) % MOD
			B[1] = (((A[0] + C[0]) % MOD + D[0]) % MOD + E[0]) % MOD
			C[1] = ((A[0] + B[0]) % MOD + D[0]) % MOD
			D[1] = ((A[0] + B[0]) % MOD + C[0]) % MOD
			E[1] = (A[0] + B[0]) % MOD
		}
		else{
			A[0] = ((((A[1] + B[1]) % MOD + C[1]) % MOD + D[1]) % MOD + E[1]) % MOD
			B[0] = (((A[1] + C[1]) % MOD + D[1]) % MOD + E[1]) % MOD
			C[0] = ((A[1] + B[1]) % MOD + D[1]) % MOD
			D[0] = ((A[1] + B[1]) % MOD + C[1]) % MOD
			E[0] = (A[1] + B[1]) % MOD
		}
		i ++
	}
	if (n % 2 == 0L) return (((((A[1] + B[1]) % MOD + C[1]) % MOD + D[1]) % MOD + E[1]) % MOD)
	else return (((((A[0] + B[0]) % MOD + C[0]) % MOD + D[0]) % MOD + E[0]) % MOD)
}