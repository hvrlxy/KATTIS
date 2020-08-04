fun main(){
	val numGames = readLine()!!.toInt()

	repeat(numGames){
		val opponent = readLine()!!
		val Y = readLine()!!

		val OArray = IntArray(13){0}
		val YArray = IntArray(13){0}

		val chars = "23456789TJQKA"
		for (i in 0 until 26){
			OArray[chars.indexOf(opponent[i])] ++
			YArray[chars.indexOf(Y[i])] ++
		}
		//println("${OArray.joinToString()}\n${YArray.joinToString()}")

		var numWins = 0

		for (i in 0 until 13){
			for (j in i - 1 downTo 0){
				if (OArray[j] <= YArray[i]) {
					numWins += OArray[j] * 2
					YArray[i] -= OArray[j]
					OArray[j] = 0
				}
				else{
					numWins += YArray[i] * 2
					OArray[j] -= YArray[i]
					YArray[i] = 0
					break
				}
				if (YArray[i] == 0) break
			}
		}

		//println("${OArray.joinToString()}\n${YArray.joinToString()}")

		for (i in 0 until 13){
			numWins += kotlin.math.min(YArray[i], OArray[i])
		}
		println(numWins)
	}
}