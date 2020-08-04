fun main(){
	val target = readLine()!!.toInt()

	val scoreArray = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
		19,20,22,24,26,28,30,32,34,36,38,40,21,27,33,39,42,45,48,51,54,57,60)

	fun printResult(a: Int){
		if (a <= 20) println("single $a")
		else if (a % 2 == 0 && a <= 40) println("double ${a/2}")
		else if (a % 3 == 0) println("triple ${a/3}")
	}

	for (i in 0 until scoreArray.size){
		if (scoreArray[i] == target){
			printResult(scoreArray[i])
			return
		}
		for (j in i until scoreArray.size){
			if (scoreArray[i] + scoreArray[j] == target){
				printResult(scoreArray[j])
				printResult(scoreArray[i])
				return
			}
			for (k in j until scoreArray.size){
				if (scoreArray[i] + scoreArray[j] + scoreArray[k] == target){
					printResult(scoreArray[j])
					printResult(scoreArray[i])
					printResult(scoreArray[k])
					return
				}
			}
		}
	}

	println("impossible")
}