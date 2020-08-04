fun main(){
	var aString = readLine()

	while (aString != null){
		val n = aString.length
		val opt = Array<BooleanArray>(n){ BooleanArray(n) }

		val aSet = mutableSetOf<String>()

		for (i in 0 until n){
			opt[i][i] = true
		}
		for (i in 0 until n - 1){
			if (aString[i + 1] == aString[i]) {
				opt[i][i + 1] = true
				aSet.add(aString.substring(i..i+1))
			}
		}

		for (k in 2 .. n){
			for (i in 0 until n){
				//println("i: $i , j: $j - ${aString[i]} ${aString[j]} - ${opt[i + 1][j - 1]}")
				val j = i + k
				if (j >= n) break
				if (aString[i] == aString[j] && opt[i + 1][j - 1]){
					opt[i][j] = true
					aSet.add(aString.substring(i..j))
				}
			}
		}

		// for (i in opt){
		// 	println(i.joinToString())
		// }

		println(aSet.toList().sorted().joinToString(separator = "\n"))
		aString = readLine()
		if (aString != null) println()
	}
}