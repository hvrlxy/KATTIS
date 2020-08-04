fun main(){
	val s = readLine()!!.split(" ").map{it.toInt()}
	val p = DoubleArray(s[0] + s[1] - 1){readLine()!!.toDouble()}

	val opt = Array<DoubleArray>(s[0] + 1){DoubleArray(s[1] + 1){0.0}}

	for (a in 0 .. s[0]){
		for (c in 0 .. s[1]){
			if (a == 0) opt[a][c] = 0.0 else if (c == 0) opt[a][c] = 1.0
			else{
				opt[a][c] = p[s[0] + s[1] - a - c] * opt[a][c - 1] + (1.0 - p[s[0] + s[1] - a - c]) * opt[a - 1][c]
			}
		}
	}
	println(opt[s[0]][s[1]])
}