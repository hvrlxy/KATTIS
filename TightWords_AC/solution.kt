fun main(){
	var stat = readLine()

	while (stat != null){
		val stats = stat.split(" ").map{ it.toInt() }
		val k = stats[0]
		val n = stats[1]

		if (k == 0) println("100.0")
		else{
			val opt = Array<DoubleArray>(n + 1){DoubleArray(k + 1){0.0}}

			for (j in 0 .. k){
				opt[1][j] = 100.0/(k + 1)
			}

			for (i in 2 .. n){
				for (j in 0 .. k){
					if (j == 0) opt[i][j] = (opt[i - 1][0] + opt[i - 1][1]) / (k + 1)
					else if (j == k) opt[i][j] = (opt[i - 1][k - 1] + opt[i - 1][k]) / (k + 1)
					else opt[i][j] = (opt[i - 1][j - 1] + opt[i - 1][j] + opt[i - 1][j + 1]) / (k + 1) 
				}
			}

			var result = 0.0

			for (i in 0 .. k){
				result += opt[n][i]
			}

			println(result)
		}
		stat = readLine()
	}
}